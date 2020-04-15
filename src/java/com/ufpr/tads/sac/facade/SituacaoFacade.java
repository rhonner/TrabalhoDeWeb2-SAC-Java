package com.ufpr.tads.sac.facade;

import java.util.List;

import com.ufpr.tads.sac.beans.Situacao;
import com.ufpr.tads.sac.dao.SituacaoDAO;

/**
 * Efetua acoes relacionadas a situacao.
 */
public class SituacaoFacade {
    
    /**
     * Busca todos as situacoes.
     * @return A lista de situacoes se obtidos com sucesso.
     */
    public static List<Situacao> buscarSituacoes() {
        return SituacaoDAO.get();
    }
    
    /**
     * Busca um produto.
     * @param id identificador do Situacao.
     * @return O objeto Situacao com o id especificado.
     */
    public static Situacao buscarSituacao(int id) {
        return SituacaoDAO.get(id);
    }
}
