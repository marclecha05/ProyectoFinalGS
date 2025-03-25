package com.example.proyectofinalgs.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Home Usuario");
        return "home";
    }

    @GetMapping("/homeAmbos")
    public String homeAmbos(Model model) {
        model.addAttribute("title", "Home Proveedor Ambos");
        return "homeAmbos";
    }

    @GetMapping("/calendarioempresa")
    public String calendarioProveedor(Model model) {
        model.addAttribute("title", "Calendario Proveedor Laboral");
        return "calendarioProveedor";
    }
}
