package com.example.springboot.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.Funcionarios;

@Repository
public interface FuncRepository extends JpaRepository<Funcionarios, UUID> {

}
