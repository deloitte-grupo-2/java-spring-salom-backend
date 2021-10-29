package com.salomdoces.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


// Tornando a classe uma entidade gerenciavel
// Esta entidade se tornará uma tabela no database
@Entity
// Definindo o nome da entidade como "endereco" no banco de dados
@Table(name="endereco")
public class Endereco {

    // Atributos encapsulados da classe

    // Declarando ID com Wrapper Class
    // (parâmetros das interfaces das classes de persistência de dados)
    // Definindo ID como Chave Primária (PK)
    @Id
    // Configurando a Chave Primária (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Definindo cardinalidade (um cliente pode ter muitos endereços e telefones)
    // Relacionamento mapeado pelo nome da tabela na entidade Cliente
    // Integridade referencial: quando um cliente é excluído,
    // os endereços e telefones associados também serão
//    @ManyToOne
////    // Ignora recursividade
//    @JsonIgnoreProperties("cliente")
//    // Configurando campo para não aceitar valores nulos: endereço sempre será associado a um cliente
//    @NotNull
//    @JoinColumn(name = "id_cliente")
//    private Cliente cliente;
//    // Configurando campo para não aceitar valores nulos
    @NotNull
    private String logradouro;
    // Configurando campo para não aceitar valores nulos
    @NotNull
    private int numero;
    // Complemento pode aceitar valores nulos
    private String complemento;
    // Configurando campo para não aceitar valores nulos
    @NotNull
    private String cep;
    // Tipo de telefone: cadastral, entrega, cobrança
    // Configurando campo para não aceitar valores nulos
    @NotNull
    private String tipo;

    // Construtor padrão
    public Endereco() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

//    public Cliente getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}