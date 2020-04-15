package com.ufpr.tads.sac.facade;

import com.ufpr.tads.sac.beans.Autenticacao;
import com.ufpr.tads.sac.beans.Pessoa;
import com.ufpr.tads.sac.dao.PessoaDAO;

/**
 * Efetua acoes relacionadas a autenticacao.
 */
public class AutenticacaoFacade {
    
    /**
     * Efetua o login do usuario.
     * @param eml email do usuario.
     * @param sen senha do usuario.
     * @return O objeto Autenticacao se autenticado com sucesso.
     */
    public static Autenticacao login(String eml, String sen) {
        return PessoaDAO.auth(eml, sen);
    }
    
    /**
     * Efetua o cadastro do usuario.
     * @param psa pessoa a ser cadastrada.
     * @return O objeto Autenticacao se cadastrado com sucesso.
     */
    public static Autenticacao cadastro(Pessoa psa) {
        return PessoaDAO.post(psa);
    }
        
}
