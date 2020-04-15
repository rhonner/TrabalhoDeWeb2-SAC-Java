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
                                <a class="dropdown-item" href="/sac/AtendimentoServlet?action=inicial"><i class="fa fa-signature"></i> Dados gerenciais</a>                                
                                <a class="dropdown-item" href="/sac/AtendimentoServlet?action=listarAberto"><i class="fa fa-clock"></i> Abertos</a>
                                <a class="dropdown-item" href="/sac/AtendimentoServlet?action=listarTudo"><i class="fa fa-list"></i> Todos</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown px-4">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-users"></i> Funcionários/Gerentes
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="/sac/GerenteServlet?action=formNew"><i class="fa fa-plus"></i> Novo</a>
                                <a class="dropdown-item" href="/sac/GerenteServlet?action=listarFunc"><i class="fa fa-list"></i> Todos</a>
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
                            <a class="nav-link" href="/sac/AutenticacaoServlet?action=logout">Sair <i class="fa fa-sign-out-alt"></i></a>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="container border border-dark rounded shadow p-5">

                <h3 class="text-center"><i class="fa fa-users"></i> Detalhes do Funcionário/Gerente</h3>
                <div class="col-md-6 offset-md-3">

                    <h5 class="border-bottom border-dark">Dados pessoais</h5>
                    <div class="row pb-3">
                        <div class="col-md-5"><b>CPF: </b><span class="cpf">${func.cpf}</span></div>
                        <div class="col-md-7"><b>Nome: </b>${func.nome}</div>
                        <div class="col-md-5"><b>Email: </b>${func.email}</div>
                        <fmt:formatDate value="${func.data}" pattern="dd/MM/yyyy" var="dt"/>
                        <div class="col-md-7"><b>Data de nascimento: </b>${dt}</div>
                    </div>
                    <h5 class="border-bottom border-dark">Endereço</h5>
                    <div class="row pb-3">
                        <div class="col-md-5"><b>Rua: </b>${func.endereco.rua}</div>
                        <div class="col-md-4"><b>CEP: </b><span class="cep">${func.endereco.cep}</span></div>
                        <div class="col-md-5"><b>Cidade: </b>${func.endereco.cidade.nome}</div>
                        <div class="col-md-7"><b>Estado: </b>${func.endereco.cidade.estado.nome}</div>
                        <div class="col-md-5"><b>Bairro: </b>${func.endereco.bairro}</div>
                        <div class="col-md-7"><b>Complemento: </b>${func.endereco.complemento}</div>
                    </div>

                </div>

            </div>

        </section>

        <script src="/sac/js/jquery-3.3.1.min.js"></script>
        <script src="/sac/js/popper.min.js"></script>
        <script src="/sac/js/bootstrap.min.js"></script>
        <script src="/sac/js/mensagem.js"></script>
        <script src="js/jquery.inputmask.bundle.js"></script>
        <script>
            $(document).ready(function () {
                $('.cpf').inputmask('999.999.999-99');
                $('.cep').inputmask('99999-999');
            });
        </script>
    </body>
</html>
