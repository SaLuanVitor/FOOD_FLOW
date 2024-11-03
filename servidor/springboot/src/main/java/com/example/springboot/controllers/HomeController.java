package com.example.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springboot.models.Funcionarios;

@Controller
public class HomeController {

    @Autowired
    FuncController funcionarioController;

    @GetMapping("/home")
    public String home() {
        return "home"; // Correspondendo ao nome do arquivo home.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Correspondendo ao nome do arquivo login.html
    }
}
