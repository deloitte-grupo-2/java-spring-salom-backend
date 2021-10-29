package com.salomdoces.controller;

import com.salomdoces.model.Produto;
import com.salomdoces.repository.ProdutoRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(value="Produto Endpoint", tags={"Produto Endpoint"})
@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/criar")
    public ResponseEntity<Produto> post(@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }
}
