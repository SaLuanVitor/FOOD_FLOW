package com.example.springboot.models;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "GER_PEDIDO")

public class Pedido implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMesa")
    private Mesa mesa;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idFuncionario")
    private Funcionarios funcionario;
    private String Status;
    private LocalTime tempoPreparo;

    @ManyToMany
    @JoinTable(
            name = "pedido_item_menu",
            joinColumns = @JoinColumn(name = "idPedido"),
            inverseJoinColumns = @JoinColumn(name = "idMenu"))
    Set<Menu> itensMenu;

    public static Long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Funcionarios getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionarios funcionario) {
        this.funcionario = funcionario;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public LocalTime getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(LocalTime tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public Set<Menu> getItensMenu() {
        return itensMenu;
    }

    public void setItensMenu(Set<Menu> itensMenu) {
        this.itensMenu = itensMenu;
    }

    

}
