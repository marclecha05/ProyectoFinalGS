package com.example.proyectofinalgs.Controllers;

import com.example.proyectofinalgs.Entities.Cliente;
import com.example.proyectofinalgs.Entities.Usuario;
import com.example.proyectofinalgs.Repositories.ClienteRepository;
import com.example.proyectofinalgs.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class RegisterController {

    private final UserRepository userRepository;
    private final ClienteRepository clienteRepository;

    public RegisterController(UserRepository userRepository, ClienteRepository clienteRepository) {
        this.userRepository = userRepository;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/registerForm")
    public void procesarRespuestas(@RequestParam String respuesta1,
                                   @RequestParam(required = false) String respuesta2, HttpServletResponse response) throws IOException {
        System.out.println("juan gilipollas");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email=((OAuth2User) authentication.getPrincipal()).getAttribute("email");
        Usuario usuario = userRepository.findByEmail(email);

        if (usuario != null) {
            if ("usuario".equals(respuesta1)) {
                Cliente cliente = new Cliente();
                cliente.setUsuario(usuario); // Asociar el usuario al cliente
                cliente.setNombre(usuario.getUsername()); // Usar el nombre del usuario como nombre del cliente

                // Asignar el rol de cliente al usuario
                usuario.setRol("CLIENTE");

                // Guardar el cliente y el usuario en la base de datos
                clienteRepository.save(cliente);
                userRepository.save(usuario);

                // Redirigir al home para usuarios
                response.sendRedirect("/home.html");
            } else if ("proveedor".equals(respuesta1)) {
                usuario.setRol("PROVEEDOR");
                userRepository.save(usuario);
                response.sendRedirect("/registerdos.html");
            }
        } else {
            response.sendRedirect("/register.html");
        }

    }

}