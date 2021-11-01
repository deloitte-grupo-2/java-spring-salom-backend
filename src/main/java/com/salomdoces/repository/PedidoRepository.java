package com.salomdoces.repository;

import com.salomdoces.model.Cliente;
import com.salomdoces.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
