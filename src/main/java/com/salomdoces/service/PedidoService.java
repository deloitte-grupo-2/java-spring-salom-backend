package com.salomdoces.service;

import com.salomdoces.model.Pedido;
import com.salomdoces.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(Pedido pedido){

        return pedidoRepository.save(pedido);

    }

}
