package com.example.springboot.dto;

import java.time.LocalTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedRecordsDto(@NotNull @NotBlank String Status, @NotNull @NotBlank LocalTime tempoPreparo,
        @NotNull @NotBlank UUID idMesa,
        @NotNull UUID idFuncionario) {

}