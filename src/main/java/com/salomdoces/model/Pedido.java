package com.salomdoces.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;


@Entity
@JsonIgnoreProperties("produto")
@Table(name="pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //Tiramos os Not Nulls para não evitar um erro recorrente de parse não nulo
    //No momento de validação de campo, podemos forçar que ele esteja preenchido
    @Column(name="forma_pagamento")
    private String formaPagamento;

    @Column(name="status")
    private String status;

    //Informa a data e horário que a solicitação foi feita
    //Não precisa ser preenchido
    @Column(name="data_encomenda")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEncomenda = new java.sql.Date(System.currentTimeMillis());

    @Column(name="data_entrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrega;

    @ManyToOne
    private Cliente cliente;

    //Um pedido pode ter muitos produtos, e um produto pode estar em vários pedidos
    //Fetch informa a forma pela qual os dados serão carregados do banco
    //Fetch Eager carrega os dados do banco independete se serão utilizados no momento
    //Cascade molda o comportamento entre pai e filho. A operação feita no pai, passa para o filho
    //Merge é uma operação que copia um estado de um objeto para o objeto persistente com mesmo identificador

    //InverseJoinColumn customiza o nome da coluna na tabela da variável de referência de classe associada
    @ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name="pedidos_produtos", joinColumns = {@JoinColumn(name="pedido_id")},
            inverseJoinColumns = {@JoinColumn(name="produto_id")})
    private List<Produto> produtos;

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataEncomenda() {
        return dataEncomenda;
    }

    public void setDataEncomenda(Date dataEncomenda) {
        this.dataEncomenda = dataEncomenda;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
