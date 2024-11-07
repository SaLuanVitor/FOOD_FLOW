package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springboot.dto.PerfRecordsDto;
import com.example.springboot.models.Mesa;
import com.example.springboot.models.Perfil;
import com.example.springboot.repositories.PerfRepository;

import jakarta.validation.Valid;

@Controller
public class PerfilController {

    @Autowired
    PerfRepository perfRepository;

     @GetMapping("/perfis")
    public String getAllPerfisPage(final Model model) {
        List<Perfil> perfis = perfRepository.findAll();
        model.addAttribute("perfis", perfis);
        return "perfil/perfis";
    }

      @GetMapping("/perfil/adicionar")
    public String addNewMesa(Model model) {
        model.addAttribute("perfil", new Perfil());
        return "perfil/adicionar";
    }

    @PostMapping("/perfil/salvar")
    public String savePerfil(@ModelAttribute("perfil") Perfil perfil) {
        perfRepository.save(perfil);
        return "redirect:/perfis";
    }

    @GetMapping("/perfil/atualizar/{id}")
    public String updateMesa(@PathVariable(value = "id") Long id, Model model) {
        Optional<Perfil> perfilOpt = perfRepository.findById(id);
        if (perfilOpt.isPresent()) {
            model.addAttribute("perfil", perfilOpt.get());
            return "perfil/atualizar";
        } else {
            model.addAttribute("message", "Perfil não encontrado.");
            return "redirect:/perfis";
        }
    }


    @GetMapping("/perfil/excluir/{id}")
    public String deleteMesaById(@PathVariable(value = "id") Long id, Model model) {
        if (perfRepository.existsById(id)) {
            perfRepository.deleteById(id);
            model.addAttribute("message", "Perfil excluído com sucesso!");
        } else {
            model.addAttribute("message", "Perfil não encontrado.");
        }
        return "redirect:/perfis";
    }

    // GET PARA OBTER TODOS OS PerfilS
    @GetMapping("/Perfil")
    public ResponseEntity<List<Perfil>> getAllPerfil() {
        return ResponseEntity.status(HttpStatus.OK).body(perfRepository.findAll());
    }

    // GET PARA OBTER UM ÚNICO Perfil

    @GetMapping("Perfil/{id}")
    public ResponseEntity<Object> getOnePerfil(@PathVariable(value = "id") Long id) {
        Optional<Perfil> perfil0 = perfRepository.findById(id);
        if (perfil0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(perfil0.get());
    }

    // PUT PARA ALTERAR DADOS

    @PutMapping("Perfil/{id}")
    public ResponseEntity<Object> updatePerfil(@PathVariable(value = "id") Long id,
            @RequestBody @Valid PerfRecordsDto perfiPerfRecords) {
        Optional<Perfil> perfil0 = perfRepository.findById(id);
        if (perfil0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado");
        }

        var perfil = perfil0.get();

        BeanUtils.copyProperties(perfiPerfRecords, perfil);
        return ResponseEntity.status(HttpStatus.OK).body(perfRepository.save(perfil));
    }

    // DELETE PARA DELETAR Perfil

    @DeleteMapping("Perfil/{id}")
    public ResponseEntity<Object> deletePerfil(@PathVariable(value = "id") Long id) {
        Optional<Perfil> perfil0 = perfRepository.findById(id);
        if (perfil0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado");
        }

        perfRepository.delete(perfil0.get());

        return ResponseEntity.status(HttpStatus.OK).body("Perfil deletado com sucesso!");

    }
}
