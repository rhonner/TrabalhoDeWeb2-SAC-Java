<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/erro.jsp" %>

<c:if test="${empty autenticacao}">
    <jsp:useBean id="msg" class="com.ufpr.tads.sac.beans.Mensagem" scope="request"/>
    <c:set target="${msg}" property="titulo" value="Acesso negado"/>
    <c:set target="${msg}" property="descricao" value="Identifique-se para acessar o sistema!"/>
    <c:set target="${msg}" property="contexto" value="danger"/>
    <jsp:forward page="/index.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <title>Beauty Embuste - SAC</title>
        <meta charset="UTF-8">
        <meta name="author" content="David Cabral & Rhonner Matheus">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/sac/css/bootstrap.min.css">
        <link rel="stylesheet" href="/sac/css/animate.css">
        <link rel="stylesheet" href="/sac/css/styles.css">
        <link rel="stylesheet" href="/sac/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
    </head>
    <body>

        <c:if test="${!empty msg}">
            <div class="alert alert-${msg.contexto} alert-dismissible shadow fade show" role="alert">
                <h5>${msg.titulo}</h5>
                ${msg.descricao}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>

        <section id="gerente">

            <nav class="navbar navbar-expand-lg navbar-light bg-transparent shadow sticky-top mb-3">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler"
                        aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarToggler">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item dropdown px-4">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-headset"></i> Atendimentos
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="AtendimentoServlet?action=inicial"><i class="fa fa-signature"></i> Dados gerenciais</a>                                
                                <a class="dropdown-item" href="AtendimentoServlet?action=listarAberto"><i class="fa fa-clock"></i> Abertos</a>
                                <a class="dropdown-item" href="AtendimentoServlet?action=listarTudo"><i class="fa fa-list"></i> Todos</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown px-4">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-users"></i> Funcionários/Gerentes
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="GerenteServlet?action=formNew"><i class="fa fa-plus"></i> Novo</a>
                                <a class="dropdown-item" href="GerenteServlet?action=listarFunc"><i class="fa fa-list"></i> Todos</a>
                            </div>
                        </li>
                        <li class="nav-item px-4">
                            <a class="nav-link" href="/sac/modulo/gerente/relats.jsp"><i class="fa fa-file-alt"></i> Relatórios</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav">
                        <span class="navbar-text px-4">
                            Bem vindo, <b>${autenticacao.nome}</b>
                        </span>
                        <li class="nav-item px-4">
                            <a class="nav-link" href="AutenticacaoServlet?action=logout">Sair <i class="fa fa-sign-out-alt"></i></a>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="container border border-dark rounded shadow p-5">

                <h3 class="text-center"><i class="fa fa-users"></i> Funcionários/Gerentes</h3>
                <div class="table-responsive-lg">
                    <table class="table table-sm table-bordered table-hover text-center bg-light">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">Data</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Email</th>
                                <th scope="col">Cargo</th>
                                <th scope="col">Opções</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaFunc}" var="list">
                                <tr>
                                    <fmt:formatDate value="${list.data}" pattern="dd/MM/yyyy" var="dt"/>
                                    <td>${dt}</td>
                                    <td>${list.nome}</td>
                                    <td class="cpf">${list.cpf}</td>
                                    <td>${list.email}</td>
                                    <td>${list.perfil.nome}</td>
                                    <td>
                                        <a href="GerenteServlet?action=show&id=${list.id}" class="btn btn-sm btn-dark" title="Visualizar"><i class="fa fa-list-ul"></i></a>
                                        <a href="GerenteServlet?action=formUpdate&id=${list.id}" class="btn btn-sm btn-dark" title="Alterar"><i class="fa fa-edit"></i></a>
                                        <button type="button" onclick="modalRemover(${list.id})" class="btn btn-sm btn-dark" title="Remover"><i class="fa fa-trash-alt"></i></button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
            </div>

        </section>

        <script src="/sac/js/jquery-3.3.1.min.js"></script>
        <script src="/sac/js/popper.min.js"></script>
        <script src="/sac/js/bootstrap.min.js"></script>
        <script src="/sac/js/mensagem.js"></script>
        <script src="/sac/js/jquery.inputmask.bundle.js"></script>
        <script src="/sac/js/jquery.dataTables.min.js"></script>
        <script src="/sac/js/dataTables.bootstrap4.min.js"></script>
        <script src="/sac/js/tabela.js"></script>
        <script>
            function modalRemover(id) {
                var r = confirm("Voce tem certeza que deseja remover o funcionário/gerente?\nA ação não pode ser revertida!");
                if (r == true) {
                    window.location = "GerenteServlet?action=remove&id=" + id;
                }
            }
        </script>
        <script>
            $(document).ready(function () {
                $('.cpf').inputmask('999.999.999-99');
                $('.cep').inputmask('99999-999');
            });
        </script>
    </body>
</html>
