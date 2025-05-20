package com.example.proyectofinalgs.Configuration;

import com.example.proyectofinalgs.Entities.Usuario;
import com.example.proyectofinalgs.Repositories.UserRepository;
import com.example.proyectofinalgs.Services.CustomOAuth2UserService;
import com.example.proyectofinalgs.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register.html", "/registerdos.html", "/registertres.html", "/login","/oauth2/**","/registerForm", "/logout", "/error", "/image/**","/**").permitAll()
                        .requestMatchers("/home", "/user.html").hasRole("CLIENTE")
                        .requestMatchers("/calendarioProveedor.html", "/userProveedor").hasRole("PROVEEDOR")
                        .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        //.defaultSuccessUrl("/home", true) // Redirige a "/home" tras login
                        .failureUrl("/login?error")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService))
                        .successHandler(oauth2SuccessHandler())) // Manejo de éxito personalizado
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            request.getSession().invalidate(); // Invalidar la sesión
                            response.sendRedirect("/login?logout");
                        })
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Codificador seguro de contraseñas
    }
    @Bean
    public AuthenticationSuccessHandler oauth2SuccessHandler() {
        return (request, response, authentication) -> {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            Map<String, Object> attributes = oauth2User.getAttributes();

            String email = (String) attributes.get("email");
            String name = (String) attributes.get("name");

            // Busca o crea el usuario en la base de datos
            Usuario usuario = userRepository.findByEmail(email);
            if (usuario == null) {
                usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setUsername(name != null ? name : "Usuario sin nombre");
                usuario.setPassword(passwordEncoder().encode("1")); // Contraseña predeterminada
                userRepository.save(usuario); // Guarda el usuario con datos mínimos
                response.sendRedirect("/register.html");
            } else {
                if (usuario.getRol() == null || usuario.getRol().isBlank()) {
                    response.sendRedirect("/register.html");
                } else {
                    switch (usuario.getRol()) {
                        case "CLIENTE" -> response.sendRedirect("/home.html");
                        case "PROVEEDOR" -> response.sendRedirect("/calendarioempresa.html");
                        default -> response.sendRedirect("/register.html");
                    }
                }
            }

           /* try {
                // Envía el correo de bienvenida
                emailService.enviarCorreo(email, "¡Bienvenido a Turnify!", "Hola " + name + ", gracias por registrarte en Turnify.");
            } catch (MessagingException e) {
                e.printStackTrace(); // Registra la excepción en el log
            }
*/
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
