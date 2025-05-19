package com.example.proyectofinalgs.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        int statusCode = (int) request.getAttribute("jakarta.servlet.error.status_code");
        String errorMessage = (String) request.getAttribute("jakarta.servlet.error.message");

        model.addAttribute("status", statusCode);
        model.addAttribute("message", errorMessage != null ? errorMessage : "Error inesperado");

        return "error"; // devuelve una vista llamada error.html o error.jsp en /templates o /WEB-INF
    }

}
