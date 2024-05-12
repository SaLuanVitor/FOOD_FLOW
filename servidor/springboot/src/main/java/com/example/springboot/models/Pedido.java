package com.example.springboot.models;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "GER_PEDIDO")

public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMesa;
    private UUID idFuncionario;
    private String Status;
    private DateTime tempo Preparo;

 
