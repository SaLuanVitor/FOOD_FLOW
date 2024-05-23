package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.MesaRecordsDto;
import com.example.springboot.models.Mesa;
import com.example.springboot.repositories.MesaRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class MesaController {

    @Autowired
    MesaRepository MesaRepository;

    // POST PARA CRIAR Mesa
    @PostMapping("/Mesa")
    public ResponseEntity<Mesa> saveMesa(@RequestBody @Valid MesaRecordsDto MesaRecordsDto) {

        var Mesa = new Mesa();

        BeanUtils.copyProperties(MesaRecordsDto, Mesa);

        return ResponseEntity.status(HttpStatus.CREATED).body(MesaRepository.save(Mesa));
    }

    // GET PARA OBTER TODOS OS MesaS
    @GetMapping("/Mesa")
    public ResponseEntity<List<Mesa>> getAllMesa() {
        return ResponseEntity.status(HttpStatus.OK).body(MesaRepository.findAll());
    }

    // GET PARA OBTER UM ÚNICO Mesa

    @GetMapping("Mesa/{id}")
    public ResponseEntity<Object> getOneMesa(@PathVariable(value = "id") UUID id) {
        Optional<Mesa> Mesa0 = MesaRepository.findById(id);
        if (Mesa0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mesa não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(Mesa0.get());
    }

    // PUT PARA ALTERAR DADOS

    @PutMapping("Mesa/{id}")
    public ResponseEntity<Object> updateMesa(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid MesaRecordsDto MesaiMesaRecords) {
        Optional<Mesa> Mesa0 = MesaRepository.findById(id);
        if (Mesa0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mesa não encontrado");
        }

        var Mesa = Mesa0.get();

        BeanUtils.copyProperties(MesaiMesaRecords, Mesa);
        return ResponseEntity.status(HttpStatus.OK).body(MesaRepository.save(Mesa));
    }

    // DELETE PARA DELETAR Mesa

    @DeleteMapping("Mesa/{id}")
    public ResponseEntity<Object> deleteMesa(@PathVariable(value = "id") UUID id) {
        Optional<Mesa> Mesa0 = MesaRepository.findById(id);
        if (Mesa0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mesa não encontrado");
        }

        MesaRepository.delete(Mesa0.get());

        return ResponseEntity.status(HttpStatus.OK).body("Mesa deletado com sucesso!");

    }
}
