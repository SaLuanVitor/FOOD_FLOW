package com.example.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Suporte {

    @GetMapping("/suporte")
    public String suporte() {
        return "suporte";
    }
    // session.setAttribute("logado", nome);
}
