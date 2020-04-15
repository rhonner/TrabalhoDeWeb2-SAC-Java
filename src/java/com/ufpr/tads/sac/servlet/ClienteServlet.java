package com.ufpr.tads.sac.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

import com.ufpr.tads.sac.beans.Mensagem;
import com.ufpr.tads.sac.beans.Autenticacao;
import com.ufpr.tads.sac.beans.Cidade;
import com.ufpr.tads.sac.beans.Endereco;
import com.ufpr.tads.sac.beans.Estado;
import com.ufpr.tads.sac.beans.Perfil;
import com.ufpr.tads.sac.beans.Pessoa;
import com.ufpr.tads.sac.facade.AtendimentoFacade;
import com.ufpr.tads.sac.facade.PessoaFacade;
import com.ufpr.tads.sac.facade.ProdutoFacade;
import com.ufpr.tads.sac.facade.TipoFacade;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Controlador de requisicoes relacionadas ao cliente.
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {

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
            request.setAttribute("atendimentos", AtendimentoFacade.get());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/cliente/atendimentos.jsp");
            rd.forward(request, response);
        }
        if ("solicitar".equals(action)) {
            request.setAttribute("datahora", new Date());
            request.setAttribute("tipos", TipoFacade.buscarTodosTipos());
            request.setAttribute("produtos", ProdutoFacade.buscarProdutos());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/cliente/solicitar.jsp");
            rd.forward(request, response);
        }
        if ("perfil".equals(action)) {
            Autenticacao a = (Autenticacao)s.getAttribute("autenticacao");
            request.setAttribute("cliente", PessoaFacade.buscarPessoa(a.getId()));
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/cliente/perfil.jsp");
            rd.forward(request, response);
        }
        if ("detalhes".equals(action)) {
            int idAtendimento = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("atendimento", AtendimentoFacade.get(idAtendimento));
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modulo/cliente/detalhes.jsp");
            rd.forward(request, response);
        }
        if ("alterar".equals(action)) {
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
            per.setId(Integer.parseInt(request.getParameter("idper")));
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
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/ClienteServlet?action=atendimentos");
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
