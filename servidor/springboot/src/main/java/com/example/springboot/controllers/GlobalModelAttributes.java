package com.example.springboot.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute
    public void addGlobalAttributes(HttpSession session, Model model) {
        Object logado = session.getAttribute("logado");
        model.addAttribute("logado", logado);

        Object mesa = session.getAttribute("mesa");
        model.addAttribute("mesa", mesa);
    }

}
