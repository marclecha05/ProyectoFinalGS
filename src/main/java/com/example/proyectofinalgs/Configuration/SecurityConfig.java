package com.example.proyectofinalgs.Configuration;

import com.example.proyectofinalgs.Repositories.UserRepository;
import com.example.proyectofinalgs.Services.CustomOAuth2UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private UserRepository userService; // Servicio para interactuar con la base de datos del usuario

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(h-> {
                    h.requestMatchers(HttpMethod.GET,"/","a").permitAll();
                    h.anyRequest().authenticated();
                })
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            request.getSession().invalidate(); // Invalidar la sesión manualmente
                            response.sendRedirect("/login?logout");
                        })
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // Página personalizada de login para OAuth2
                        .defaultSuccessUrl("/home.html", true) // Redirigir a /dashboard después de un login exitoso
                        .failureUrl("/login?error")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // Servicio para obtener los datos del usuario
                        )
                        .successHandler(oauth2SuccessHandler()))
                .build();
    }

    private AuthenticationSuccessHandler oauth2SuccessHandler() {
        return (request, response, authentication) -> {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            Map<String, Object> attributes = oauth2User.getAttributes();
            String email = (String) attributes.get("email");
            String name = (String) attributes.get("name");
            User user = userService.findByEmail(email);
            response.sendRedirect("/home.html");
        };
    }
}