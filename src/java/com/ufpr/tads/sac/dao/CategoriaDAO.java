package com.ufpr.tads.sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.sac.beans.Categoria;

/**
 * Realiza interacoes com a base de dados relacionadas a Categoria.
 */
public class CategoriaDAO {
    
    /**
     * Obtem o registro de Categoria cadastrado na base de dados.
     * @param id identificador de Categoria.
     * @return O objeto Categoria com o id especificado.
     */
    public static Categoria get(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM categoria WHERE id_categoria = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id_categoria"));
                cat.setNome(rs.getString("nome"));
                return cat;
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter categoria! :(");
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
     * Obtem todos os registros de Categoria cadastrados na base de dados.
     * @return A lista de Categorias cadastradas.
     */
    public static List<Categoria> get() {
        List<Categoria> cats = new ArrayList<Categoria>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM categoria;");
            rs = st.executeQuery();
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id_categoria"));
                cat.setNome(rs.getString("nome"));
                cats.add(cat);
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter categorias! :(");
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
        return cats;
    }
    
    /**
     * Insere um registro de Categoria na base de dados.
     * @param cat objeto da Categoria a ser inserida.
     * @return true se a Categoria foi inserida com sucesso.
     */
    public static boolean post(Categoria cat) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("INSERT INTO categoria (nome) VALUES (?);");
            st.setString(1, cat.getNome());
            st.executeUpdate();
            return true;
        } catch(Exception e) {
            System.out.println("Erro ao inserir categoria! :(");
        }
        finally {
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
    
    /**
     * Altera um registro de Categoria na base de dados.
     * @param cat objeto da Categoria a ser alterada.
     * @return true se a Categoria foi alterada com sucesso.
     */
    public static boolean put(Categoria cat) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("UPDATE categoria SET nome = ? WHERE id_categoria = ?;");
            st.setString(1, cat.getNome());
            st.setInt(2, cat.getId());
            st.executeUpdate();
            return true;
        } catch(Exception e) {
            System.out.println("Erro ao alterar categoria! :(");
        }
        finally {
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
    
    /**
     * Remove um Categoria cadastrado da base de dados.
     * @param id identificador da Categoria a ser removida.
     * @return true se a Categoria foi removida com sucesso.
     */
    public static boolean del(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("DELETE FROM categoria WHERE id_categoria = ?;");
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch(Exception e) {
            System.out.println("Erro ao remover categoria! :(");
        } finally {
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
