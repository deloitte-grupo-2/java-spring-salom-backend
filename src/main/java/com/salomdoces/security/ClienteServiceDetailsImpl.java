package com.salomdoces.security;

import com.salomdoces.model.Cliente;
import com.salomdoces.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Configurar a classe como um Service
// Classe implementará UserDetailsService para tratar pesquisa de Usuário
@Service
public class ClienteServiceDetailsImpl implements UserDetailsService {

    // Injetando o repositório via Spring
    // Será usado como se fosse um objeto
    @Autowired
    private ClienteRepository clienteRepository;

    // Sobreescrevendo o método loadUserByUsername de UserDetails
    // Retornando os dados do usuário, caso encontrado
    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<Cliente> user = clienteRepository.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException(email + " não encontrado."));
        return user.map(ClienteDetailsImpl::new).get();
    }
}
