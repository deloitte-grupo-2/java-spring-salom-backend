package com.salomdoces.service;

import com.salomdoces.model.Cliente;
import com.salomdoces.model.ClienteLogin;
import com.salomdoces.repository.ClienteRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

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

    // Método Logar: gerar o token do usuário
    // Interface Optional: evitar NullPointerException por retorno null
    public Optional<ClienteLogin> logar(Optional<ClienteLogin> user) {

        // Criando um objeto encoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // Pesquisando pelo usuário
        Optional<Cliente> usuario = clienteRepository.findByEmail(user.get().getEmail());

        // Verificando se usuário foi encontrado
        if(usuario.isPresent()) {
            // Comparar se a senha fornecida bate com a senha gravada
            if(encoder.matches(user.get().getSenha(),usuario.get().getSenha())) {
                // Senhas conferem. Criar token.
                // String de autenticação: ariella.falcao@deloitte.com:duda2021
                String auth = user.get().getEmail() + ":" + user.get().getSenha();
                // Encondando a string de autenticação usando Base64
                byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                // Gerando token
                String authHeader = "Basic " + new String(encodeAuth);
                // Atualizando o usuário que está efetuando o login com o token
                // Atribuindo token
                user.get().setToken(authHeader);
                // Atribuindo o nome cadastrado no Banco de Dados
                user.get().setNome(usuario.get().getNome());
                //Retornar o usuário autenticado
                return user;
            }
        }
        // Usuário não encontrado no Banco de Dados
        return null;
    }
}
