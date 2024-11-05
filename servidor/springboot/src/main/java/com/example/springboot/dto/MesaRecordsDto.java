package com.example.springboot.dto;

import jakarta.validation.constraints.NotNull;

public record MesaRecordsDto(@NotNull int numeroMesa, @NotNull int capacidade) {

}
