package com.salomdoces.controller;

import com.salomdoces.model.Pedido;
import com.salomdoces.repository.PedidoRepository;
import com.salomdoces.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*",allowedHeaders = "*")

public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;


    @PostMapping("/criar")
    public ResponseEntity<Pedido> post(@RequestBody Pedido pedido){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                pedidoRepository.save(pedido)
        );
    }
}
