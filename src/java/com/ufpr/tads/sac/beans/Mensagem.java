package com.ufpr.tads.sac.beans;

/**
 * Mensagem.
 */
public class Mensagem {
    
    private String titulo;
    private String descricao;
    private String contexto;

    public Mensagem() {
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getContexto() {
        return contexto;
    }
    public void setContexto(String contexto) {
        this.contexto = contexto;
    }
    
}
