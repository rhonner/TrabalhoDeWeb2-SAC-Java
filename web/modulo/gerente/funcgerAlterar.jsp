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

        <section id="funcgerAlterar">

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

            <div class="container">
                <c:choose>
                    <c:when test="${func != null}">
                        <form action="GerenteServlet?action=update" method="POST">
                            <input type="hidden" name="ided" value="${func.endereco.id}">
                            <input type="hidden" name="id" value="${func.id}">
                            <h3 class="text-center mb-0"><i class="fa fa-user"></i> Alterar</h3>
                        </c:when>
                        <c:otherwise>
                            <form action="GerenteServlet?action=new" method="POST">
                                <h3 class="text-center mb-0"><i class="fa fa-user"></i> Novo</h3>
                            </c:otherwise>
                        </c:choose>
                        <div class="row m-0">

                            <div class="col-md-8 pr-5">
                                <h5>Dados pessoais</h5>
                                <div class="form-row mb-3">
                                    <div class="form-group col-md-12">
                                        <label for="input-nome"><i class="fa fa-user"></i> Nome completo:</label>
                                        <input type="text" name="nome" value="${func.nome}" class="form-control bg-transparent border-dark"
                                               id="input-nome" placeholder="Exemplo da Silva" maxlength="255" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="input-cpf"><i class="fa fa-address-card"></i> CPF:</label>
                                        <input type="text" name="cpf" value="${func.cpf}" class="form-control bg-transparent border-dark"
                                               id="input-cpf" placeholder="xxx.xxx.xxx-xx" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="input-data"><i class="fa fa-calendar-alt"></i> Data de nascimento:</label>
                                        <fmt:formatDate value="${func.data}" pattern="yyyy-MM-dd" var="data"/>
                                        <input type="date" name="data" value="${data}" class="form-control bg-transparent border-dark"
                                               id="input-data" placeholder="dd/mm/aaaa" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="input-telefone"><i class="fa fa-phone"></i> Telefone:</label>
                                        <input type="text" name="telefone" value="${func.telefone}" class="form-control bg-transparent border-dark"
                                               id="input-telefone" placeholder="(xx) xxxxx-xxxx" required>
                                    </div>
                                </div>
                                <h5>Endereço</h5>
                                <div class="form-row mb-3">
                                    <div class="form-group col-md-6">
                                        <label for="input-rua"><i class="fa fa-road"></i> Rua:</label>
                                        <input type="text" name="rua" value="${func.endereco.rua}" class="form-control bg-transparent border-dark"
                                               id="input-rua" placeholder="Av. Exemplo" maxlength="255" required>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label for="input-numero"><i class="fa fa-home"></i> Numero:</label>
                                        <input type="text" name="numero" value="${func.endereco.numero}" class="form-control bg-transparent border-dark"
                                               id="input-numero" placeholder="xxxxx" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="input-cep"><i class="fa fa-map-marker-alt"></i> CEP:</label>
                                        <input type="text" name="cep" value="${func.endereco.cep}" class="form-control bg-transparent border-dark"
                                               id="input-cep" placeholder="xxxxx-xxx" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="input-estado"><i class="fa fa-globe"></i> Estado:</label>
                                        <select class="custom-select bg-transparent border-dark" name="estado" id="input-estado" required>
                                            <option value="${func.endereco.cidade.estado.id}" selected>${func.endereco.cidade.estado.nome}</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="input-cidade"><i class="fa fa-city"></i> Cidade:</label>
                                        <select class="custom-select bg-transparent border-dark" name="cidade" id="input-cidade" required>
                                            <option value="${func.endereco.cidade.estado.id}" selected>${func.endereco.cidade.nome}</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="input-bairro"><i class="fa fa-map-signs"></i> Bairro:</label>
                                        <input type="text" name="bairro" value="${func.endereco.bairro}" class="form-control bg-transparent border-dark"
                                               id="input-bairro" placeholder="Vila Exemplo" maxlenght="255" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="input-complemento"><i class="fa fa-bullseye"></i> Complemento:</label>
                                        <input type="text" name="complemento" value="${func.endereco.complemento}" class="form-control bg-transparent border-dark"
                                               id="input-complemento" placeholder="Apto. Exemplo" maxlenght="50">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 align-self-center border border-dark rounded shadow p-4">
                                <h5>Conta</h5>
                                <div class="form-row mb-3">
                                    <div class="form-group col-md-12">
                                        <label for="input-perfil"><i class="fa fa-users"></i> Perfil:</label>
                                        <select name="perfil" class="custom-select bg-transparent border-dark"
                                                id="input-perfil" required>
                                            <c:forEach items="${perfil}" var="per">
                                                <option value="${per.id}">${per.nome}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="input-email"><i class="fa fa-envelope"></i> Email:</label>
                                        <input type="email" name="email" value="${func.email}" class="form-control bg-transparent border-dark"
                                               id="input-email" placeholder="exemplo@exemplo" maxlenght="255" required>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="input-senha"><i class="fa fa-key"></i> Senha:</label>
                                        <input type="password" name="senha" value="" class="form-control bg-transparent border-dark"
                                               id="input-senha" placeholder="**********" maxlenght="50" required>
                                    </div>
                                </div>

                                <button type="submit" class="btn btn-dark col-md-12">Salvar <i class="fa fa-check"></i></button>

                            </div>

                        </div>

                    </form>

            </div>
        </section>

        <script src="/sac/js/jquery-3.3.1.min.js"></script>
        <script src="/sac/js/popper.min.js"></script>
        <script src="/sac/js/bootstrap.min.js"></script>
        <script src="/sac/js/jquery.inputmask.bundle.js"></script>
        <script src="/sac/js/mensagem.js"></script>
        <script src="/sac/js/forms.js"></script>
    </body>

</html>
