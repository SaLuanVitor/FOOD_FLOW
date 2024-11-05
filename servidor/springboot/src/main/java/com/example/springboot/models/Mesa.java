package com.example.springboot.models;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "GER_MESA")
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMesa;

    @Column(name = "numero_mesa")
    private int numeroMesa;

    @Column(name = "capacidade")
    private int capacidade;

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Long getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }

    @Override
    public String toString() {
        return "Mesa [idMesa=" + idMesa + ", numeroMesa=" + numeroMesa + ", capacidade=" + capacidade + ", disponivel="
                + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Mesa mesa = (Mesa) o;
        return idMesa != null && idMesa.equals(mesa.idMesa);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
