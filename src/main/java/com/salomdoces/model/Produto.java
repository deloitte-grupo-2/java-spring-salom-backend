package com.salomdoces.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@JsonIgnoreProperties("pedido")
@Table(name="produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @NotNull
    @Size(min=2, max=20)
    @Column(name="nome")
    private String nome;


    @NotNull
    @Column(name="preco")
    private Double preco;

    @NotNull
    @Column(name="quantidade")
    private Integer quantidade;

    @Size(min=6, max=100)
    @Column(name="descricao")
    private String descricao;

    @Column(name="imagemUrl")
    private String imagemUrl;


    //Um pedido pode ter muitos produtos, e um produto pode estar em vários pedidos
    //Fetch informa a forma pela qual os dados serão carregados do banco
    //Fetch Lazy carrega os dados do banco apenas quando eles são explicitamente pedidos
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "produtos")
    private List<Pedido> pedidos;

    public Produto() {
    }

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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
