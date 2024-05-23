package com.example.springboot.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record PedidoMenuRecordsDto(@NotNull UUID idMenu, @NotNull UUID idPedido) {

}
