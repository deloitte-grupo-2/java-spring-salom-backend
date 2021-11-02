package com.salomdoces.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

// implements Serializable:
// Necessário serializar a classe para que os dados
// sejam trocadas de forma binária (e não objetos Java)

// Tornando a classe uma entidade gerenciável
// Esta entidade se tornará uma tabela no database
@Entity
// Definindo o nome da entidade como "cliente" no banco de dados
@Table(name="cliente")
public class Cliente implements Serializable  {

    // Atributos encapsulados da classe

    // Declarando ID com Wrapper Class
    // (parâmetros das interfaces das classes de persistência de dados)
    // Definindo ID como Chave Primária (PK)
    @Id
    // Configurando a Chave Primária (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Configurando campo para não aceitar valores nulos
    @NotNull
    @Size(min=2, max=100)
    private String nome;
    // Configurando campo para não aceitar valores nulos
    @Size(min=11, max=14)
    private String cpf;
    // endereco: Será um atributo multivalorado do tipo Endereco
    // Definindo cardinalidade (um cliente pode ter muitos endereços e telefones)
    // Relacionamento mapeado pelo nome da tabela
    // Integridade referencial: quando um cliente é excluído,
    // os endereços e telefones associados também serão

    @OneToMany (cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cliente")
    private List<Endereco> endereco;
    // telefone: Será um atributo multivalorado do tipo Telefone
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cliente")
    private List<Telefone> telefone;
    // Configurando campos para usuário
    // Configurando campos para não aceitar valores nulos

    @NotNull
    @Size(min=6, max=100)

    private String email;

    @NotNull
    private String senha;

    @OneToMany(mappedBy="cliente", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("cliente")
    private List<Pedido> pedidos;

    // Construtor padrão
    public Cliente() { }

    // Getters e Setters: métodos de acesso aos atributos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    //A validacao de email foi feita em ClienteController
//    // Métodos da classe
//    public void validarEmail(String email) {
//        // Regra de negócio: Validação do e-mail
//        // Exemplo:
//        // 1) iniciar com 1 caracter alfanúmérico
//        // 2) Conter @
//        // 3) Conter 1 caracter alfanúmérico após @
//        // 4) Conter 1 ponto (".")
//        // 5) Conter 1 caracter alfanúmérico após ponto
//        // Resultado: a@b.c
//
//        this.setEmail(email);
//    }
}