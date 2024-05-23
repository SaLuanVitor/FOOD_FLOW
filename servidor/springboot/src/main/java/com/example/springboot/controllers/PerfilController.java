package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.PerfRecordsDto;
import com.example.springboot.models.Perfil;
import com.example.springboot.repositories.PerfRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class PerfilController {

    @Autowired
    PerfRepository perfRepository;

    // POST PARA CRIAR Perfil
    @PostMapping("/Perfil")
    public ResponseEntity<Perfil> savePerf(@RequestBody @Valid PerfRecordsDto PerfRecordsDto) {

        var Perfil = new Perfil();

        BeanUtils.copyProperties(PerfRecordsDto, Perfil);

        return ResponseEntity.status(HttpStatus.CREATED).body(perfRepository.save(Perfil));
    }

    // GET PARA OBTER TODOS OS PerfilS
    @GetMapping("/Perfil")
    public ResponseEntity<List<Perfil>> getAllPerfil() {
        return ResponseEntity.status(HttpStatus.OK).body(perfRepository.findAll());
    }

    // GET PARA OBTER UM ÚNICO Perfil

    @GetMapping("Perfil/{id}")
    public ResponseEntity<Object> getOnePerfil(@PathVariable(value = "id") UUID id) {
        Optional<Perfil> perfil0 = perfRepository.findById(id);
        if (perfil0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(perfil0.get());
    }

    // PUT PARA ALTERAR DADOS

    @PutMapping("Perfil/{id}")
    public ResponseEntity<Object> updatePerfil(@PathVariable(value = "id") UUID id,
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
    public ResponseEntity<Object> deletePerfil(@PathVariable(value = "id") UUID id) {
        Optional<Perfil> perfil0 = perfRepository.findById(id);
        if (perfil0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado");
        }

        perfRepository.delete(perfil0.get());

        return ResponseEntity.status(HttpStatus.OK).body("Perfil deletado com sucesso!");

    }
}
