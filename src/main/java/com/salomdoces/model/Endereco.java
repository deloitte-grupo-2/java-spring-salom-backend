package com.salomdoces.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


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
    // Ignora recursividade
    // Configurando campo para não aceitar valores nulos: endereço sempre será associado a um cliente

    // Configurando campo para não aceitar valores nulos

    @NotNull
    @Size(min=4, max=100)
    private String logradouro;
    // Configurando campo para não aceitar valores nulos


    @NotNull
    private int numero;
    // Complemento pode aceitar valores nulos

    @Size(min=1, max=100)
    private String complemento;
    // Configurando campo para não aceitar valores nulos
    @NotNull
    @Size(min=8, max=9)
    private String cep;
    // Tipo de telefone: cadastral, entrega, cobrança
    // Configurando campo para não aceitar valores nulos
    @NotNull
    @Size(min=2, max=30)
    private String apelido;

    @ManyToOne
    @JsonIgnoreProperties("cliente")
    private Cliente cliente;

    // Construtor padrão
    public Endereco() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }



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

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
}