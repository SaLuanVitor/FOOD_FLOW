package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springboot.models.Menu;
import com.example.springboot.repositories.MenuRepository;

@Controller
public class MenuController {

    @Autowired
    MenuRepository menuRepository;

    @GetMapping("/menu")
    public String getAllMenusPage(final Model model) {
        List<Menu> menus =  menuRepository.findAll();
        model.addAttribute("menus",menus);
        return "menu/menus";
    }

     @GetMapping("/menu/adicionar")
    public String addNewMenu(Model model) {
        Menu menu = new Menu();
        model.addAttribute("menu", menu);
        return "menu/adicionar";
    }
 
    @PostMapping("/menu/salvar")
    public String saveMenu(@ModelAttribute("menu") Menu menu) {
        menuRepository.save(menu);
        return "redirect:/menu";
    }

     @GetMapping("/menu/atualizar/{id}")
    public String updateMenu(@PathVariable(value = "id") Long id, Model model) {
        Menu menu = menuRepository.findById(id).get();
        model.addAttribute("menu", menu);
        return "menu/atualizar";
    }
 
    @GetMapping("/menu/excluir/{id}")
    public String deleteMenuById(@PathVariable(value = "id") Long id) {
        menuRepository.deleteById(id);
        return "redirect:/menu";
 
    }

}
