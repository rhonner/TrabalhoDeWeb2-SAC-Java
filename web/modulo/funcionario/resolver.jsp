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

        <section id="atender">

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
                                <a class="dropdown-item" href="FuncionarioServlet?action=atendimentosAbertos"><i class="fa fa-clock"></i> Abertos</a>
                                <a class="dropdown-item" href="FuncionarioServlet?action=atendimentos"><i class="fa fa-list"></i> Todos</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown px-4">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-cubes"></i> Produtos
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="FuncionarioServlet?action=novoProduto"><i class="fa fa-plus"></i> Novo</a>
                                <a class="dropdown-item" href="FuncionarioServlet?action=produtos"><i class="fa fa-list"></i> Todos</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown px-4">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-clipboard-list"></i> Categorias
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="FuncionarioServlet?action=novaCategoria"><i class="fa fa-plus"></i> Nova</a>
                                <a class="dropdown-item" href="FuncionarioServlet?action=categorias"><i class="fa fa-list"></i> Todas</a>
                            </div>
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
                <form action="AtendimentoServlet?action=resolver" method="post">

                    <h3 class="text-center"><i class="fa fa-headset"></i> Resolver atendimento</h3>
                    <div class="row m-0">

                        <div class="col-md-6">
                            <h5>Cliente</h5>
                            <div class="form-row mb-3">
                                <input type="hidden" name="id" value="${atendimento.id}">
                                <div class="form-group col-md-6">
                                    <label for="input-data"><i class="fa fa-calendar-alt"></i> Data:</label>
                                    <fmt:formatDate value="${atendimento.datahora}" pattern="dd/MM/yyyy HH:mm:ss" var="dh"/>
                                    <input type="text" name="data" value="${dh}" class="form-control border-dark"
                                           id="input-data" readonly>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="input-pessoa"><i class="fa fa-user"></i> Pessoa:</label>
                                    <input type="text" name="pessoa" value="${atendimento.pessoa.nome}" class="form-control border-dark"
                                           id="input-pessoa" readonly>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="input-tipo"><i class="fa fa-list"></i> Tipo:</label>
                                    <input type="text" name="tipo" value="${atendimento.tipo.nome}" class="form-control border-dark"
                                           id="input-tipo" readonly>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="input-produto"><i class="fa fa-cube"></i> Produto:</label>
                                    <input type="text" name="produto" value="${atendimento.produto.nome}" class="form-control border-dark"
                                           id="input-produto" readonly>
                                </div>
                            </div>
                            <div class="form-row mb-3">
                                <div class="form-group col-md-12">
                                    <label for="input-descricao"><i class="fa fa-pen"></i> Descrição:</label>
                                    <textarea name="descricao" class="form-control border-dark"
                                              id="input-descricao" rows="3" readonly>${atendimento.descricao}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <h5>Funcionário</h5>
                            <div class="form-row mb-3">
                                <div class="form-group col-md-12">
                                    <label for="input-descricao"><i class="fa fa-thumbs-up"></i> Solução:</label>
                                    <textarea name="solucao" class="form-control bg-transparent border-dark"
                                              id="input-solucao" maxlength="255" rows="7" required>${atendimento.solucao}</textarea>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-dark col-md-12">Resolver <i class="fa fa-check"></i></button>

                        </div>
                                
                    </div>

                </form>
            </div>       

        </section>

        <script src="/sac/js/jquery-3.3.1.min.js"></script>
        <script src="/sac/js/popper.min.js"></script>
        <script src="/sac/js/bootstrap.min.js"></script>
        <script src="/sac/js/mensagem.js"></script>
    </body>
</html>
