package com.ufpr.tads.sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ufpr.tads.sac.beans.Endereco;
import com.ufpr.tads.sac.beans.Cidade;
import com.ufpr.tads.sac.beans.Pessoa;

/**
 * Realiza interacoes com a base de dados relacionadas a Endereco.
 */
public class EnderecoDAO {
    
    /**
     * Obtem o registro de Endereco cadastrado na base de dados.
     * @param id identificador de Endereco.
     * @return O objeto Endereco com o id especificado.
     */
    public static Endereco get(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM endereco WHERE id_endereco = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Endereco end = new Endereco();
                end.setId(rs.getInt("id_endereco"));
                end.setRua(rs.getString("rua"));
                end.setNumero(rs.getInt("numero"));
                end.setCep(rs.getString("cep"));
                end.setComplemento(rs.getString("complemento"));
                end.setBairro(rs.getString("bairro"));
                Cidade cid = CidadeDAO.get(rs.getInt("id_cidade"));
                end.setCidade(cid);
                return end;
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter endereco! :(");
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
    
        public static void AlterarP(Pessoa psa) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("update endereco set rua = ?, numero = ?, cep = ?, complemento = ?, bairro = ?,id_cidade = ? where id_endereco = ?");
            st.setString(1, psa.getEndereco().getRua());
            st.setInt(2, psa.getEndereco().getNumero());
            String cepOK = psa.getEndereco().getCep().replaceAll("[.-]", "");
            st.setString(3, cepOK);
            st.setString(4, psa.getEndereco().getComplemento());
            st.setString(5, psa.getEndereco().getBairro());
            st.setInt(6,psa.getEndereco().getCidade().getId());
            st.setInt(7,psa.getEndereco().getId());
            st.executeUpdate();
                            
        } catch(Exception e) {
            System.out.println("Erro ao obter endereco! :(");
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
    }
    
    
    public static boolean checkRua(String rua) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM endereco WHERE rua = ?;");
            st.setString(1, rua);
            rs = st.executeQuery();
            if (rs.next()) {
                check = true;
                return check;
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter endereco! :(");
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
