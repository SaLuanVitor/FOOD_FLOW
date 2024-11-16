package com.example.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.HistoricoPedido;

@Repository
public interface HistoricoPedidoRepository extends JpaRepository<HistoricoPedido, Long> {

    @Query("SELECT h FROM HistoricoPedido h WHERE h.pedido.idPedido = :idPedido")
    List<HistoricoPedido> findByPedidoId(@Param("idPedido") Long idPedido);
}
