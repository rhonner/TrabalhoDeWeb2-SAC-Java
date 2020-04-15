package com.ufpr.tads.sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.ufpr.tads.sac.beans.Cidade;
import com.ufpr.tads.sac.beans.Estado;

/**
 * Realiza interacoes com a base de dados relacionadas a Cidade.
 */
public class CidadeDAO {
    
    /**
     * Obtem todos os registros de Cidade do Estado especificado.
     * @param id indentificador de Estado.
     * @return A lista de Cidades.
     */
    public static List<Cidade> gets(int id) {
        List<Cidade> cids = new ArrayList<Cidade>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM cidade WHERE id_estado = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Cidade cid = new Cidade();
                cid.setId(rs.getInt("id_cidade"));
                cid.setNome(rs.getString("nome"));
                cids.add(cid);
            }
        } catch(Exception e) {
            System.out.println("Erro ao obter cidades! :(");
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
        return cids;
    }
    
    /**
     * Obtem o registro de Cidade cadastrado na base de dados.
     * @param id identificador de Cidade.
     * @return O objeto Cidade com o id especificado.
     */
    public static Cidade get(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM cidade WHERE id_cidade = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Cidade cid = new Cidade();
                cid.setId(rs.getInt("id_cidade"));
                cid.setNome(rs.getString("nome"));
                Estado est = EstadoDAO.get(rs.getInt("id_estado"));
                cid.setEstado(est);
                return cid;
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter cidade! :(");
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
    public static boolean checkCidade(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM cidade WHERE id_cidade = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                    check = true;
                    return check;
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter cidade! :(");
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
