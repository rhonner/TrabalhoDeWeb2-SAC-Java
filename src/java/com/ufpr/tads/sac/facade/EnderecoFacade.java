package com.ufpr.tads.sac.facade;

import java.util.List;

import com.ufpr.tads.sac.beans.Estado;
import com.ufpr.tads.sac.dao.EstadoDAO;
import com.ufpr.tads.sac.beans.Cidade;
import com.ufpr.tads.sac.dao.CidadeDAO;

/**
 * Efetua acoes relacionadas a Endereco.
 */
public class EnderecoFacade {
    
    /**
     * Busca todos os estados.
     * @return A lista de estados se obtidos com sucesso.
     */
    public static List<Estado> buscarEstados() {
        return EstadoDAO.get();
    }
    
    /**
     * Busca um estado.
     * @param id identificador do estado.
     * @return O objeto Estado com o id especificado.
     */
    public static Estado buscarEstado(int id) {
        return EstadoDAO.get(id);
    }
    
    /**
     * Busca as cidades de um estado.
     * @param id identificador do Estado.
     * @return A lista de cidades se obtidas com sucesso.
     */
    public static List<Cidade> buscarCidades(int id) {
        return CidadeDAO.gets(id);
    }
    
    /**
     * Busca uma cidade.
     * @param id identificador da cidade.
     * @return O objeto Cidade com o id especificado.
     */
    public static Cidade buscarCidade(int id) {
        return CidadeDAO.get(id);
    }
}
