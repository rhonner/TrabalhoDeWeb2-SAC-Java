$(document).ready(function(){
    $("#input-cpf").inputmask("999.999.999-99");
    $("#input-telefone").inputmask("(9{2}) 9{4,5}-9{4}");
    $("#input-numero").inputmask("99999");
    $("#input-cep").inputmask("99999-999");
    
    $("#input-cpf").change(function(){
        var v = $("#input-cpf").val();
        if (!validarCPF(v)) {
            var msg = "<div class=\"alert alert-danger alert-dismissible shadow fade show\" role=\"alert\"><h5>Erro</h5>CPF inválido, favor corrigir<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button></div>";
            $(document.body).prepend(msg);
            $(".alert").fadeTo(3000, 500).fadeOut(500);
        };
    });
    
    getEstados();
    $("#input-estado").change(function () {
        getCidades();
    });
});

function validarCPF(cpf) {	
    cpf = cpf.replace(/[^\d]+/g,'');
    if (cpf == '') return false;	
    if (cpf.length != 11 || 
        cpf == "00000000000" || 
        cpf == "11111111111" || 
        cpf == "22222222222" || 
        cpf == "33333333333" || 
        cpf == "44444444444" || 
        cpf == "55555555555" || 
        cpf == "66666666666" || 
        cpf == "77777777777" || 
        cpf == "88888888888" || 
        cpf == "99999999999")
            return false;		
    add = 0;	
    for (i=0; i < 9; i ++)		
        add += parseInt(cpf.charAt(i)) * (10 - i);	
        rev = 11 - (add % 11);	
        if (rev == 10 || rev == 11)		
            rev = 0;	
        if (rev != parseInt(cpf.charAt(9)))		
            return false;		
    add = 0;	
    for (i = 0; i < 10; i ++)		
        add += parseInt(cpf.charAt(i)) * (11 - i);	
    rev = 11 - (add % 11);	
    if (rev == 10 || rev == 11)	
        rev = 0;	
    if (rev != parseInt(cpf.charAt(10)))
        return false;		
    return true;   
}

function getEstados() {
    $.ajax({
        url: "AjaxServlet",
        data: {
            action: "estados"
        },
        dataType: 'json',
        success: function (data) {
            $("#input-estado").empty();
            $.each(data, function (i, obj) {
                $("#input-estado").append('<option value=' + obj.id + '>' + obj.nome + '</option>');
            });
        },
        error: function (request, textStatus, errorThrown) {
            alert(request.status + ', Error: ' + request.statusText);
        }
    });
}

function getCidades() {
    var idEstado = $("#input-estado").val();
    $.ajax({
        url: "AjaxServlet",
        data: {
            action: "cidades",
            estado: idEstado
        },
        dataType: 'json',
        success: function (data) {
            $("#input-cidade").empty();
            $.each(data, function (i, obj) {
                $("#input-cidade").append('<option value=' + obj.id + '>' + obj.nome + '</option>');
            });
        },
        error: function (request, textStatus, errorThrown) {
            alert(request.status + ', Error: ' + request.statusText);
        }
    });
}
