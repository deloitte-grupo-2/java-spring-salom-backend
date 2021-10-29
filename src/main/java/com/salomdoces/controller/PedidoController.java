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


@Api(value="Pedido Endpoint", tags={"Pedido Endpoint"})
@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*",allowedHeaders = "*")

public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;


//    @GetMapping("/consultar")
//    public ResponseEntity<List<Pedido>> getAll() {
//        return ResponseEntity.ok(pedidoRepository.findAll());
//    }


    @PostMapping("/criar")
    public ResponseEntity<Pedido> post(@RequestBody Pedido pedido){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.criarPedido(pedido));
    }
}
