package com.example.proyectofinalgs.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class HomeController {





    @GetMapping("/calendarioempresa")
    public String calendarioProveedor(Model model) {
        model.addAttribute("title", "Calendario Proveedor Laboral");
        return "calendarioProveedor";
    }
}
