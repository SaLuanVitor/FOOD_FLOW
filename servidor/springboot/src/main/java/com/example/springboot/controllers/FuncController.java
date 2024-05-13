package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.FuncRecordsDto;
import com.example.springboot.dto.LoginDto;
import com.example.springboot.models.Funcionarios;
import com.example.springboot.repositories.FuncRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class FuncController {

    @Autowired
    FuncRepository funcRepository;

    // POST PARA CRIAR Funcionário
    @PostMapping("/funcionarios")
    public ResponseEntity<Funcionarios> saveFunc(@RequestBody @Valid FuncRecordsDto FuncRecordsDto) {

        var Funcionarios = new Funcionarios();

        BeanUtils.copyProperties(FuncRecordsDto, Funcionarios);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcRepository.save(Funcionarios));
    }

    // GET PARA OBTER TODOS OS FuncionárioS
    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionarios>> getAllFuncs() {
        return ResponseEntity.status(HttpStatus.OK).body(funcRepository.findAll());
    }

    // GET PARA OBTER UM ÚNICO Funcionário

    @GetMapping("funcionarios/{id}")
    public ResponseEntity<Object> getOneFunc(@PathVariable(value = "id") UUID id) {
        Optional<Funcionarios> funcioario0 = funcRepository.findById(id);
        if (funcioario0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(funcioario0.get());
    }

    // PUT PARA ALTERAR DADOS

    @PutMapping("funcionarios/{id}")
    public ResponseEntity<Object> updateFuncionario(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid FuncRecordsDto funcioarioRecords) {
        Optional<Funcionarios> funcioario0 = funcRepository.findById(id);
        if (funcioario0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado");
        }

        var funcioario = funcioario0.get();

        BeanUtils.copyProperties(funcioarioRecords, funcioario);
        return ResponseEntity.status(HttpStatus.OK).body(funcRepository.save(funcioario));
    }

    // DELETE PARA DELETAR Funcionarios

    @DeleteMapping("funcionarios/{id}")
    public ResponseEntity<Object> deleteFuncioario(@PathVariable(value = "id") UUID id) {
        Optional<Funcionarios> funcioario0 = funcRepository.findById(id);
        if (funcioario0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado");
        }

        funcRepository.delete(funcioario0.get());

        return ResponseEntity.status(HttpStatus.OK).body("Funcionário deletado com sucesso!");

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDto loginDto) {
        String nome = loginDto.nome();
        String senha = loginDto.senha();

        // Verificar se o nome de usuário e a senha foram fornecidos
        if (nome == null || senha == null || nome.isEmpty() || senha.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome de usuário e senha devem ser fornecidos.");
        }

        // Buscar o funcionário pelo nome de usuário (nome) no banco de dados
        Optional<Funcionarios> funcionarioOpt = funcRepository.findByNome(nome);

        // Verificar se o funcionário foi encontrado
        if (funcionarioOpt.isPresent()) {
            Funcionarios funcionario = funcionarioOpt.get();
            // Verificar se a senha corresponde
            if (senha.equals(funcionario.getSenha())) {
                // Login bem-sucedido
                return ResponseEntity.status(HttpStatus.OK).body("Login bem-sucedido!");
            } else {
                // Senha incorreta
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta!");
            }
        } else {
            // Funcionário não encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
    }
}
