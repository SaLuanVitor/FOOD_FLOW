package com.example.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordsDto(@NotBlank String nome, @NotNull String senha, @NotBlank String cpf) {

}
