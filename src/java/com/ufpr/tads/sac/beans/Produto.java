package com.ufpr.tads.sac.beans;

import java.io.Serializable;

/**
 * Situacao.
 */
public class Produto implements Serializable {
    
    private int id;
    private String nome;
    private Categoria categoria; 

    public Produto() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
}
