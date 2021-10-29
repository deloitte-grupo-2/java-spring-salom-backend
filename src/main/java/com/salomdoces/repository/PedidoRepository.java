package com.salomdoces.repository;

import com.salomdoces.model.Cliente;
import com.salomdoces.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

//    public Optional<Pedido> findByEmail(String email);


}
