package com.salomdoces.controller;

import com.salomdoces.model.Cliente;
import com.salomdoces.repository.ClienteRepository;
import com.salomdoces.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Definindo a classe como um controller
@RestController
// Mapeando o End Point Usuário
// (cadastrar somente o usuário (email/senha) do Cliente)
@RequestMapping("/usuario")
// Captura qualquer referência: permite acesso externo
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ClienteController {

    // Injetando o service via Spring
    // Será usado como se fosse um objeto
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar")
    // RequestBody: JSON entrando via requisição
    // Parâmetro usuário recebendo nome, e-mail e senha
    public ResponseEntity<Cliente> post(@RequestBody Cliente usuario) {
        // Chamando o Service para cadastrar o usuário
        // Tendo sucesso, retornar resposta HTTP 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.cadastrarUsuario(usuario));
    }
}
