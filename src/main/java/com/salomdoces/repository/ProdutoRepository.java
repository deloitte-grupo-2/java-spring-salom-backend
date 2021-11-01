package com.salomdoces.repository;

import com.salomdoces.model.Cliente;
import com.salomdoces.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Optional<Produto> findByNome(String nome);

}
