package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.UserRecordsDto;
import com.example.springboot.models.Usuarios;
import com.example.springboot.repositories.UserRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    // POST PARA CRIAR USUÁRIO
    @PostMapping("/usuario")
    public ResponseEntity<Usuarios> saveUser(@RequestBody @Valid UserRecordsDto userRecordsDto) {

        var usuarios = new Usuarios();

        BeanUtils.copyProperties(userRecordsDto, usuarios);

        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(usuarios));
    }

    // GET PARA OBTER TODOS OS USUÁRIOS
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuarios>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    // GET PARA OBTER UM ÚNICO USUÁRIO

    @GetMapping("usuario/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id) {
        Optional<Usuarios> usuario0 = userRepository.findById(id);
        if (usuario0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuario0.get());
    }

    // PUT PARA ALTERAR DADOS

    @PutMapping("usuario/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid UserRecordsDto usuarioRecords) {
        Optional<Usuarios> usuario0 = userRepository.findById(id);
        if (usuario0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        var usuario = usuario0.get();

        BeanUtils.copyProperties(usuarioRecords, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(usuario));
    }

    // DELETE PARA DELETAR USUARIOS

    @DeleteMapping("usuario/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") UUID id) {
        Optional<Usuarios> usuario0 = userRepository.findById(id);
        if (usuario0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        userRepository.delete(usuario0.get());

        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso!");

    }
}
