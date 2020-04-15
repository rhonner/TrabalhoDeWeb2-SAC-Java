package com.ufpr.tads.sac.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ufpr.tads.sac.beans.Mensagem;
import com.ufpr.tads.sac.facade.AtendimentoFacade;
import java.sql.Timestamp;
import com.ufpr.tads.sac.facade.CategoriaFacade;
import com.ufpr.tads.sac.facade.ProdutoFacade;

/**
 * Controlador de requisicoes relacionadas ao funcionario.
 */
@WebServlet(name = "FuncionarioServlet", urlPatterns = {"/FuncionarioServlet"})
public class FuncionarioServlet extends HttpServlet {

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

        if ("atendimentos".equals(action)) {
            java.sql.Timestamp timestampp = new Timestamp(System.currentTimeMillis() - 604800000);
            request.setAttribute("timestampp", timestampp);
            request.setAttribute("atendimentos", AtendimentoFacade.get());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/funcionario/atendimentos.jsp");
            rd.forward(request, response);
        }
        if ("atendimentosAbertos".equals(action)) {
            java.sql.Timestamp timestampp = new Timestamp(System.currentTimeMillis() - 604800000);
            request.setAttribute("timestampp", timestampp);
            request.setAttribute("atendimentos", AtendimentoFacade.get());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/funcionario/atendimentosAbertos.jsp");
            rd.forward(request, response);
        }
        if ("resolver".equals(action)) {
            int idAtendimento = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("atendimento", AtendimentoFacade.get(idAtendimento));
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/funcionario/resolver.jsp");
            rd.forward(request, response);
        }
        
        if ("novoProduto".equals(action)) {
            request.setAttribute("categorias", CategoriaFacade.buscarCategorias());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/funcionario/produtoNovo.jsp");
            rd.forward(request, response);
        }
        if ("produtos".equals(action)) {
            request.setAttribute("produtos", ProdutoFacade.buscarProdutos());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/funcionario/produtos.jsp");
            rd.forward(request, response);
        }

        if ("novaCategoria".equals(action)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/funcionario/categoriaNova.jsp");
            rd.forward(request, response);
        }
        if ("categorias".equals(action)) {
            request.setAttribute("categorias", CategoriaFacade.buscarCategorias());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/funcionario/categorias.jsp");
            rd.forward(request, response);
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
