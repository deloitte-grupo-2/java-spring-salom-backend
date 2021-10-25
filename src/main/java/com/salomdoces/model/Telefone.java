package com.salomdoces.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

// Tornando a classe uma entidade gerenciavel
// Esta entidade se tornará uma tabela no database
@Entity
// Definindo o nome da entidade como "telefone" no banco de dados
@Table(name="telefone")
public class Telefone {

    // Atributos encapsulados da classe

    // Declarando ID com Wrapper Class
    // (parâmetros das interfaces das classes de persistência de dados)
    // Definindo ID como Chave Primária (PK)
    @Id
    // Configurando a Chave Primária (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    // Definindo cardinalidade (um cliente pode ter muitos endereços e telefones)
    // Relacionamento mapeado pelo nome da tabela na entidade Cliente
    // Integridade referencial: quando um cliente é excluído,
    // os endereços e telefones associados também serão
    @ManyToOne
    // Ignora recursividade
    @JsonIgnoreProperties("cliente")
    // Configurando campo para não aceitar valores nulos: telefone sempre será associado a um cliente
    @NotNull
    private Cliente cliente;
    // Configurando campo para não aceitar valores nulos
    @NotNull
    private String ddd;
    // Configurando campo para não aceitar valores nulos
    @NotNull
    private String numero;
    // Tipo de telefone: residencial, comercial, celular
    // Configurando campo para não aceitar valores nulos
    @NotNull
    private String tipo;

    // Construtor padrão
    public Telefone() { }

    // Getters e Setters da classe
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
