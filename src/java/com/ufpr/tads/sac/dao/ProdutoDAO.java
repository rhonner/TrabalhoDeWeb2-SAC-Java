package com.ufpr.tads.sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.ufpr.tads.sac.beans.Produto;
import com.ufpr.tads.sac.beans.Categoria;

/**
 * Realiza interacoes com a base de dados relacionadas a Produto.
 */
public class ProdutoDAO {
    
    /**
     * Obtem o registro de Produto cadastrado na base de dados.
     * @param id identificador de Produto.
     * @return O objeto Produto com o id especificado.
     */
    public static Produto get(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM produto WHERE id_produto = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Produto pdt = new Produto();
                pdt.setId(rs.getInt("id_produto"));
                pdt.setNome(rs.getString("nome"));
                Categoria cat = CategoriaDAO.get(rs.getInt("id_categoria"));
                pdt.setCategoria(cat);
                return pdt;
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter produto! :(");
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
     * Obtem todos os registros de Produto cadastrados na base de dados.
     * @return A lista de Produtos cadastrados.
     */
    public static List<Produto> get() {
        List<Produto> pdts = new ArrayList<Produto>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM produto;");
            rs = st.executeQuery();
            while (rs.next()) {
                Produto pdt = new Produto();
                pdt.setId(rs.getInt("id_produto"));
                pdt.setNome(rs.getString("nome"));
                Categoria cat = CategoriaDAO.get(rs.getInt("id_categoria"));
                pdt.setCategoria(cat);
                pdts.add(pdt);
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter produtos! :(");
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
        return pdts;
    }
    
    /**
     * Insere um registro de Produto na base de dados.
     * @param pdt objeto de Produto a ser inserido.
     * @return true se o Produto foi inserido com sucesso.
     */
    public static boolean post(Produto pdt) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("INSERT INTO produto (nome, id_categoria) VALUES (?, ?);");
            st.setString(1, pdt.getNome());
            st.setInt(2, pdt.getCategoria().getId());
            st.executeUpdate();
            return true;
        } catch(Exception e) {
            System.out.println("Erro ao inserir produto! :(");
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
     * Altera um registro de Produto na base de dados.
     * @param pdt objeto de Produto a ser alterado.
     * @return true se o Produto foi alterado com sucesso.
     */
    public static boolean put(Produto pdt) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("UPDATE produto SET nome = ?, id_categoria = ? WHERE id_produto = ?;");
            st.setString(1, pdt.getNome());
            st.setInt(2, pdt.getCategoria().getId());
            st.setInt(3, pdt.getId());
            st.executeUpdate();
            return true;
        } catch(Exception e) {
            System.out.println("Erro ao alterar produto! :(");
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
     * Remove um Produto cadastrado da base de dados.
     * @param id identificador do Produto a ser removido.
     * @return true se o Produto foi removido com sucesso.
     */
    public static boolean del(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("DELETE FROM produto WHERE id_produto = ?;");
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch(Exception e) {
            System.out.println("Erro ao remover produto! :(");
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
