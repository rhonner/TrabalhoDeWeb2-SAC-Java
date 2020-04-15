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
        
        <section id="login">
            <div class="row">
                <div class="container bg-transparent border border-dark rounded shadow p-5">
                    
                    <h1 class="display-4">Beauty Embuste</h1>
                    <h5>Serviço de Atendimento ao Cliente</h5>
                    <hr class="my-4">
                    <form action="AutenticacaoServlet?action=login" method="post">
                        <div class="form-group">
                            <label for="input-email"><i class="fa fa-envelope"></i> Email:</label>
                            <input type="email" name="email" class="form-control bg-transparent border-dark"
                                   id="input-email" placeholder="exemplo@exemplo" required>
                        </div>
                        <div class="form-group">
                            <label for="input-senha"><i class="fa fa-key"></i> Senha:</label>
                            <input type="password" name="senha" class="form-control bg-transparent border-dark"
                                   id="input-senha" placeholder="************" required>
                        </div>
                        <button type="submit" class="btn btn-dark col-12 my-3">Entrar <i class="fa fa-sign-in"></i></button>
                    </form>
                    <div class="text-center">
                        Nao possui conta? <a href="AutenticacaoServlet?action=cadastroForm" class="text-dark"><b>Cadastre-se</b></a>
                    </div>
                    
                </div>
            </div>
        </section>
        
        <script src="/sac/js/jquery-3.3.1.min.js"></script>
        <script src="/sac/js/popper.min.js"></script>
        <script src="/sac/js/bootstrap.min.js"></script>
        <script src="/sac/js/mensagem.js"></script>
    </body>
</html>
