package com.salomdoces.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotNull
    @Size(min=5, max=15)
    @Column(name="forma_pagamento")
    private String formaPagamento;

    @Size(min=5, max=30)
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

    @NotNull
    @Column(name="preco_total")
    private Double precoTotal;

    //Fetch informa a forma pela qual os dados serão carregados do banco
    //Fetch Eager carrega os dados do banco independete se serão utilizados no momento

    //Merge é uma operação que copia um estado de um objeto para o objeto persistente com mesmo identificador


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @NotNull
    private List<ItemPedido> itens;

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

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}
