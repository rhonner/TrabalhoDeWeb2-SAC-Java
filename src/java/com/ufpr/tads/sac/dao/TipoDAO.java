package com.ufpr.tads.sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.sac.beans.Tipo;

/**
 * Realiza interacoes com a base de dados relacionadas a Tipo.
 */
public class TipoDAO {
    
    /**
     * Obtem o registro de Tipo cadastrado na base de dados.
     * @param id identificador de Tipo.
     * @return O objeto Tipo com o id especificado.
     */
    public static Tipo get(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM tipo WHERE id_tipo = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Tipo tip = new Tipo();
                tip.setId(rs.getInt("id_tipo"));
                tip.setNome(rs.getString("nome"));
                return tip;
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter tipo! :(");
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
    
    
        public static List<Tipo> buscarTodosTipos() {
        List<Tipo> resultados = new ArrayList<Tipo>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM tipo;");
            rs = st.executeQuery();
            while (rs.next()) {
                Tipo tipo = new Tipo();
                tipo.setId(rs.getInt("id_tipo"));
                tipo.setNome(rs.getString("nome"));
                resultados.add(tipo);
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter atendimentos! :(");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
        return resultados;
    }
}
