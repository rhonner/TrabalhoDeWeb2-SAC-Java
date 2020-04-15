package com.ufpr.tads.sac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ufpr.tads.sac.beans.Autenticacao;
import com.ufpr.tads.sac.beans.Cidade;
import com.ufpr.tads.sac.beans.Pessoa;
import com.ufpr.tads.sac.beans.Perfil;
import com.ufpr.tads.sac.beans.Endereco;
import com.ufpr.tads.sac.beans.Estado;
import java.util.ArrayList;
import java.util.List;

/**
 * Realiza interacoes com a base de dados relacionadas a Pessoa.
 */
public class PessoaDAO {

    /**
     * Verifica se a Pessoa esta cadastrada na base de dados.
     *
     * @param eml email da Pessoa.
     * @param sen senha da Pessoa.
     * @return O objeto Autenticacao se a Pessoa esta cadastrada.
     */
    public static Autenticacao auth(String eml, String sen) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT id_pessoa, nome, id_perfil FROM pessoa WHERE email = ? AND senha = md5(?);");
            st.setString(1, eml);
            st.setString(2, sen);
            rs = st.executeQuery();
            if (rs.next()) {
                Autenticacao aut = new Autenticacao();
                aut.setId(rs.getInt("id_pessoa"));
                aut.setNome(rs.getString("nome"));
                aut.setPerfil(PerfilDAO.get(rs.getInt("id_perfil")));
                return aut;
            }
        } catch (Exception e) {
            System.out.println("Erro ao autenticar usuário! :(");
        }
        finally {
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
        return null;
    }

    /**
     * Insere um registro de Pessoa na base de dados.
     *
     * @param psa objeto de Pessoa a ser inserido.
     * @return O objeto Autenticacao se Pessoa foi inserida com sucesso.
     */
    public static Autenticacao post(Pessoa psa) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("INSERT INTO endereco (rua, numero, cep, complemento, bairro, id_cidade) VALUES (?, ?, ?, ?, ?, ?);");
            st.setString(1, psa.getEndereco().getRua());
            st.setInt(2, psa.getEndereco().getNumero());
            st.setString(3, psa.getEndereco().getCep());
            st.setString(4, psa.getEndereco().getComplemento());
            st.setString(5, psa.getEndereco().getBairro());
            st.setInt(6, psa.getEndereco().getCidade().getId());
            st.executeUpdate();
            st = conn.prepareStatement("SELECT id_endereco FROM endereco WHERE rua = ? AND numero = ? AND cep = ?;");
            st.setString(1, psa.getEndereco().getRua());
            st.setInt(2, psa.getEndereco().getNumero());
            st.setString(3, psa.getEndereco().getCep());
            rs = st.executeQuery();
            int estId = 0;
            if (rs.next()) {
                estId = rs.getInt("id_endereco");
            }
            st = conn.prepareStatement("INSERT INTO pessoa (nome, cpf, data, telefone, email, senha, id_perfil, id_endereco) VALUES (?, ?, ?, ?, ?, md5(?), ?, ?);");
            st.setString(1, psa.getNome());
            st.setString(2, psa.getCpf());
            java.util.Date dt = psa.getData();
            java.sql.Date data = new java.sql.Date(dt.getTime());
            st.setDate(3, data);
            st.setString(4, psa.getTelefone());
            st.setString(5, psa.getEmail());
            st.setString(6, psa.getSenha());
            st.setInt(7, psa.getPerfil().getId());
            st.setInt(8, estId);
            st.executeUpdate();
            Autenticacao aut = new Autenticacao();
            aut.setNome(psa.getNome());
            return aut;
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar pessoa! :(");
        }
        finally {
            if (rs != null) {
                try { rs.close(); } catch (Exception e) {}
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
        return null;
    }
    public static void InserirFunc(Pessoa psa) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("INSERT INTO endereco (rua, numero, cep, complemento, bairro, id_cidade) VALUES (?, ?, ?, ?, ?, ?);");
            st.setString(1, psa.getEndereco().getRua());
            st.setInt(2, psa.getEndereco().getNumero());
            st.setString(3, psa.getEndereco().getCep());
            st.setString(4, psa.getEndereco().getComplemento());
            st.setString(5, psa.getEndereco().getBairro());
            st.setInt(6, psa.getEndereco().getCidade().getId());
            st.executeUpdate();
            st = conn.prepareStatement("SELECT id_endereco FROM endereco WHERE rua = ? AND numero = ? AND cep = ?;");
            st.setString(1, psa.getEndereco().getRua());
            st.setInt(2, psa.getEndereco().getNumero());
            st.setString(3, psa.getEndereco().getCep());
            rs = st.executeQuery();
            int estId = 0;
            if (rs.next()) {
                estId = rs.getInt("id_endereco");
            }
            st = conn.prepareStatement("INSERT INTO pessoa (nome, cpf, data, telefone, email, senha, id_perfil, id_endereco) VALUES (?, ?, ?, ?, ?, md5(?), ?, ?);");
            st.setString(1, psa.getNome());
            st.setString(2, psa.getCpf());
            java.util.Date dt = psa.getData();
            java.sql.Date data = new java.sql.Date(dt.getTime());
            st.setDate(3, data);
            st.setString(4, psa.getTelefone());
            st.setString(5, psa.getEmail());
            st.setString(6, psa.getSenha());
            st.setInt(7, psa.getPerfil().getId());
            st.setInt(8, estId);
            st.executeUpdate();
            Autenticacao aut = new Autenticacao();
            aut.setNome(psa.getNome());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar funcionario! :(");
        } finally {
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
    }

    /**
     * Obtem o registro de Pessoa cadastrado na base de dados.
     *
     * @param id identificador de Pessoa.
     * @return o objeto Pessoa com o id especificado.
     */
    public static Pessoa get(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM pessoa WHERE id_pessoa = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Pessoa psa = new Pessoa();
                psa.setId(rs.getInt("id_pessoa"));
                psa.setNome(rs.getString("nome"));
                psa.setCpf(rs.getString("cpf"));
                psa.setData(rs.getDate("data"));
                psa.setTelefone(rs.getString("telefone"));
                psa.setEmail(rs.getString("email"));
                psa.setSenha(rs.getString("senha"));
                Perfil prf = PerfilDAO.get(rs.getInt("id_perfil"));
                psa.setPerfil(prf);
                Endereco end = EnderecoDAO.get(rs.getInt("id_endereco"));
                psa.setEndereco(end);
                return psa;
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter pessoa! :(");
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
        return null;
    }

    public static List<Pessoa> getAllFunc() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Pessoa> lista = new ArrayList<Pessoa>();
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(" select  p.*, pr.nome as cargo from pessoa p, perfil pr where  (p.id_perfil= pr.id_perfil) and (p.id_perfil = 2 or p.id_perfil = 3) ");
            rs = st.executeQuery();
            while (rs.next()) {
                Pessoa psa = new Pessoa();
                psa.setId(rs.getInt("id_pessoa"));
                psa.setNome(rs.getString("nome"));
                psa.setCpf(rs.getString("cpf"));
                psa.setData(rs.getDate("data"));
                psa.setTelefone(rs.getString("telefone"));
                psa.setEmail(rs.getString("email"));
                psa.setSenha(rs.getString("senha"));
                Perfil prf = PerfilDAO.get(rs.getInt("id_perfil"));
                psa.setPerfil(prf);
                Endereco end = EnderecoDAO.get(rs.getInt("id_endereco"));
                Perfil perfil = new Perfil();
                perfil.setNome(rs.getString("cargo"));
                psa.setPerfil(perfil);
                psa.setEndereco(end);
                lista.add(psa);
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter pessoa! :(");
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
    public static List<Pessoa> getAllFunci(int idFunc) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Pessoa> lista = new ArrayList<Pessoa>();
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(" select  p.*, pr.nome as cargo from pessoa p, perfil pr where id_pessoa != ? and (p.id_perfil= pr.id_perfil) and (p.id_perfil = 2 or p.id_perfil = 3) ");
            st.setInt(1,idFunc);
            rs = st.executeQuery();
            while (rs.next()) {
                Pessoa psa = new Pessoa();
                psa.setId(rs.getInt("id_pessoa"));
                psa.setNome(rs.getString("nome"));
                psa.setCpf(rs.getString("cpf"));
                psa.setData(rs.getDate("data"));
                psa.setTelefone(rs.getString("telefone"));
                psa.setEmail(rs.getString("email"));
                psa.setSenha(rs.getString("senha"));
                Perfil prf = PerfilDAO.get(rs.getInt("id_perfil"));
                psa.setPerfil(prf);
                Endereco end = EnderecoDAO.get(rs.getInt("id_endereco"));
                Perfil perfil = new Perfil();
                perfil.setNome(rs.getString("cargo"));
                psa.setPerfil(perfil);
                psa.setEndereco(end);
                lista.add(psa);
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter pessoa! :(");
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

    public static Pessoa getFuncGer(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(" SELECT p.id_pessoa as id,p.senha,p.nome as nome,p.cpf as cpf, p.data as data, p.telefone as telefone, p.email as email,p.id_perfil as pidiper,\n"
                    + " pr.nome as cargo,\n"
                    + " ed.rua as rua,p.id_endereco as ided, ed.bairro as bairro,ed.complemento as complemento, ed.cep as cep,ed.numero as numero,\n"
                    + " cid.nome as cidade, cid.id_cidade as cididi,\n"
                    + " est.nome as estado, est.id_estado as idestado\n"
                    + " FROM pessoa p, perfil pr, endereco ed, cidade cid, estado est WHERE id_pessoa = ? and p.id_perfil = pr.id_perfil and \n"
                    + " ed.id_endereco = p.id_endereco and ed.id_cidade = cid.id_cidade and cid.id_estado = est.id_estado;");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Pessoa psa = new Pessoa();
                psa.setId(rs.getInt("id"));
                psa.setNome(rs.getString("nome"));
                psa.setCpf(rs.getString("cpf"));
                psa.setSenha(rs.getString("senha"));
                psa.setData(rs.getDate("data"));
                psa.setTelefone(rs.getString("telefone"));
                psa.setEmail(rs.getString("email"));
                Perfil prf = new Perfil();
                prf.setId(rs.getInt("pidiper"));
                prf.setNome(rs.getString("cargo"));
                Estado est = new Estado();
                est.setNome(rs.getString("estado"));
                Cidade cid = new Cidade();
                cid.setEstado(est);
                cid.setNome(rs.getString("cidade"));
                Endereco end = new Endereco();
                end.setId(rs.getInt("ided"));
                end.setNumero(rs.getInt("numero"));
                end.setBairro(rs.getString("bairro"));
                end.setRua(rs.getString("rua"));
                end.setCep(rs.getString("cep"));
                end.setComplemento(rs.getString("complemento"));
                end.setCidade(cid);
                psa.setPerfil(prf);
                psa.setEndereco(end);
                return psa;
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter pessoa! :(");
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
        return null;
    }
    public static Pessoa getGer(String login, String senha) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("select id_pessoa as id from pessoa where email = ? and senha = md5(?)");
            st.setString(1, login);
            st.setString(2,senha);
            rs = st.executeQuery();
            if (rs.next()) {
                Pessoa psa = new Pessoa();
                psa.setId(rs.getInt("id"));
                return psa;
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter gerente! :(");
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
        return null;
    }

    public static void Alterar(Pessoa psa) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("update pessoa set nome = ?, cpf = ?, data = ?, telefone = ?, email = ?, senha = md5(?), id_perfil = ? \n"
                    + " where id_pessoa = ?;");
            st.setString(1, psa.getNome());
            st.setString(2, psa.getCpf());
            java.util.Date dt = psa.getData();
            java.sql.Date data = new java.sql.Date(dt.getTime());
            st.setDate(3, data);
            st.setString(4, psa.getTelefone());
            st.setString(5, psa.getEmail());
            st.setString(6, psa.getSenha());
            st.setInt(7, psa.getPerfil().getId() );
            EnderecoDAO.AlterarP(psa);
            
            st.setInt(8, psa.getId());

            st.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro ao alterar pessoa! :(");
        } finally {
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
    }

    
        public static void remover(int id_pessoa) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("DELETE FROM pessoa WHERE id_pessoa = ?;");
            st.setInt(1, id_pessoa);
            st.executeUpdate();
        } catch(Exception e) {
            System.out.println("Erro ao remover cliente! :(");
        } finally {
            if (st != null) {
                try { st.close(); } catch (Exception e) {}
            }
            if (conn != null) {
                try { conn.close(); } catch (Exception e) {}
            }
        }
    }
}
