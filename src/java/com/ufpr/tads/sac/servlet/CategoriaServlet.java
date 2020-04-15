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
import com.ufpr.tads.sac.beans.Produto;
import com.ufpr.tads.sac.beans.Categoria;
import com.ufpr.tads.sac.facade.ProdutoFacade;
import com.ufpr.tads.sac.facade.CategoriaFacade;

/**
 * Controlador de requisicoes relacionadas a categoria.
 */
@WebServlet(name = "CategoriaServlet", urlPatterns = {"/CategoriaServlet"})
public class CategoriaServlet extends HttpServlet {

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

        if ("novo".equals(action)) {
            Categoria cat = new Categoria();
            cat.setNome(request.getParameter("categoria"));
            if (CategoriaFacade.novaCategoria(cat)) {
                msg.setTitulo("Sucesso");
                msg.setDescricao("Produto cadastrado com sucesso!");
                msg.setContexto("success");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/FuncionarioServlet?action=categorias");
                rd.forward(request, response);
            } else {
                msg.setTitulo("Erro");
                msg.setDescricao("Erro ao cadastrar produto, tente novamente!");
                msg.setContexto("danger");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/FuncionarioServlet?action=categorias");
                rd.forward(request, response);
            }
        }
        if ("detalhes".equals(action)) {
            int idCategoria = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("categoria", CategoriaFacade.buscarCategoria(idCategoria));
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/funcionario/categoriaDetalhes.jsp");
            rd.forward(request, response);
        }
        if ("alterar".equals(action)) {
            int idCategoria = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("categoria", CategoriaFacade.buscarCategoria(idCategoria));
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/funcionario/categoriaAlterar.jsp");
            rd.forward(request, response);
        }
        if ("alterarCategoria".equals(action)) {
            Categoria cat = new Categoria();
            int idCategoria = Integer.parseInt(request.getParameter("id"));
            cat.setId(idCategoria);
            cat.setNome(request.getParameter("categoria"));
            if (CategoriaFacade.alterarCategoria(cat)) {
                msg.setTitulo("Sucesso");
                msg.setDescricao("Produto alterado com sucesso!");
                msg.setContexto("success");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/FuncionarioServlet?action=categorias");
                rd.forward(request, response);
            } else {
                msg.setTitulo("Erro");
                msg.setDescricao("Erro ao alterar produto, tente novamente!");
                msg.setContexto("danger");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/FuncionarioServlet?action=categorias");
                rd.forward(request, response);
            }
        }
        if ("remover".equals(action)) {
            int idCategoria = Integer.parseInt(request.getParameter("id"));
            if (CategoriaFacade.removerCategoria(idCategoria)) {
                msg.setTitulo("Sucesso");
                msg.setDescricao("Produto removido com sucesso!");
                msg.setContexto("success");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/FuncionarioServlet?action=categorias");
                rd.forward(request, response);
            } else {
                msg.setTitulo("Erro");
                msg.setDescricao("Erro ao remover produto, tente novamente!");
                msg.setContexto("danger");
                request.setAttribute("msg", msg);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/FuncionarioServlet?action=categorias");
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
