package com.salomdoces.controller;

import com.salomdoces.model.Cliente;
import com.salomdoces.model.ClienteLogin;
import com.salomdoces.repository.ClienteRepository;
import com.salomdoces.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    // Injetando o repository via Spring
    @Autowired
    private ClienteRepository clienteRepository;

    // Usuário: cadastrar
    @PostMapping("/cadastrar")
    // RequestBody: JSON entrando via requisição
    // Parâmetro usuário recebendo nome, e-mail e senha
    public ResponseEntity<Cliente> post(@RequestBody Cliente usuario) {
        // Chamando o Service para cadastrar o usuário
        // Tendo sucesso, retornar resposta HTTP 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.cadastrarUsuario(usuario));
    }

    // Usuário: efetuar login
    @PostMapping("/logar")
    // RequestBody: JSON entrando via requisição
    // Parâmetro usuário recebendo e-mail e senha
    public ResponseEntity<ClienteLogin> authentication(@RequestBody Optional<ClienteLogin> usuario) {
        return clienteService.logar(usuario).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    // Cliente: cadastrar dados para compra
    // Atualizando Usuário
    @PutMapping("/atualizar")
    public ResponseEntity<Cliente> put(@RequestBody Cliente cliente) {
        // Chamando o repository para salvar o Cliente/Usuário editado
        // Informando o status da atualização como OK
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(cliente));
    }
}
