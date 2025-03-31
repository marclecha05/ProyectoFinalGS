package com.example.proyectofinalgs.Controllers;

import com.example.proyectofinalgs.Entities.Usuario;
import com.example.proyectofinalgs.Repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class RegistertresController {
    private UserRepository userRepository; // Inyecta el repositorio de usuarios
    @GetMapping("/registertres")
    public String mostrarTerceraEtapaRegistro() {
        return "registertres"; // Devuelve la vista del tercer formulario
    }
    @PostMapping("")
    public String procesarTerceraEtapa(@RequestParam String respuesta, Authentication authentication) {
        // Procesa la respuesta
        String email = authentication.getName();
        Usuario usuario = userRepository.findByEmail(email);
        String sitioWeb = "";
        if (usuario.getRol().equals("ROLE_PROVEEDOR_AMBOS")) {
            sitioWeb = "homeAmbos";
        } else if (usuario.getRol().equals("ROLE_PROVEEDOR_LABORAL")) {
            sitioWeb="calendarioProveedor";
        }
        return "redirect:/" + sitioWeb;
    }
}
