package com.example.proyectofinalgs.Controllers;

import com.example.proyectofinalgs.Entities.User;
import com.example.proyectofinalgs.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class RegisterController {

    private final UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/registerForm")
    public void procesarRespuestas(@RequestParam String respuesta1,
                                   @RequestParam(required = false) String respuesta2, HttpServletResponse response) throws IOException {
        System.out.println("juan gilipollas");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email=((OAuth2User) authentication.getPrincipal()).getAttribute("email");
        User user = userRepository.findByEmail(email);

        if (user != null) {
            if ("usuario".equals(respuesta1)) {
                user.setRol("USUARIO");
                userRepository.save(user);
                response.sendRedirect("/home");
            } else if ("proveedor".equals(respuesta1)) {
                if ("ambos".equals(respuesta2)) {
                    user.setRol("PROVEEDOR_AMBOS");
                    userRepository.save(user);
                    response.sendRedirect("/registerdos.html");
                } else if ("laboral".equals(respuesta2)) {
                    user.setRol("PROVEEDOR_LABORAL");
                    userRepository.save(user);
                    response.sendRedirect("/registerdos.html");
                }

            }
        } else {
            response.sendRedirect("/register.html");
        }

    }



}
