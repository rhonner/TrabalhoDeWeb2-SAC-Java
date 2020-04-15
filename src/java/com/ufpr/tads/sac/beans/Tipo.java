package com.ufpr.tads.sac.beans;

import java.io.Serializable;

/**
 * Tipo.
 */
public class Tipo implements Serializable {
    
    private int id;
    private String nome;

    public Tipo() {
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
