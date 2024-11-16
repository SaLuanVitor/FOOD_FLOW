package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.Pedido;

@Repository
public interface PedRepository extends JpaRepository<Pedido, Long> {

    // List<Pedido> findByMesa(Integer funcaoInt);

}