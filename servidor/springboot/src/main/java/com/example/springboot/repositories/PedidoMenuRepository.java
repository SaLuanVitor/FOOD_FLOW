package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.PedidoMenu;

@Repository
public interface PedidoMenuRepository extends JpaRepository<PedidoMenu, Long> {

}