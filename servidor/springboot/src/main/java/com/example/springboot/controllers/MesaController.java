package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;

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

    private final MesaRepository mesaRepository;

    // Injeção de dependência via construtor
    public MesaController(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @GetMapping("/mesas")
    public String getAllMesasPage(final Model model) {
        List<Mesa> mesas = mesaRepository.findAll();
        model.addAttribute("mesas", mesas);
        return "mesas/mesas";
    }

    @GetMapping("/mesas/adicionar")
    public String addNewMesa(Model model) {
        model.addAttribute("mesa", new Mesa());
        return "mesas/adicionar";
    }

    @PostMapping("/mesas/salvar")
    public String saveMesa(@ModelAttribute("mesas") Mesa mesas) {
        mesaRepository.save(mesas);
        return "redirect:/mesas";
    }

    @GetMapping("/mesas/atualizar/{id}")
    public String updateMesa(@PathVariable(value = "id") Long id, Model model) {
        Optional<Mesa> mesaOpt = mesaRepository.findById(id);
        if (mesaOpt.isPresent()) {
            model.addAttribute("mesa", mesaOpt.get());
            return "mesas/atualizar";
        } else {
            model.addAttribute("message", "Mesa não encontrada.");
            return "redirect:/mesas";
        }
    }

    @GetMapping("/mesas/excluir/{id}")
    public String deleteMesaById(@PathVariable(value = "id") Long id, Model model) {
        if (mesaRepository.existsById(id)) {
            mesaRepository.deleteById(id);
            model.addAttribute("message", "Mesa excluída com sucesso!");
        } else {
            model.addAttribute("message", "Mesa não encontrada.");
        }
        return "redirect:/mesas";
    }
}
