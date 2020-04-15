package com.ufpr.tads.sac.beans;

import java.io.Serializable;

/**
 * Representa a sessao iniciada do usuario.
 */
public class Autenticacao implements Serializable {
    
    private int id;
    private String nome;
    private Perfil perfil;

    public Autenticacao() {
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

    public Perfil getPerfil() {
        return perfil;
    }
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
}
