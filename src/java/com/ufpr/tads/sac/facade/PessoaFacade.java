package com.ufpr.tads.sac.facade;

import com.ufpr.tads.sac.beans.Cidade;
import com.ufpr.tads.sac.beans.Estado;
import com.ufpr.tads.sac.beans.Perfil;
import com.ufpr.tads.sac.beans.Pessoa;
import com.ufpr.tads.sac.dao.CidadeDAO;
import com.ufpr.tads.sac.dao.EstadoDAO;
import com.ufpr.tads.sac.dao.PerfilDAO;
import com.ufpr.tads.sac.dao.PessoaDAO;
import java.util.List;

/**
 * Efetua acoes relacionadas a pessoa.
 */
public class PessoaFacade {
    
    /**
     * Busca um perfil.
     * @param id identificador do Perfil.
     * @return O objeto Perfil com o id especificado.
     */
    public static Perfil buscarPerfil(int id) {
        return PerfilDAO.get(id);
    }
    
    /**
     * Busca uma pessoa.
     * @param id identificador da Pessoa.
     * @return O objeto Pessoa com o id especificado.
     */
    public static Pessoa buscarPessoa(int id) {
        return PessoaDAO.get(id);
    }
    
    public static List<Pessoa> getAllFunc(){
        return PessoaDAO.getAllFunc();
    }
    
     public static Pessoa getFuncGer(int id){
         return PessoaDAO.getFuncGer(id);
     }
     
     public static List<Estado> buscarEstados() {
         return EstadoDAO.get();
     }
     
     public static List<Cidade> gets(int id){
         return CidadeDAO.gets(id);
     }
     
      public static void Alterar(Pessoa psa) {
           PessoaDAO.Alterar(psa);
      }
      
      public static void remover(int id_pessoa){
          PessoaDAO.remover(id_pessoa);
      }
      
      public static void InserirFunc(Pessoa psa){
          PessoaDAO.InserirFunc(psa);
      }
      
      public static Pessoa getGer(String login, String senha){
          return PessoaDAO.getGer(login, senha);
      }
      
      public static List<Pessoa> getAllFunci(int idFunc){
          return PessoaDAO.getAllFunci(idFunc);
      }
}
