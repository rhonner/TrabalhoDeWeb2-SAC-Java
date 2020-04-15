package com.ufpr.tads.sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.ufpr.tads.sac.beans.Estado;

/**
 * Realiza interacoes com a base de dados relacionadas a Estado.
 */
public class EstadoDAO {
    
    /**
     * Obtem todos os registros de Estado cadastrados na base de dados.
     * @return A lista de Estados cadastrados.
     */
    public static List<Estado> get() {
        List<Estado> ests = new ArrayList<Estado>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM estado;");
            rs = st.executeQuery();
            while (rs.next()) {
                Estado est = new Estado();
                est.setId(rs.getInt("id_estado"));
                est.setNome(rs.getString("nome"));
                est.setSigla(rs.getString("sigla"));
                ests.add(est);
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter estados! :(");
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
        return ests;
    }
    
    /**
     * Obtem o registro de Estado cadastrado na base de dados.
     * @param id identificador de Estado.
     * @return O objeto Estado com o id especificado.
     */
    public static Estado get(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM estado WHERE id_estado = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Estado est = new Estado();
                est.setId(rs.getInt("id_estado"));
                est.setNome(rs.getString("nome"));
                est.setSigla(rs.getString("sigla"));
                return est;
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter estado! :(");
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
    
        public static boolean checkEstado(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM estado WHERE id_estado = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                check = true;
                return check;
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter estado! :(");
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
        return false;
    }
    
}
