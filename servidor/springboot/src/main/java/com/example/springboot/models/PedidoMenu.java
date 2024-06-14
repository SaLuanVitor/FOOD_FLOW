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
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedidoMenu;
    private Long idPedido;
    private Long idMenu;

    public static Long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getIdPedidoMenu() {
        return idPedidoMenu;
    }

    public void setIdPedidoMenu(Long idPedidoMenu) {
        this.idPedidoMenu = idPedidoMenu;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

}