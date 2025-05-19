package com.example.proyectofinalgs.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class HomeController {

        @GetMapping("/home")
        public void home(Model model, HttpServletResponse response) throws IOException {
            model.addAttribute("title", "Home Usuario");
            response.sendRedirect("/home.html");
        }


    }

