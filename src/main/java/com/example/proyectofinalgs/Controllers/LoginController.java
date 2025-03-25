package com.example.proyectofinalgs.Controllers;

import com.example.proyectofinalgs.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("users", userService.findAll());
        return "login";
    }
}
