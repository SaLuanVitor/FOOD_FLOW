package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.MenuRecordsDto;
import com.example.springboot.models.Menu;
import com.example.springboot.repositories.MenuRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class MenuController {

    @Autowired
    MenuRepository MenuRepository;

    // POST PARA CRIAR Menuionário
    @PostMapping("/Menu")
    public ResponseEntity<Menu> saveMenu(@RequestBody @Valid MenuRecordsDto MenuRecordsDto) {

        var Menu = new Menu();

        BeanUtils.copyProperties(MenuRecordsDto, Menu);

        return ResponseEntity.status(HttpStatus.CREATED).body(MenuRepository.save(Menu));
    }

    // GET PARA OBTER TODOS OS MenuionárioS
    @GetMapping("/Menu")
    public ResponseEntity<List<Menu>> getAllMenus() {
        return ResponseEntity.status(HttpStatus.OK).body(MenuRepository.findAll());
    }

    // GET PARA OBTER UM ÚNICO Menuionário

    @GetMapping("Menu/{id}")
    public ResponseEntity<Object> getOneMenu(@PathVariable(value = "id") UUID id) {
        Optional<Menu> menu0 = MenuRepository.findById(id);
        if (menu0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(menu0.get());
    }

    // PUT PARA ALTERAR DADOS

    @PutMapping("Menu/{id}")
    public ResponseEntity<Object> updateMenuionario(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid MenuRecordsDto menuRecords) {
        Optional<Menu> menu0 = MenuRepository.findById(id);
        if (menu0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menuionário não encontrado");
        }

        var menu = menu0.get();

        BeanUtils.copyProperties(menuRecords, menu);
        return ResponseEntity.status(HttpStatus.OK).body(MenuRepository.save(menu));
    }

    // DELETE PARA DELETAR Menuionarios

    @DeleteMapping("Menu/{id}")
    public ResponseEntity<Object> deleteMenu(@PathVariable(value = "id") UUID id) {
        Optional<Menu> menu0 = MenuRepository.findById(id);
        if (menu0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu não encontrado");
        }

        MenuRepository.delete(menu0.get());

        return ResponseEntity.status(HttpStatus.OK).body("Menu deletado com sucesso!");

    }
}
