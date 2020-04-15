package com.ufpr.tads.sac.facade;

import java.util.List;

import com.ufpr.tads.sac.beans.Produto;
import com.ufpr.tads.sac.dao.ProdutoDAO;

/**
 * Efetua acoes relacionadas a produto.
 */
public class ProdutoFacade {
    
    /**
     * Busca todos os produtos.
     * @return A lista de produtos se obtidos com sucesso.
     */
    public static List<Produto> buscarProdutos() {
        return ProdutoDAO.get();
    }
    
    /**
     * Busca um produto.
     * @param id identificador do Produto.
     * @return O objeto Produto com o id especificado.
     */
    public static Produto buscarProduto(int id) {
        return ProdutoDAO.get(id);
    }
    
    /**
     * Insere um Produto.
     * @param pdt objeto de Produto a ser inserido.
     * @return true se o Produto foi inserido com sucesso.
     */
    public static boolean novoProduto(Produto pdt) {
        return ProdutoDAO.post(pdt);
    }
    
    /**
     * Altera um Produto.
     * @param pdt objeto de Produto a ser alterado.
     * @return true se o Produto foi alterado com sucesso.
     */
    public static boolean alterarProduto(Produto pdt) {
        return ProdutoDAO.put(pdt);
    }
    
    /**
     * Remove um Produto.
     * @param id identificador do Produto a ser removido.
     * @return true se o Produto foi removido com sucesso.
     */
    public static boolean removerProduto(int id) {
        return ProdutoDAO.del(id);
    }
    
}
