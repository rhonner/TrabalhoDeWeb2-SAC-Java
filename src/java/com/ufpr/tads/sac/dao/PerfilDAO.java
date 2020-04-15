package com.ufpr.tads.sac.dao;

import com.ufpr.tads.sac.beans.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ufpr.tads.sac.beans.Perfil;
import com.ufpr.tads.sac.beans.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 * Realiza interações com a base de dados relacionadas a Perfil.
 */
public class PerfilDAO {
    
    /**
     * Obtem o registro de Perfil cadastrado na base de dados.
     * @param id identificador de Perfil.
     * @return O objeto Perfil com o id especificado.
     */
    public static Perfil get(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM perfil WHERE id_perfil = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Perfil prf = new Perfil();
                prf.setId(rs.getInt("id_perfil"));
                prf.setNome(rs.getString("nome"));
                return prf;
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter perfil! :(");
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
    
    
    public static List<Perfil> getAllPerf() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Perfil> lista = new ArrayList<Perfil>();
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("select * from perfil where id_perfil = 2 or id_perfil = 3;");
            rs = st.executeQuery();
            while (rs.next()) {
                Perfil perfil = new Perfil();
                perfil.setId(rs.getInt("id_perfil"));
                perfil.setNome(rs.getString("nome"));               
                lista.add(perfil);
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter perfil! :(");
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
        return lista;
    }
}
