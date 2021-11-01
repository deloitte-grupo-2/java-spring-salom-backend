package com.salomdoces.controller;

import com.salomdoces.model.Cliente;
import com.salomdoces.model.Pedido;
import com.salomdoces.repository.PedidoRepository;
import com.salomdoces.service.PedidoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Api(value="Pedido Endpoint", tags={"Pedido Endpoint"})
@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;


    @GetMapping("/consultar")
    public ResponseEntity<List<Pedido>> consultarTodos() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pedidoRepository.findAll());
    }

    @PostMapping("/criar")
    public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.criarPedido(pedido));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Pedido> atualizarPeloId(@PathVariable("id") Long id, @RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pedidoService.atualizarPedido(id, pedido));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Pedido> deletarPeloId(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pedidoService.deletarPedido(id));
    }
}
