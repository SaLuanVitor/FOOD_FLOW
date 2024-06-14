package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.PedidoMenuRecordsDto;

import com.example.springboot.models.PedidoMenu;
import com.example.springboot.repositories.PedidoMenuRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class PedidoMenuController {

    @Autowired
    PedidoMenuRepository pedidoMenuRepository;

    // POST PARA CRIAR PedidoMenu
    @PostMapping("/PedidoMenu")
    public ResponseEntity<PedidoMenu> savePerf(@RequestBody @Valid PedidoMenuRepository PedidoMenuRecordsDto) {

        var PedidoMenu = new PedidoMenu();

        BeanUtils.copyProperties(PedidoMenuRecordsDto, PedidoMenu);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoMenuRepository.save(PedidoMenu));
    }

    // GET PARA OBTER TODOS OS PedidoMenuS
    @GetMapping("/PedidoMenu")
    public ResponseEntity<List<PedidoMenu>> getAllPedidoMenu() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoMenuRepository.findAll());
    }

    // GET PARA OBTER UM ÚNICO PedidoMenu

    @GetMapping("PedidoMenu/{id}")
    public ResponseEntity<Object> getOnePedidoMenu(@PathVariable(value = "id") Long id) {
        Optional<PedidoMenu> PedidoMenu0 = pedidoMenuRepository.findById(id);
        if (PedidoMenu0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PedidoMenu não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(PedidoMenu0.get());
    }

    // PUT PARA ALTERAR DADOS

    @PutMapping("PedidoMenu/{id}")
    public ResponseEntity<Object> updatePedidoMenu(@PathVariable(value = "id") Long id,
            @RequestBody @Valid PedidoMenuRecordsDto pedidoMenuRecords) {
        Optional<PedidoMenu> PedidoMenu0 = pedidoMenuRepository.findById(id);
        if (PedidoMenu0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PedidoMenu não encontrado");
        }

        var PedidoMenu = PedidoMenu0.get();

        BeanUtils.copyProperties(pedidoMenuRecords, PedidoMenu);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoMenuRepository.save(PedidoMenu));
    }

    // DELETE PARA DELETAR PedidoMenu

    @DeleteMapping("PedidoMenu/{id}")
    public ResponseEntity<Object> deletePedidoMenu(@PathVariable(value = "id") Long id) {
        Optional<PedidoMenu> PedidoMenu0 = pedidoMenuRepository.findById(id);
        if (PedidoMenu0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PedidoMenu não encontrado");
        }

        pedidoMenuRepository.delete(PedidoMenu0.get());

        return ResponseEntity.status(HttpStatus.OK).body("PedidoMenu deletado com sucesso!");

    }
}
