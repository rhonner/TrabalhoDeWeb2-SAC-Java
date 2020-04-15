package com.ufpr.tads.sac.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Atendimento.
 */
public class Atendimento implements Serializable {
    
    private int id;
    private Date datahora;
    private String descricao;
    private String solucao;
    private Situacao situacao;
    private Tipo tipo;
    private Produto produto;
    private Pessoa pessoa;

    public Atendimento() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Date getDatahora() {
        return datahora;
    }
    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSolucao() {
        return solucao;
    }
    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public Situacao getSituacao() {
        return situacao;
    }
    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Tipo getTipo() {
        return tipo;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
}
