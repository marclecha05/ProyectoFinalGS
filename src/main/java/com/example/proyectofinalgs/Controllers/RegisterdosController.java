package com.example.proyectofinalgs.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class RegisterdosController {
    @GetMapping("/registerdos")
    public String mostrarSegundaEtapaRegistro() {
        return "registerdos"; // Devuelve la vista del segundo formulario
    }

    @PostMapping()
    public String procesarSegundaEtapa(@RequestParam String respuesta) {
        // Procesa la respuesta
        return "redirect:/registertres";
    }
}
