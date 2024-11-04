package com.example.springboot.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.Funcionarios;

@Repository
public interface FuncRepository extends JpaRepository<Funcionarios, Long> {
    @Query("SELECT f FROM Funcionarios f WHERE f.nome = :nome")
    Optional<Funcionarios> findByNome(String nome);

    Optional<Funcionarios> findByNomeAndSenha(String nome, String senha);
}
