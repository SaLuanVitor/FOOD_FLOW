package com.example.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // Correspondendo ao nome do arquivo index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Correspondendo ao nome do arquivo login.html
    }
}
