package com.ufpr.tads.sac.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ufpr.tads.sac.facade.AutenticacaoFacade;
import com.ufpr.tads.sac.facade.EnderecoFacade;
import com.ufpr.tads.sac.facade.PessoaFacade;
import com.ufpr.tads.sac.beans.Autenticacao;
import com.ufpr.tads.sac.beans.Mensagem;
import com.ufpr.tads.sac.beans.Pessoa;
import com.ufpr.tads.sac.beans.Endereco;
import com.ufpr.tads.sac.beans.Cidade;
import com.ufpr.tads.sac.facade.AtendimentoFacade;

/**
 * Controlador de requisicoes relacionadas a autenticacao.
 */
@WebServlet(name = "AutenticacaoServlet", urlPatterns = {"/AutenticacaoServlet"})
public class AutenticacaoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        Mensagem msg = new Mensagem();

        if ("login".equals(action)) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            Autenticacao a = AutenticacaoFacade.login(email, senha);
            if (a == null) {
                msg.setTitulo("Erro");
                msg.setDescricao("Email/senha incorretos!");
                msg.setContexto("danger");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", msg);
                rd.forward(request, response);
            } else {
                HttpSession s = request.getSession();
                s.setAttribute("autenticacao", a);
                String autt = null;
                msg.setTitulo("Sucesso");
                msg.setDescricao("Autenticado com sucesso!");
                msg.setContexto("success");
                request.setAttribute("msg", msg);
                String page = null;
                if (a.getPerfil().isCliente()) {
                    page = "/ClienteServlet?action=atendimentos";
                }
                if (a.getPerfil().isFuncionario()) {
                    page = "/FuncionarioServlet?action=atendimentos";
                    autt = "isFun";
                }
                if (a.getPerfil().isGerente()) {
                    page = "/AtendimentoServlet?action=inicial";
                    autt = "isGer";
                    s.setAttribute("Func",PessoaFacade.getGer(email, senha));
                    request.setAttribute("total",AtendimentoFacade.getTotal());
                    request.setAttribute("EmAberto",AtendimentoFacade.getAberto());
                    request.setAttribute("Perc",AtendimentoFacade.getPerc());
                    request.setAttribute("Tipos",AtendimentoFacade.buscarTodosTipo());
                }
                s.setAttribute("autt", autt);
                RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
                rd.forward(request, response);
            }
        }
        if ("cadastroForm".equals(action)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
            rd.forward(request, response);
        }
        if ("cadastro".equals(action)) {
            Pessoa psa = new Pessoa();
            String aux;
            psa.setNome(request.getParameter("nome"));
            aux = request.getParameter("cpf");
            aux = aux.replaceAll("[.-]", "");
            psa.setCpf(aux);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            aux = request.getParameter("data");
            Date data = null;
            try {
                data = format.parse(aux);
            } catch (ParseException e) {
                System.out.println("Erro ao converter data! :(");
            }
            psa.setData(data);
            aux = request.getParameter("telefone");
            aux = aux.replaceAll("[()-]", "");
            aux = aux.replaceAll(" ", "");
            psa.setTelefone(aux);
            psa.setEmail(request.getParameter("email"));
            psa.setSenha(request.getParameter("senha"));
            Endereco end = new Endereco();
            end.setRua(request.getParameter("rua"));
            aux = request.getParameter("numero");
            aux = aux.replaceAll("[_]", "");
            aux = aux.replaceAll(" ", "");
            end.setNumero(Integer.parseInt(aux));
            aux = request.getParameter("cep");
            aux = aux.replaceAll("[-]", "");
            end.setCep(aux);
            end.setComplemento(request.getParameter("complemento"));
            end.setBairro(request.getParameter("bairro"));
            int idCidade = Integer.parseInt(request.getParameter("cidade"));
            Cidade cid = EnderecoFacade.buscarCidade(idCidade);
            end.setCidade(cid);
            psa.setEndereco(end);
            psa.setPerfil(PessoaFacade.buscarPerfil(1));
            Autenticacao a = AutenticacaoFacade.cadastro(psa);
            if (a == null) {
                msg.setTitulo("Erro");
                msg.setDescricao("Erro ao cadastrar, tente novamente!");
                msg.setContexto("danger");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            } else {
                msg.setTitulo("Sucesso");
                msg.setDescricao("Cadastrado com sucesso, autentique-se para continuar!");
                msg.setContexto("success");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        }
        if ("logout".equals(action)) {
            HttpSession s = request.getSession(false);
            if (s != null) {
                s.invalidate();
                msg.setTitulo("Sucesso");
                msg.setDescricao("Desconectado com sucesso!");
                msg.setContexto("success");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
