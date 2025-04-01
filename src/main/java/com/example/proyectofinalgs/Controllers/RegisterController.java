package com.example.proyectofinalgs.Controllers;

import com.example.proyectofinalgs.Entities.Cliente;
import com.example.proyectofinalgs.Entities.Proveedor;
import com.example.proyectofinalgs.Entities.Usuario;
import com.example.proyectofinalgs.Repositories.ClienteRepository;
import com.example.proyectofinalgs.Repositories.ProveedorRepository;
import com.example.proyectofinalgs.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class RegisterController {

    private final UserRepository userRepository;
    private final ClienteRepository clienteRepository;
    private final ProveedorRepository proveedorRepository;

    public RegisterController(UserRepository userRepository, ClienteRepository clienteRepository, ProveedorRepository proveedorRepository) {
        this.userRepository = userRepository;
        this.clienteRepository = clienteRepository;
        this.proveedorRepository = proveedorRepository;
    }

    @PostMapping("/registerForm")
    public void procesarRespuestas(@RequestParam String respuesta1,
                                   @RequestParam(required = false) String respuesta2, HttpServletResponse response, HttpSession session) throws IOException {
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
                Proveedor proveedor = new Proveedor();
                proveedor.setUsuario(usuario); // Asociar el usuario al proveedor
                proveedor.setNombre(usuario.getUsername()); // Usar el nombre del usuario como nombre del proveedor
                usuario.setRol("PROVEEDOR");
                userRepository.save(usuario);
                proveedorRepository.save(proveedor);
                session.setAttribute("proveedorId", proveedor.getId());
                response.sendRedirect("/registerdos.html");
            }
        } else {
            response.sendRedirect("/register.html");
        }

    }

}