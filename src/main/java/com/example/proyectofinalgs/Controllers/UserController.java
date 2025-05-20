package com.example.proyectofinalgs.Controllers;


import com.example.proyectofinalgs.Entities.Usuario;
import com.example.proyectofinalgs.Services.EmailService;
import com.example.proyectofinalgs.Services.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }
    @GetMapping("/users")
    List<Usuario> all() {
        return userService.findAll();
    }

    @GetMapping("/user")
    List<String> info() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> userInfo;
        if (authentication.getPrincipal() instanceof OAuth2User) {
            Usuario usuario = userService.findByEmail(((OAuth2User) authentication.getPrincipal()).getAttribute("email"));
            userInfo = new ArrayList<>(Arrays.asList(usuario.getUsername(), usuario.getEmail()));
        }else {
            userInfo = new ArrayList<>(Arrays.asList(userService.findByUsername(authentication.getName()).getUsername(), userService.findByUsername(authentication.getName()).getEmail()));
        }
        return userInfo;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        Usuario usuario = userService.findByEmail(email);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }

        boolean isPasswordValid = userService.validatePassword(password, usuario.getPassword());
        if (!isPasswordValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Login exitoso");
    }
    @GetMapping("/delete")
    public void deleteUser(HttpServletResponse response) throws IOException, MessagingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario loggedUsuario;

        if (authentication.getPrincipal() instanceof OAuth2User) {
            loggedUsuario = userService.findByEmail(((OAuth2User) authentication.getPrincipal()).getAttribute("email"));
        } else {
            loggedUsuario = userService.findByUsername(authentication.getName());
        }

        emailService.enviarCorreo(loggedUsuario.getEmail(), "¡Cuenta eliminada!", "Su cuenta ha sido eliminada con éxito.");
        userService.deleteById(loggedUsuario.getId());

        response.sendRedirect("/logout");
    }
}

