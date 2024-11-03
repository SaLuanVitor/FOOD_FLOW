package com.example.springboot.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.Perfil;

@Repository
public interface PerfRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByDescricao(String descricao);
}
