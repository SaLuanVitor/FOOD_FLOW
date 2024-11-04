package com.example.springboot.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.Mesa;
import com.example.springboot.models.Pedido;

@Repository
public interface PedRepository extends JpaRepository<Pedido, Long> {

    // List<Pedido> findByMesa(Integer funcaoInt);

}