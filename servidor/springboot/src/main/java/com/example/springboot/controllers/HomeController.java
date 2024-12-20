package com.example.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    FuncController funcionarioController;

    @GetMapping("/home")
    public String home() {
        return "home"; // Correspondendo ao nome do arquivo home.html
    }

    @GetMapping("/ajuda")
    public String ajuda() {
        return "ajuda"; // Correspondendo ao nome do arquivo home.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Correspondendo ao nome do arquivo login.html
    }
}
