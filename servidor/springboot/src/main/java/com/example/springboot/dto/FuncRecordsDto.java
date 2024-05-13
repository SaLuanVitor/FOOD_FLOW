package com.example.springboot.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncRecordsDto(@NotNull @NotBlank String nome, @NotNull @NotBlank String senha,
        String funcao,
        String email,
        @NotNull UUID idPefil) {

}
