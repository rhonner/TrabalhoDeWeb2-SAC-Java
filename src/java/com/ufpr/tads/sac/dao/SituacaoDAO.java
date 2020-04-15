package com.ufpr.tads.sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.ufpr.tads.sac.beans.Situacao;

/**
 * Realiza interacoes com a base de dados relacionadas a Situacao.
 */
public class SituacaoDAO {
    
    /**
     * Obtem o registro de Situacao cadastrado na base de dados.
     * @param id identificador de Situacao.
     * @return O objeto Situacao com o id especificado.
     */
    public static Situacao get(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM situacao WHERE id_situacao = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Situacao sit = new Situacao();
                sit.setId(rs.getInt("id_situacao"));
                sit.setNome(rs.getString("nome"));
                return sit;
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter situacao! :(");
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (Exception e) {}
            }
            if (st != null) {
                try { st.close(); } catch (Exception e) {}
            }
            if (conn != null) {
                try { conn.close(); } catch (Exception e) {}
            }
        }
        return null;
    }
    
    /**
     * Obtem todos os registros de Situacao cadastrados na base de dados.
     * @return A lista de Situacoes cadastrados.
     */
    public static List<Situacao> get() {
        List<Situacao> sits = new ArrayList<Situacao>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM situacao;");
            rs = st.executeQuery();
            while (rs.next()) {
                Situacao sit = new Situacao();
                sit.setId(rs.getInt("id_situacao"));
                sit.setNome(rs.getString("nome"));
                sits.add(sit);
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter situacoes! :(");
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (Exception e) {}
            }
            if (st != null) {
                try { st.close(); } catch (Exception e) {}
            }
            if (conn != null) {
                try { conn.close(); } catch (Exception e) {}
            }
        }
        return sits;
    }
    
}
