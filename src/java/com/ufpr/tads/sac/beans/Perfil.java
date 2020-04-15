package com.ufpr.tads.sac.beans;

import java.io.Serializable;

/**
 * Perfil.
 */
public class Perfil implements Serializable {
    
    private int id;
    private String nome;

    public Perfil() {
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
    
    public boolean isCliente() {
        return this.id == 1;
    }

    public boolean isFuncionario() {
        return this.id == 2;
    }

    public boolean isGerente() {
        return this.id == 3;
    }
    
}
