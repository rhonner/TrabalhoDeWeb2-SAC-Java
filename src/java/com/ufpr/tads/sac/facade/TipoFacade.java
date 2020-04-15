package com.ufpr.tads.sac.facade;

import java.util.List;

import com.ufpr.tads.sac.beans.Tipo;
import com.ufpr.tads.sac.dao.TipoDAO;

/**
 * Efetua acoes relacionadas a tipo.
 */
public class TipoFacade {
    public static List<Tipo> buscarTodosTipos() {
        return TipoDAO.buscarTodosTipos();
    }
    
    /**
     * Busca um tipo.
     * @param id identificador do Tipo.
     * @return O objeto Tipo com o id especificado.
     */
    public static Tipo buscarTipo(int id) {
        return TipoDAO.get(id);
    }
    
}
