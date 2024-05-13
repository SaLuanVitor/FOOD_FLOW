package com.example.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // Retorna o nome do template Thymeleaf/Freemarker
    }

    @GetMapping("/Login")
    public String Login() {
        return "index"; // Retorna o nome do template Thymeleaf/Freemarker
    }
}
