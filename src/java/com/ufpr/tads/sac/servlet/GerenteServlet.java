/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.sac.servlet;

import com.ufpr.tads.sac.beans.Cidade;
import com.ufpr.tads.sac.beans.Endereco;
import com.ufpr.tads.sac.beans.Estado;
import com.ufpr.tads.sac.beans.Mensagem;
import com.ufpr.tads.sac.beans.Perfil;
import com.ufpr.tads.sac.beans.Pessoa;
import com.ufpr.tads.sac.facade.AtendimentoFacade;
import com.ufpr.tads.sac.facade.PerfilFacade;
import com.ufpr.tads.sac.facade.PessoaFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rhone
 */
@WebServlet(name = "GerenteServlet", urlPatterns = {"/GerenteServlet"})
public class GerenteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        if ("listarFunc".equals(action)) {
            Pessoa pessoa = (Pessoa) request.getSession().getAttribute("Func");
            request.setAttribute("listaFunc", PessoaFacade.getAllFunci(pessoa.getId()));
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/gerente/funcger.jsp");
            rd.forward(request, response);
        }
        if ("show".equals(action)) {
            int idFunc = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("func", PessoaFacade.getFuncGer(idFunc));
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/gerente/funcgerVisualizar.jsp");
            rd.forward(request, response);
        }
        if ("formUpdate".equals(action)) {
            int idFunc = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("func", PessoaFacade.getFuncGer(idFunc));
            request.setAttribute("perfil", PerfilFacade.getAllPerf());
            request.setAttribute("estados", PessoaFacade.buscarEstados());
            request.setAttribute("estados", PessoaFacade.buscarEstados());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/gerente/funcgerAlterar.jsp");
            rd.forward(request, response);
        }
        if ("update".equals(action)) {
            Pessoa psa = new Pessoa();
            Endereco end = new Endereco();
            end.setRua(request.getParameter("rua"));
            String numero = request.getParameter("numero");
            numero = numero.replaceAll("[_]", "");
            numero = numero.replaceAll(" ", "");
            end.setNumero(Integer.parseInt(numero));
            String cepOK = request.getParameter("cep").replaceAll("[.-]", "");
            end.setCep(cepOK);
            end.setComplemento(request.getParameter("complemento"));
            end.setBairro(request.getParameter("bairro"));
            end.setId(Integer.parseInt(request.getParameter("ided")));
            Cidade cid = new Cidade();
            cid.setId(Integer.parseInt(request.getParameter("cidade")));
            Estado est = new Estado();
            est.setId(Integer.parseInt(request.getParameter("estado")));
            cid.setEstado(est);
            end.setCidade(cid);
            psa.setId(Integer.parseInt(request.getParameter("id")));
            Perfil per = new Perfil();
            per.setId(Integer.parseInt(request.getParameter("perfil")));
            String cpf = request.getParameter("cpf");
            String cpfOK = cpf.replaceAll("[.-]", "");
            psa.setCpf(cpfOK);
            psa.setNome(request.getParameter("nome"));
            psa.setEmail(request.getParameter("email"));
            String telefone = request.getParameter("telefone").replaceAll("[()-]", "");
            telefone = telefone.replaceAll(" ", "");
            psa.setTelefone(telefone);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String str = request.getParameter("data");
            Date data = null;
            try {
                data = format.parse(str);
            } catch (ParseException e) {
                System.out.println("Erro ao converter data para cadastro do cliente! :(");
            }
            psa.setData(data);
            psa.setEndereco(end);
            psa.setPerfil(per);
            psa.setSenha(request.getParameter("senha"));
            PessoaFacade.Alterar(psa);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/GerenteServlet?action=listarFunc");
            rd.forward(request, response);
        }

        if ("remove".equals(action)) {
            int idPessoa = Integer.parseInt(request.getParameter("id"));
            PessoaFacade.remover(idPessoa);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/GerenteServlet?action=listarFunc");
            rd.forward(request, response);
        }

        if ("formNew".equals(action)) {
            request.setAttribute("estados", PessoaFacade.buscarEstados());
            request.setAttribute("perfil", PerfilFacade.getAllPerf());
            request.setAttribute("estados", PessoaFacade.buscarEstados());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/gerente/funcgerAlterar.jsp");
            rd.forward(request, response);
        }

        if ("new".equals(action)) {
            Pessoa psa = new Pessoa();
            Endereco end = new Endereco();
            end.setRua(request.getParameter("rua"));
            String numero = request.getParameter("numero");
            numero = numero.replaceAll("[_]", "");
            numero = numero.replaceAll(" ", "");
            end.setNumero(Integer.parseInt(numero));
            String cepOK = request.getParameter("cep").replaceAll("[.-]", "");
            end.setCep(cepOK);
            end.setComplemento(request.getParameter("complemento"));
            end.setBairro(request.getParameter("bairro"));
            Cidade cid = new Cidade();
            cid.setId(Integer.parseInt(request.getParameter("cidade")));
            Estado est = new Estado();
            est.setId(Integer.parseInt(request.getParameter("estado")));
            cid.setEstado(est);
            end.setCidade(cid);
            Perfil per = new Perfil();
            per.setId(Integer.parseInt(request.getParameter("perfil")));
            String cpf = request.getParameter("cpf");
            String cpfOK = cpf.replaceAll("[.-]", "");
            psa.setCpf(cpfOK);
            psa.setNome(request.getParameter("nome"));
            String telefone = request.getParameter("telefone").replaceAll("[()-]", "");
            telefone = telefone.replaceAll(" ", "");
            psa.setTelefone(telefone);
            psa.setEmail(request.getParameter("email"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String str = request.getParameter("data");
            Date data = null;
            try {
                data = format.parse(str);
            } catch (ParseException e) {
                System.out.println("Erro ao converter data para cadastro do cliente! :(");
            }
            psa.setData(data);
            psa.setEndereco(end);
            psa.setPerfil(per);
            psa.setSenha(request.getParameter("senha"));
            PessoaFacade.InserirFunc(psa);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/GerenteServlet?action=listarFunc");
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
