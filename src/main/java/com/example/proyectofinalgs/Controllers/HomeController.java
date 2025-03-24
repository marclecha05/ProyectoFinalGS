package com.example.proyectofinalgs.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Home"); // Agregar el título a la vista
        return "home"; // Nombre del archivo HTML en templates
    }
}

