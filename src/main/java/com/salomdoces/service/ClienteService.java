package com.salomdoces.service;

import com.salomdoces.model.Cliente;
import com.salomdoces.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// Tornando esta classe um Service
@Service
public class ClienteService {

    // Injetando o repositório via Spring
    // Será usado como se fosse um objeto
    @Autowired
    private ClienteRepository clienteRepository;

    // Antes de cadastrar usuário, criptografar a senha recebida
    public Cliente cadastrarUsuario(Cliente usuario) {
        // Trabalhando com a senha encriptada (encodada)
        // Criando um encoder para criptografar a senha
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // Recuperando e encriptando a senha do usuário
        String senhaCriptografada = encoder.encode(usuario.getSenha());
        // Gravando a senha encriptada
        usuario.setSenha(senhaCriptografada);
        // Retornando o usuário com a senha encriptada
        return clienteRepository.save(usuario);
    }
}
