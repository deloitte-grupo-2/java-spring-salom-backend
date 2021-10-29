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

    @Column(name="forma_pagamento")
    private String formaPagamento;

    @Column(name="status")
    private String status;

    @Column(name="data_encomenda")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEncomenda = new java.sql.Date(System.currentTimeMillis());

    @Column(name="data_entrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrega;

    @ManyToOne
    private Cliente cliente;

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
