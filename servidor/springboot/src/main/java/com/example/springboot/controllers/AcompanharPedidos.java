package com.example.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcompanharPedidos {

    @GetMapping("/acompanharPedidos")
    public String acompanharPedidos() {
        return "acompanharPedidos/acompanharPedidos";
    }
}
