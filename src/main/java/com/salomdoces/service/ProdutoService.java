package com.salomdoces.service;

import com.salomdoces.model.Produto;
import com.salomdoces.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    public Produto cadastrarProduto(Produto produto){
        return this.produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Long id, Produto produto) {
        Optional<Produto> produtoAtualizadoOptional = this.produtoRepository.findById(id);
        if (!produtoAtualizadoOptional.isPresent()) {
            return null;
        }
        Produto atualizandoProduto = produtoAtualizadoOptional.get();
        if (produto.getNome() != null) {
            atualizandoProduto.setNome(produto.getNome());
        }
        if (produto.getPreco() != null) {
            atualizandoProduto.setPreco(produto.getPreco());
        }
        if (produto.getDescricao() != null) {
            atualizandoProduto.setDescricao(produto.getDescricao());
        }
        if (produto.getImagemUrl() != null) {
            atualizandoProduto.setImagemUrl(produto.getImagemUrl());
        }
        Produto produtoAtualizado = this.produtoRepository.save(atualizandoProduto);
        return produtoAtualizado;
    }

    public Produto deletarProduto(Long id) {
        Optional<Produto> produtoDeletadoOptional = this.produtoRepository.findById(id);
        if (!produtoDeletadoOptional.isPresent()) {
            return null;
        }
        Produto produtoDeletado = produtoDeletadoOptional.get();
        this.produtoRepository.delete(produtoDeletado);
        return produtoDeletado;
    }
    
}
