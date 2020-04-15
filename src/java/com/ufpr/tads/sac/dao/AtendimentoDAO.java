package com.ufpr.tads.sac.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.sac.beans.Atendimento;
import com.ufpr.tads.sac.beans.Produto;
import com.ufpr.tads.sac.beans.Situacao;
import com.ufpr.tads.sac.beans.Tipo;
import com.ufpr.tads.sac.beans.PesquisaGerente;
import com.ufpr.tads.sac.beans.Pessoa;

/**
 * Realiza interacoes com a base de dados relacionadas a Atendimento.
 */
public class AtendimentoDAO {
    
    /**
     * Obtem todos os registros de Atendimento cadastrados na base de dados.
     * @return A lista de Atendimentos cadastrados.
     */
    public static List<Atendimento> get() {
        List<Atendimento> atds = new ArrayList<Atendimento>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM atendimento;");
            rs = st.executeQuery();
            while (rs.next()) {
                Atendimento atd = new Atendimento();
                atd.setId(rs.getInt("id_atendimento"));
                atd.setDatahora(rs.getTimestamp("datahora"));
                atd.setDescricao(rs.getString("descricao"));
                atd.setSolucao(rs.getString("solucao"));
                Situacao sit = SituacaoDAO.get(rs.getInt("id_situacao"));
                atd.setSituacao(sit);
                Tipo tip = TipoDAO.get(rs.getInt("id_tipo"));
                atd.setTipo(tip);
                Produto pdt = ProdutoDAO.get(rs.getInt("id_produto"));
                atd.setProduto(pdt);
                Pessoa psa = PessoaDAO.get(rs.getInt("id_pessoa"));
                atd.setPessoa(psa);
                atds.add(atd);
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter atendimentos! :(");
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
        return atds;
    }
    
    /**
     * Obtem o registro de Atendimento cadastrado na base de dados.
     * @param id identificador de Atendimento.
     * @return O objeto Atendimento com o id especificado.
     */
    public static Atendimento get(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM atendimento WHERE id_atendimento = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Atendimento atd = new Atendimento();
                atd.setId(rs.getInt("id_atendimento"));
                atd.setDatahora(rs.getTimestamp("datahora"));
                atd.setDescricao(rs.getString("descricao"));
                atd.setSolucao(rs.getString("solucao"));
                Situacao sit = SituacaoDAO.get(rs.getInt("id_situacao"));
                atd.setSituacao(sit);
                Tipo tip = TipoDAO.get(rs.getInt("id_tipo"));
                atd.setTipo(tip);
                Produto prd = ProdutoDAO.get(rs.getInt("id_produto"));
                atd.setProduto(prd);
                Pessoa psa = PessoaDAO.get(rs.getInt("id_pessoa"));
                atd.setPessoa(psa);
                return atd;
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter atendimento! :(");
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
     * Insere um registro de Atendimento na base de dados.
     * @param atd objeto de Atendimento a ser inserido.
     * @return true se o Atendimento foi inserido com sucesso.
     */
    public static boolean post(Atendimento atd) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("INSERT INTO atendimento (datahora, descricao, solucao, id_situacao, id_tipo, id_produto, id_pessoa) VALUES (?, ?, ?, ?, ?, ?, ?);");
            java.util.Date dt = atd.getDatahora();
            java.sql.Timestamp data = new java.sql.Timestamp(dt.getTime());
            st.setTimestamp(1, data);
            st.setString(2, atd.getDescricao());
            st.setString(3, atd.getSolucao());
            st.setInt(4, atd.getSituacao().getId());
            st.setInt(5, atd.getTipo().getId());
            st.setInt(6, atd.getProduto().getId());
            st.setInt(7, atd.getPessoa().getId());
            st.executeUpdate();
            return true;
        } catch(Exception e) {
            System.out.println("Erro ao inserir atendimento! :(");
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
     * Altera um registro de Atendimento na base de dados.
     * @param atd objeto de Atendimento a ser alterado.
     * @return true se o Atendimento foi alterado com sucesso.
     */
    public static boolean put(Atendimento atd) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("UPDATE atendimento SET datahora = ?, descricao = ?, solucao = ?, id_situacao = ?, id_tipo = ?, id_produto = ?, id_pessoa = ? WHERE id_atendimento = ?;");
            java.util.Date dt = atd.getDatahora();
            java.sql.Timestamp data = new java.sql.Timestamp(dt.getTime());
            st.setTimestamp(1, data);
            st.setString(2, atd.getDescricao());
            st.setString(3, atd.getSolucao());
            st.setInt(4, atd.getSituacao().getId());
            st.setInt(5, atd.getTipo().getId());
            st.setInt(6, atd.getProduto().getId());
            st.setInt(7, atd.getPessoa().getId());
            st.setInt(8, atd.getId());
            st.executeUpdate();
            return true;
        } catch(Exception e) {
            System.out.println("Erro ao alterar atendimento! :(");
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
     * Remove um Atendimento cadastrado da base de dados.
     * @param id identificador do Atendimento a ser removido.
     * @return true se o Atendimento foi removido com sucesso.
     */
    public static boolean del(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("DELETE FROM atendimento WHERE id_atendimento = ?;");
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch(Exception e) {
            System.out.println("Erro ao remover atendimento! :(");
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
        
    /**
     * Obtem todos os registros de Atendimento solicitados por uma Pessoa.
     * @param id identificador de Pessoa.
     * @return A lista de Atendimentos solicitados.
     */
    public static List<Atendimento> getSolicitados(int id) {
        List<Atendimento> atds = new ArrayList<Atendimento>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT * FROM atendimento WHERE id_pessoa = ?;");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Atendimento atd = new Atendimento();
                atd.setId(rs.getInt("id_atendimento"));
                atd.setDatahora(rs.getTimestamp("datahora"));
                atd.setDescricao(rs.getString("descricao"));
                atd.setSolucao(rs.getString("solucao"));
                Situacao sit = SituacaoDAO.get(rs.getInt("id_situacao"));
                atd.setSituacao(sit);
                Tipo tip = TipoDAO.get(rs.getInt("id_tipo"));
                atd.setTipo(tip);
                Produto pdt = ProdutoDAO.get(rs.getInt("id_produto"));
                atd.setProduto(pdt);
                Pessoa psa = PessoaDAO.get(rs.getInt("id_pessoa"));
                atd.setPessoa(psa);
                atds.add(atd);
            }                
        } catch(Exception e) {
            System.out.println("Erro ao obter atendimentos! :(");
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
        return atds;
    }

    public static int getAberto() {
        int total;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT count(*) FROM atendimento;");
            rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("count(*)");
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
        return 0;
    }

    public static int getAbertoT() {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("SELECT count(*) from atendimento where id_situacao = 1");
            rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("count(*)");
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
        return 0;
    }

    public static float getPerc() {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("select format(count(a.id_situacao)/t.total*100,'2') as percentual from atendimento a, (select count(*) as total from atendimento) t where id_situacao = 1 group by id_situacao;");
            rs = st.executeQuery();
            if (rs.next()) {
                return rs.getFloat("percentual");
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
        return 0;
    }

    public static List<PesquisaGerente> buscarTodosTipo() {
        List<PesquisaGerente> resultados = new ArrayList<PesquisaGerente>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("select t.nome,count(case when id_situacao = 1 then a.id_tipo end) as aberto, count(*) as fechado\n" +
" from tipo t, atendimento a where t.id_tipo = a.id_tipo group by t.nome;");
            rs = st.executeQuery();
            while (rs.next()) {
                PesquisaGerente pesquisa = new PesquisaGerente();
                pesquisa.setCampoS1(rs.getString("t.nome"));
                pesquisa.setCampoI1(rs.getInt("aberto"));
                pesquisa.setCampoI2(rs.getInt("fechado"));
                resultados.add(pesquisa);
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

    
    public static List<Atendimento> buscarTodosAberto() {
        List<Atendimento> resultados = new ArrayList<Atendimento>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(" select a.*,ps.nome as penome,ps.cpf as pecpf,ps.email as peemail, ps.telefone as petelefone,t.nome as tnome,t.id_tipo as tid,p.nome as pnome,p.id_produto as pid \n" +
" from atendimento a,pessoa ps,tipo t, produto p\n" +
" where a.id_situacao = 1 and t.id_tipo = a.id_tipo and p.id_produto = a.id_produto and a.id_pessoa = ps.id_pessoa\n" +
" order by datahora asc;");
            rs = st.executeQuery();
            while (rs.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setDatahora(rs.getTimestamp("datahora"));
                atendimento.setDescricao(rs.getString("descricao"));
                atendimento.setSolucao(rs.getString("solucao"));
                Tipo tipo = new Tipo();
                tipo.setNome(rs.getString("tnome"));
                tipo.setId(rs.getInt("tid"));
                Produto produto = new Produto();
                produto.setNome(rs.getString("pnome"));
                produto.setId(rs.getInt("pid"));
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id_pessoa"));
                pessoa.setNome(rs.getString("penome"));
                pessoa.setCpf(rs.getString("pecpf"));
                pessoa.setEmail(rs.getString("peemail"));
                pessoa.setTelefone(rs.getString("petelefone"));
                atendimento.setProduto(produto);
                atendimento.setTipo(tipo);
                atendimento.setPessoa(pessoa);
                resultados.add(atendimento);
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
    
    public static List<Atendimento> buscarTodos() {
        List<Atendimento> resultados = new ArrayList<Atendimento>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement("select a.*,ps.nome as penome,ps.cpf as pecpf,ps.email as peemail, ps.telefone as petelefone,t.nome as tnome,t.id_tipo as tid,p.nome as pnome,p.id_produto as pid\n" +
"from atendimento a,pessoa ps,tipo t, produto p\n" +
"where  t.id_tipo = a.id_tipo and p.id_produto = a.id_produto and a.id_pessoa = ps.id_pessoa\n" +
"order by datahora asc;");
            rs = st.executeQuery();
            while (rs.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setDatahora(rs.getTimestamp("datahora"));
                atendimento.setDescricao(rs.getString("descricao"));
                atendimento.setSolucao(rs.getString("solucao"));
                Tipo tipo = new Tipo();
                tipo.setNome(rs.getString("tnome"));
                tipo.setId(rs.getInt("tid"));
                Produto produto = new Produto();
                produto.setNome(rs.getString("pnome"));
                produto.setId(rs.getInt("pid"));
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id_pessoa"));
                pessoa.setNome(rs.getString("penome"));
                pessoa.setCpf(rs.getString("pecpf"));
                pessoa.setEmail(rs.getString("peemail"));
                pessoa.setTelefone(rs.getString("petelefone"));
                atendimento.setProduto(produto);
                atendimento.setTipo(tipo);
                atendimento.setPessoa(pessoa);
                resultados.add(atendimento);
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
