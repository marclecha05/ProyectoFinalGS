package com.example.proyectofinalgs.Controllers;

import com.example.proyectofinalgs.Entities.User;
import com.example.proyectofinalgs.Repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String mostrarFormulario() {
        return "register"; // Devuelve la vista del formulario de registro
    }

    @PostMapping
    public String procesarRespuestas(@RequestParam String respuesta1,
                                     @RequestParam(required = false) String respuesta2,
                                     Authentication authentication) {

        String email = authentication.getName();
        User user = userRepository.findByEmail(email);

        if (user != null) {
            if ("usuario".equals(respuesta1)) {
                user.setRol("ROLE_USUARIO");
                userRepository.save(user);
                return "redirect:/home";
            } else if ("proveedor".equals(respuesta1)) {
                if ("ambos".equals(respuesta2)) {
                    user.setRol("ROLE_PROVEEDOR_AMBOS");
                    userRepository.save(user);
                    return "redirect:/registerdos";
                } else if ("laboral".equals(respuesta2)) {
                    user.setRol("ROLE_PROVEEDOR_LABORAL");
                    userRepository.save(user);
                    return "redirect:/registerdos";
                }

            }
        }

        return "redirect:/register";
    }



}
