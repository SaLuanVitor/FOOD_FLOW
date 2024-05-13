package com.example.springboot.models;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "GER_PEDIDO")

public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMesa;
    private UUID idFuncionario;
    private String Status;
    private LocalTime tempoPreparo;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UUID getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(UUID idMesa) {
        this.idMesa = idMesa;
    }

    public UUID getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(UUID idFuncionario) {
        this.idFuncionario = idFuncionario;
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

}
