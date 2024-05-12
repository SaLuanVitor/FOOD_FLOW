package com.example.springboot.models;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "GER_MENU")

public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMenu;
    private String titulo;
    private String descricao;
    private Double preco;
    private Boolean ativo;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

   }