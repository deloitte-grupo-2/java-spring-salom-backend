package com.salomdoces.controller;

import com.salomdoces.model.Cliente;
import com.salomdoces.model.ClienteLogin;
import com.salomdoces.repository.ClienteRepository;
import com.salomdoces.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;
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
        // Validar o e-mail recebido via JSON
        if(isEmailValido(usuario.getEmail())) {
            // Email informado é válido
            // Pesquisar se o e-mail já está cadastrado
            Optional<Cliente> usuarioCadastrado = clienteRepository.findByEmail(usuario.getEmail());
            if (usuarioCadastrado.isPresent()) {
                // Email já cadastrado. Abortar cadastro.
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            } else {
                // Chamando o Service para cadastrar o usuário
                // Tendo sucesso, retornar resposta HTTP 201 (Created)
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(clienteService.cadastrarUsuario(usuario));
            }
        } // Se e-mail inválido
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
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
        // Validar o e-mail recebido via JSON
        if(isEmailValido(cliente.getEmail())) {
            // Email informado é válido
            // Pesquisar pelo Cliente que está tentando atualizar
            Optional<Cliente> clienteAtualizar = clienteRepository.findByEmail(cliente.getEmail());
            if (clienteAtualizar.isPresent()) {
                cliente.setId(clienteAtualizar.get().getId());
                cliente.setSenha(clienteAtualizar.get().getSenha());
                // Chamando o repository para salvar o Cliente/Usuário editado
                // Informando o status da atualização como OK
                return ResponseEntity.status(HttpStatus.OK)
                        .body(clienteRepository.save(cliente));
            }
            // Cliente não encontrado. Abortar atualização.
            // Retornar HTTP Status 404 (Recurso não encontrado)
            return ResponseEntity.notFound().build();
        } // Se e-mail inválido
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    // Consultar Cliente por e-mail
    // Criando método para responder o verbo Get
    // passando o email informado como parâmetro de entrada
    @GetMapping("/consultar/{email}")
    public ResponseEntity<Cliente> getByEmail(@PathVariable String email) {
        // Validar o e-mail recebido via JSON
        if(isEmailValido(email)) {
            // Email informado é válido
            // Chamando método findBy do repository para efetuar a pesquisa
            // retornando o resultado via mapeamento da resposta OK ou NotFound
            return clienteRepository.findByEmail(email)
                    .map(resp -> ResponseEntity.ok(resp))
                    .orElse(ResponseEntity.notFound().build());
        } // Se e-mail inválido
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    // Consultar Cliente por e-mail
    // Criando método para responder o verbo Get
    // passando o email informado como parâmetro de entrada
    @GetMapping("/consultar/id/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    // Construir Get para todos os registros
    @GetMapping("/consultar/todos")
    public ResponseEntity<List<Cliente>> getAll() {
        // Retornar lista de Clientes
        // Retorna [] caso tabela vazia
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    // Validar e-mail informado
    public boolean isEmailValido(String email) {
        // Inicializar o retorno
        boolean emailValido = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException e) {
            emailValido=false;
        }
        return emailValido;
    }
}
