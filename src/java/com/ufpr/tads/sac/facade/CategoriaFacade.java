package com.ufpr.tads.sac.facade;

import java.util.List;

import com.ufpr.tads.sac.beans.Categoria;
import com.ufpr.tads.sac.dao.CategoriaDAO;

/**
 * Efetua acoes relacionadas a categoria.
 */
public class CategoriaFacade {
    
    /**
     * Busca todos as categorias.
     * @return A lista de categorias se obtidos com sucesso.
     */
    public static List<Categoria> buscarCategorias() {
        return CategoriaDAO.get();
    }
    
    /**
     * Busca uma categoria.
     * @param id identificador da Categoria.
     * @return O objeto Categoria com o id especificado.
     */
    public static Categoria buscarCategoria(int id) {
        return CategoriaDAO.get(id);
    }
    
    /**
     * Insere uma Categoria.
     * @param cat objeto de Categoria a ser inserido.
     * @return true se a Categoria foi inserida com sucesso.
     */
    public static boolean novaCategoria(Categoria cat) {
        return CategoriaDAO.post(cat);
    }
    
    /**
     * Altera uma Categoria.
     * @param cat objeto de Categoria a ser alterado.
     * @return true se a Categoria foi alterada com sucesso.
     */
    public static boolean alterarCategoria(Categoria cat) {
        return CategoriaDAO.put(cat);
    }
    
    /**
     * Remove uma Categoria.
     * @param id identificador do Categoria a ser removido.
     * @return true se a Categoria foi removida com sucesso.
     */
    public static boolean removerCategoria(int id) {
        return CategoriaDAO.del(id);
    }
}
