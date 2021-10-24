package com.salomdoces.repository;

import com.salomdoces.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Definindo a interface como um Repository
@Repository
// Extendendo JPARepository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    // Sobreescrevendo método findBy para pesquisar usuário (e-mail do cliente)
    public Optional<Cliente> findByEmail(String usuario);

}
