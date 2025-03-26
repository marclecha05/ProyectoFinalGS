package com.example.proyectofinalgs.Services;

import com.example.proyectofinalgs.Entities.User;
import com.example.proyectofinalgs.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;


@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Autowired
    private UserRepository userRepository;

    private final OAuth2UserService <OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

    private PasswordEncoder passwordEncoder;

    public CustomOAuth2UserService( @Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        // Obtiene el usuario de Google
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email"); // Extrae el email del usuario

        // Busca el usuario en la base de datos
        User user = userRepository.findByEmail(email);

        // Si el usuario no existe, lo creamos
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setUsername(oAuth2User.getAttribute("name"));
            if (user.getUsername()==null){
                user.setUsername(oAuth2User.getAttribute("login"));
            }
            user.setPassword(passwordEncoder.encode("1"));

            // Establece una contraseña predeterminada codificada
            user.setPassword(passwordEncoder.encode("1"));  // O cualquier otra lógica
            userRepository.save(user);
        } else {

            // Add this line at the beginning of the loadUser method
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            
            // Then, the rest of your code
            if (user.getRol().equals("USUARIO_ROL")){
                try {
                    response.sendRedirect("/home.html");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (user.getRol().equals("PROVEEDOR_LABORAL")){
                try {
                    response.sendRedirect("/calendarioProveedor.html");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // Devuelve el usuario autenticado directamente
        return oAuth2User; // No necesito roles, devolvere el objeto y au
    }
}
