package com.example.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDto(
        @NotNull @NotBlank String nome,
        @NotNull @NotBlank String senha) {
}
