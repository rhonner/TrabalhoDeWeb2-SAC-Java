package com.ufpr.tads.sac.facade;

import java.util.List;

import com.ufpr.tads.sac.beans.Atendimento;
import com.ufpr.tads.sac.dao.AtendimentoDAO;
import com.ufpr.tads.sac.beans.PesquisaGerente;

/**
 * Efetua acoes relacionadas a Atendimento.
 */
public class AtendimentoFacade {
    
    /**
     * Busca todos os atendimentos.
     * @return A lista de atendimentos se obtidos com sucesso.
     */
    public static List<Atendimento> get() {
        return AtendimentoDAO.get();
    }
    
    /**
     * Busca um atendimento.
     * @param id identificador do atendimento.
     * @return O objeto Atendimento se obtido com sucesso.
     */
    public static Atendimento get(int id) {
        return AtendimentoDAO.get(id);
    }
    
    /**
     * Cria um novo atendimento.
     * @param atd atendimento a ser criado.
     * @return true se o atendimento foi criado com sucesso.
     */
    public static boolean novo(Atendimento atd) {
        return AtendimentoDAO.post(atd);
    }
    
    /**
     * Resolve um atendimento aberto.
     * @param atd atendimento a ser resolvido.
     * @return true se o atendimento foi resolvido com sucesso.
     */
    public static boolean resolver(Atendimento atd) {
        return AtendimentoDAO.put(atd);
    }
    
    /**
     * Remove um atendimento.
     * @param id identificador do Atendimento a ser removido.
     * @return true se o Atendimento foi removido com sucesso.
     */
    public static boolean remover(int id) {
        return AtendimentoDAO.del(id);
    }

    public static int getTotal() {
        return AtendimentoDAO.getAberto();
    }

    public static int getAberto() {
        return AtendimentoDAO.getAbertoT();
    }

    public static float getPerc() {
        return AtendimentoDAO.getPerc();
    }
    
    public static List<PesquisaGerente> buscarTodosTipo() {
        return AtendimentoDAO.buscarTodosTipo();
    }
    
    public static List<Atendimento> buscarTodosAberto() {
        return AtendimentoDAO.buscarTodosAberto();
    }
    
    public static List<Atendimento> buscarTodos(){
        return AtendimentoDAO.buscarTodos();
    } 
}
