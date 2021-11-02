package com.salomdoces.service;

import com.salomdoces.model.Pedido;
import com.salomdoces.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(Pedido pedido){
        return this.pedidoRepository.save(pedido);
    }

    public Pedido atualizarPedido(Long id, Pedido pedido) {
        Optional<Pedido> pedidoAtualizadoOptional = this.pedidoRepository.findById(id);
        if (!pedidoAtualizadoOptional.isPresent()) {
            return null;
        }
        Pedido atualizandoPedido = pedidoAtualizadoOptional.get();
        if (pedido.getFormaPagamento() != null) {
            atualizandoPedido.setFormaPagamento(pedido.getFormaPagamento());
        }
        if (pedido.getStatus() != null) {
            atualizandoPedido.setStatus(pedido.getStatus());
        }
        if (pedido.getDataEncomenda() != null) {
            atualizandoPedido.setDataEncomenda(pedido.getDataEncomenda());
        }
        if (pedido.getDataEntrega() != null) {
            atualizandoPedido.setDataEntrega(pedido.getDataEntrega());
        }
        if(pedido.getItens() != null){
            atualizandoPedido.setItens(pedido.getItens());
        }
        if(pedido.getPrecoTotal() != null){
            atualizandoPedido.setPrecoTotal(pedido.getPrecoTotal());
        }

        Pedido pedidoAtualizado = this.pedidoRepository.save(atualizandoPedido);
        return pedidoAtualizado;
    }

    public Pedido deletarPedido(Long id) {
        Optional<Pedido> pedidoDeletadoOptional = this.pedidoRepository.findById(id);
        if (!pedidoDeletadoOptional.isPresent()) {
            return null;
        }
        Pedido pedidoDeletado = pedidoDeletadoOptional.get();
        this.pedidoRepository.delete(pedidoDeletado);
        return pedidoDeletado;
    }
}
