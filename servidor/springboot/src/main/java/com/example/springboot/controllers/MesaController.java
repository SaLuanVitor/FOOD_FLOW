package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springboot.models.Mesa;
import com.example.springboot.repositories.MesaRepository;

@Controller
public class MesaController {

    @Autowired
    MesaRepository mesaRepository;

    @GetMapping("/mesas")
    public String getAllMesasPage(final Model model) {
        List<Mesa> mesas =  mesaRepository.findAll();
        model.addAttribute("mesas",mesas);
        return "mesas/mesas";
    }

     @GetMapping("/mesas/adicionar")
    public String addNewMesa(Model model) {
        Mesa mesa = new Mesa();
        model.addAttribute("mesa", mesa);
        return "mesas/adicionar";
    }
 
    @PostMapping("/mesas/salvar")
    public String saveMesa(@ModelAttribute("mesa") Mesa mesa) {
        mesaRepository.save(mesa);
        return "redirect:/mesas";
    }

     @GetMapping("/mesas/atualizar/{id}")
    public String updateMesa(@PathVariable(value = "id") Long id, Model model) {
        Mesa mesa = mesaRepository.findById(id).get();
        model.addAttribute("mesa", mesa);
        return "mesas/atualizar";
    }
 
    @GetMapping("/mesas/excluir/{id}")
    public String deleteMesaById(@PathVariable(value = "id") Long id) {
        mesaRepository.deleteById(id);
        return "redirect:/mesas";
 
    }

    


   
}
