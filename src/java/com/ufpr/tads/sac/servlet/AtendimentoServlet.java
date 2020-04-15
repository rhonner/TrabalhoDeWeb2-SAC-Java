package com.ufpr.tads.sac.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ufpr.tads.sac.beans.Mensagem;
import com.ufpr.tads.sac.beans.Atendimento;
import com.ufpr.tads.sac.beans.Autenticacao;
import com.ufpr.tads.sac.beans.Produto;
import com.ufpr.tads.sac.beans.Tipo;
import com.ufpr.tads.sac.facade.AtendimentoFacade;
import com.ufpr.tads.sac.facade.PessoaFacade;
import com.ufpr.tads.sac.facade.ProdutoFacade;
import com.ufpr.tads.sac.facade.SituacaoFacade;
import com.ufpr.tads.sac.facade.TipoFacade;

/**
 * Controlador de requisicoes relacionadas ao cliente.
 */
@WebServlet(name = "AtendimentoServlet", urlPatterns = {"/AtendimentoServlet"})
public class AtendimentoServlet extends HttpServlet {

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

        HttpSession s = request.getSession(false);
        if (s == null || s.getAttribute("autenticacao") == null) {
            msg.setTitulo("Acesso negado");
            msg.setDescricao("Identifique-se para acessar o sistema!");
            msg.setContexto("danger");
            request.setAttribute("msg", msg);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }

        if ("listarAberto".equals(action)) {
            request.setAttribute("lista", AtendimentoFacade.get());
            java.sql.Timestamp timestampp = new Timestamp(System.currentTimeMillis() - 604800000);
            request.setAttribute("timestampp", timestampp);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/gerente/Abertos.jsp");
            rd.forward(request, response);
        }
        if ("listarTudo".equals(action)) {
            request.setAttribute("lista", AtendimentoFacade.get());
            java.sql.Timestamp timestampp = new Timestamp(System.currentTimeMillis() - 604800000);
            request.setAttribute("timestampp", timestampp);
            if ("isGer".equals(request.getSession().getAttribute("autt"))) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/gerente/todos.jsp");
                rd.forward(request, response);
            }
            if ("isFun".equals(request.getSession().getAttribute("autt"))) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/funcionario/atendimentos.jsp");
                rd.forward(request, response);

            }
        }
        if ("inicial".equals(action)) {
            request.setAttribute("lista", AtendimentoFacade.buscarTodosAberto());
            request.setAttribute("total", AtendimentoFacade.getTotal());
            request.setAttribute("EmAberto", AtendimentoFacade.getAberto());
            request.setAttribute("Perc", AtendimentoFacade.getPerc());
            request.setAttribute("Tipos", AtendimentoFacade.buscarTodosTipo());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/gerente/index.jsp");
            rd.forward(request, response);
        }
        if ("novo".equals(action)) {
            Atendimento atd = new Atendimento();
            String aux;
            aux = request.getParameter("data");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date data = null;
            try {
                data = format.parse(aux);
            } catch (ParseException e) {
                System.out.println("Erro ao converter data para atendimento! :(");
            }
            atd.setDatahora(data);
            atd.setDescricao(request.getParameter("descricao"));
            atd.setSolucao("");
            int idTipo = Integer.parseInt(request.getParameter("tipo"));
            Tipo tip = TipoFacade.buscarTipo(idTipo);
            atd.setTipo(tip);
            int idProduto = Integer.parseInt(request.getParameter("produto"));
            Produto prd = ProdutoFacade.buscarProduto(idProduto);
            atd.setProduto(prd);
            Autenticacao a = (Autenticacao) s.getAttribute("autenticacao");
            atd.setPessoa(PessoaFacade.buscarPessoa(a.getId()));
            atd.setSituacao(SituacaoFacade.buscarSituacao(1));
            if (AtendimentoFacade.novo(atd)) {
                msg.setTitulo("Sucesso");
                msg.setDescricao("Atendimento solicitado com sucesso!");
                msg.setContexto("success");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ClienteServlet?action=atendimentos");
                rd.forward(request, response);
            } else {
                msg.setTitulo("Erro");
                msg.setDescricao("Erro ao solicitar atendimento, tente novamente!");
                msg.setContexto("danger");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ClienteServlet?action=atendimentos");
                rd.forward(request, response);
            }
        }
        if ("resolver".equals(action)) {
            int idAtendimento = Integer.parseInt(request.getParameter("id"));
            Atendimento atd = AtendimentoFacade.get(idAtendimento);
            atd.setSolucao(request.getParameter("solucao"));
            atd.setSituacao(SituacaoFacade.buscarSituacao(2));
            if (AtendimentoFacade.resolver(atd)) {
                msg.setTitulo("Sucesso");
                msg.setDescricao("Atendimento resolvido com sucesso!");
                msg.setContexto("success");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/FuncionarioServlet?action=atendimentos");
                rd.forward(request, response);
            } else {
                msg.setTitulo("Erro");
                msg.setDescricao("Erro ao resolver atendimento, tente novamente!");
                msg.setContexto("danger");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/FuncionarioServlet?action=atendimentos");
                rd.forward(request, response);
            }
        }
        if ("remover".equals(action)) {
            int idAtendimento = Integer.parseInt(request.getParameter("id"));
            if (AtendimentoFacade.remover(idAtendimento)) {
                msg.setTitulo("Sucesso");
                msg.setDescricao("Produto removido com sucesso!");
                msg.setContexto("success");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ClienteServlet?action=atendimentos");
                rd.forward(request, response);
            } else {
                msg.setTitulo("Erro");
                msg.setDescricao("Erro ao remover produto, tente novamente!");
                msg.setContexto("danger");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ClienteServlet?action=atendimentos");
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
