<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/erro.jsp" %>

<c:set var="sessao" value="${request.getSession(false)}"/>
<c:if test="${!empty sessao}">
    <c:remove var="sessao" scope="session"/>
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
        
        <section id="cadastro">
            <div class="container bg-transparent border border-dark rounded shadow p-5">
                <form action="AutenticacaoServlet?action=cadastro" method="post">
                                        
                    <h1>Beauty Embuste</h1>
                    <h5>Serviço de Atendimento ao Cliente</h5>
                    <hr>

                    <div class="row m-0">
                        
                        <div class="col-md-8 pr-5">
                            <h5>Dados pessoais</h5>
                            <div class="form-row mb-3">
                                <div class="form-group col-md-12">
                                    <label for="input-nome"><i class="fa fa-user"></i> Nome completo:</label>
                                    <input type="text" name="nome" class="form-control bg-transparent border-dark"
                                           id="input-nome" placeholder="Exemplo da Silva" maxlength="255" required>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="input-cpf"><i class="fa fa-address-card"></i> CPF:</label>
                                    <input type="text" name="cpf" class="form-control bg-transparent border-dark"
                                           id="input-cpf" placeholder="xxx.xxx.xxx-xx" required>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="input-data"><i class="fa fa-calendar-alt"></i> Data de nascimento:</label>
                                    <input type="date" name="data" class="form-control bg-transparent border-dark"
                                           id="input-data" placeholder="dd/mm/aaaa" required>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="input-telefone"><i class="fa fa-phone"></i> Telefone:</label>
                                    <input type="text" name="telefone" class="form-control bg-transparent border-dark"
                                           id="input-telefone" placeholder="(xx) xxxxx-xxxx" required>
                                </div>
                            </div>
                            <h5>Endereço</h5>
                            <div class="form-row mb-3">
                                <div class="form-group col-md-6">
                                    <label for="input-rua"><i class="fa fa-road"></i> Rua:</label>
                                    <input type="text" name="rua" class="form-control bg-transparent border-dark"
                                           id="input-rua" placeholder="Av. Exemplo" maxlength="255" required>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="input-numero"><i class="fa fa-home"></i> Numero:</label>
                                    <input type="text" name="numero" class="form-control bg-transparent border-dark"
                                           id="input-numero" placeholder="xxxxx" required>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="input-cep"><i class="fa fa-map-marker-alt"></i> CEP:</label>
                                    <input type="text" name="cep" class="form-control bg-transparent border-dark"
                                           id="input-cep" placeholder="xxxxx-xxx" required>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="input-estado"><i class="fa fa-globe"></i> Estado:</label>
                                    <select class="custom-select bg-transparent border-dark" name="estado" id="input-estado" required>
                                        <!-- cidades -->
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="input-cidade"><i class="fa fa-city"></i> Cidade:</label>
                                    <select class="custom-select bg-transparent border-dark" name="cidade" id="input-cidade" required>
                                        <!-- estados -->
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="input-bairro"><i class="fa fa-map-signs"></i> Bairro:</label>
                                    <input type="text" name="bairro" class="form-control bg-transparent border-dark"
                                           id="input-bairro" placeholder="Vila Exemplo" maxlenght="255" required>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="input-complemento"><i class="fa fa-bullseye"></i> Complemento:</label>
                                    <input type="text" name="complemento" class="form-control bg-transparent border-dark"
                                           id="input-complemento" placeholder="Apto. Exemplo" maxlenght="50">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 align-self-center border border-dark rounded shadow p-4">
                            <h5>Conta</h5>
                            <div class="form-row mb-3">
                                <div class="form-group col-md-12">
                                    <label for="input-email"><i class="fa fa-envelope"></i> Email:</label>
                                    <input type="email" name="email" class="form-control bg-transparent border-dark"
                                           id="input-email" placeholder="exemplo@exemplo" maxlenght="255" required>
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="input-senha"><i class="fa fa-key"></i> Senha:</label>
                                    <input type="password" name="senha" class="form-control bg-transparent border-dark"
                                           id="input-senha" placeholder="**********" maxlenght="50" required>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-dark col-md-12"><i class="fa fa-check"></i> Cadastrar</a></button>

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
