package com.example.springboot.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedRecordsDto(@NotNull @NotBlank String Status, @NotNull @NotBlank DateTime tempoPreparo,
                @NotNull @NotBlank UUID,
                @NotNull UUID idFuncionario) {

}