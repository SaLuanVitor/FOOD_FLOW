package com.example.springboot.dto;

import jakarta.validation.constraints.NotNull;

public record MenuRecordsDto(@NotNull String descricao, @NotNull String titulo, @NotNull Double preco, @NotNull Boolean ativo) {

}