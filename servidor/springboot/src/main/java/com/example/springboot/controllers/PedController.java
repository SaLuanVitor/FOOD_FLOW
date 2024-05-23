package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.PedRecordsDto;
import com.example.springboot.models.Pedido;
import com.example.springboot.repositories.PedRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class PedController {

    @Autowired
    PedRepository pedRepository;

    // POST PARA CRIAR Pedido
    @PostMapping("/Pedido")
    public ResponseEntity<Pedido> savePed(@RequestBody @Valid PedRecordsDto PedRecordsDto) {

        var Pedido = new Pedido();

        BeanUtils.copyProperties(PedRecordsDto, Pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedRepository.save(Pedido));
    }

    // GET PARA OBTER TODOS OS Pedidos
    @GetMapping("/Pedido")
    public ResponseEntity<List<Pedido>> getAllPedido() {
        return ResponseEntity.status(HttpStatus.OK).body(pedRepository.findAll());
    }

    // GET PARA OBTER UM ÚNICO Pedido

    @GetMapping("Pedido/{id}")
    public ResponseEntity<Object> getOnePedido(@PathVariable(value = "id") UUID id) {
        Optional<Pedido> pedido0 = pedRepository.findById(id);
        if (pedido0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedido0.get());
    }

    // PUT PARA ALTERAR DADOS

    @PutMapping("Pedido/{id}")
    public ResponseEntity<Object> updatePedido(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid PedRecordsDto pedRecords) {
        Optional<Pedido> pedido0 = pedRepository.findById(id);
        if (pedido0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        var pedido = pedido0.get();

        BeanUtils.copyProperties(pedRecords, pedido);
        return ResponseEntity.status(HttpStatus.OK).body(pedRepository.save(pedido));
    }

    // DELETE PARA DELETAR Pedido

    @DeleteMapping("Pedido/{id}")
    public ResponseEntity<Object> deletePedido(@PathVariable(value = "id") UUID id) {
        Optional<Pedido> pedido0 = pedRepository.findById(id);
        if (pedido0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        pedRepository.delete(pedido0.get());

        return ResponseEntity.status(HttpStatus.OK).body("Pedido deletado com sucesso!");

    }
}