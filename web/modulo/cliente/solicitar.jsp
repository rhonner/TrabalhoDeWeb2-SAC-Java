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

        <section id="solicitar">

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
                <form action="AtendimentoServlet?action=novo" method="post">

                    <h3 class="text-center"><i class="fa fa-plus"></i> Solicitar atendimento</h3>

                    <div class="form-row mb-3">
                        <div class="form-group col-md-12">
                            <label for="input-data"><i class="fa fa-calendar-alt"></i> Data:</label>
                            <fmt:formatDate value="${datahora}" pattern="dd/MM/yyyy HH:mm:ss" var="dh"/>
                            <input type="text" name="data" value="${dh}" class="form-control border-dark"
                                   id="input-data" readonly>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="input-tipo"><i class="fa fa-list"></i> Tipo:</label>
                            <select name="tipo" class="custom-select bg-transparent border-dark"
                                    id="input-tipo" required>
                                <c:forEach items="${tipos}" var="tip">
                                    <option value="${tip.id}">${tip.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="input-produto"><i class="fa fa-cube"></i> Produto:</label>
                            <select name="produto" class="custom-select bg-transparent border-dark"
                                    id="input-produto" required>
                                <c:forEach items="${produtos}" var="prd">
                                    <option value="${prd.id}">${prd.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-row mb-3">
                        <div class="form-group col-md-12">
                            <label for="input-descricao"><i class="fa fa-pen"></i> Descrição: </label>
                            <textarea name="descricao" class="form-control bg-transparent border-dark"
                                      id="input-descricao" maxlength="255" rows="3" required></textarea>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-dark col-md-4 offset-md-4">Solicitar <i class="fa fa-check"></i></button>

                </form>
            </div>       

        </section>

        <script src="/sac/js/jquery-3.3.1.min.js"></script>
        <script src="/sac/js/popper.min.js"></script>
        <script src="/sac/js/bootstrap.min.js"></script>
        <script src="/sac/js/mensagem.js"></script>
    </body>
</html>
