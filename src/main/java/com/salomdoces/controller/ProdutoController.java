package com.salomdoces.controller;

import com.salomdoces.model.Produto;
import com.salomdoces.repository.ProdutoRepository;
import com.salomdoces.service.ProdutoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value="Produto Endpoint", tags={"Produto Endpoint"})
@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/consultar")
    public ResponseEntity<List<Produto>> consultarTodos() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/consultar/nome/{nome}")
    public ResponseEntity<Produto> consultarPeloNome(@PathVariable String nome) {
        return produtoRepository.findByNome(nome).map(resp ->
                ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/criar")
    public ResponseEntity<Produto> criar(@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoService.cadastrarProduto(produto));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Produto> atualizarPeloId(@PathVariable("id") Long id, @RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(produtoService.atualizarProduto(id, produto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Produto> deletarPeloId(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(produtoService.deletarProduto(id));
    }
}
