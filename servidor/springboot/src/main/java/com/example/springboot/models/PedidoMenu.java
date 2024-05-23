package com.example.springboot.models;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "GER_PEDIDO_MENU")

public class PedidoMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPedidoMenu;
    private UUID idPedido;
    private UUID idMenu;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UUID getIdPedidoMenu() {
        return idPedidoMenu;
    }

    public void setIdPedidoMenu(UUID idPedidoMenu) {
        this.idPedidoMenu = idPedidoMenu;
    }

    public UUID getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(UUID idPedido) {
        this.idPedido = idPedido;
    }

    public UUID getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(UUID idMenu) {
        this.idMenu = idMenu;
    }

}