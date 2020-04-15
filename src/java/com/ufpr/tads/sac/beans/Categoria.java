package com.ufpr.tads.sac.beans;

import java.io.Serializable;

/**
 * Situacao.
 */
public class Categoria implements Serializable {
    
    private int id;
    private String nome;

    public Categoria() {
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
    
}
