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

        <section id="cliente">

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
                                <a class="dropdown-item" href="ClienteServlet?action=solicitar"><i class="fa fa-plus"></i> Solicitar</a>
                                <a class="dropdown-item" href="ClienteServlet?action=atendimentos"><i class="fa fa-list"></i> Todos</a>
                            </div>
                        </li>
                        <li class="nav-item px-4">
                            <a class="nav-link" href="ClienteServlet?action=perfil"><i class="fa fa-user"></i> Perfil</a>
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

                <h3 class="text-center"><i class="fa fa-list"></i> Atendimentos</h3>
                <div class="table-responsive-lg">
                    <table class="table table-sm table-bordered table-hover text-center bg-light">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Data/Hora</th>
                                <th scope="col">Descrição</th>
                                <th scope="col">Solução</th>
                                <th scope="col">Situação</th>
                                <th scope="col">Tipo</th>
                                <th scope="col">Produto</th>
                                <th scope="col">Opções</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${atendimentos}" var="atd">
                                <c:if test="${atd.pessoa.id == autenticacao.id}">
                                    <tr>
                                        <td scope="row">${atd.id}</td>
                                        <fmt:formatDate value="${atd.datahora}" pattern="dd/MM/yyyy HH:mm:ss" var="data"/>
                                        <td>${data}</td>
                                        <td>${atd.descricao}</td>
                                        <td>${atd.solucao}</td>
                                        <td>${atd.situacao.nome}</td>
                                        <td>${atd.tipo.nome}</td>
                                        <td>${atd.produto.nome}</td>
                                        <td>
                                            <a href="ClienteServlet?action=detalhes&id=${atd.id}" class="btn btn-sm btn-dark" title="Detalhes"><i class="fa fa-eye"></i></a>
                                            <c:if test="${atd.situacao.id == 1}">
                                                <button type="button" onclick="remover(${atd.id})" class="btn btn-sm btn-dark" title="Remover"><i class="fa fa-trash-alt"></i></button>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:if>
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
        <script src="/sac/js/jquery.dataTables.min.js"></script>
        <script src="/sac/js/dataTables.bootstrap4.min.js"></script>
        <script src="/sac/js/tabela.js"></script>
        <script>
            function remover(id) {
                var r = confirm("Voce tem certeza que deseja remover o atendimento?\nA ação não pode ser revertida!");
                if (r == true) {
                    window.location = "AtendimentoServlet?action=remover&id=" + id;
                }
            }
        </script>
    </body>
</html>
