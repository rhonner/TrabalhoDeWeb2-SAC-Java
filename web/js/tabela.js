$(document).ready(function() {
    $('.table').DataTable({
        lengthMenu: [ 5, 10, 20, 30 ],
        searching: true,
        fixedHeader: false,
        ordering: true,
        pageLength: 5,
        info: true,
        paging: true,
        pagingType: "full_numbers",
        processing: true,
        responsive: true,
        language: {
            "decimal":           "",
            "emptyTable":        "Nenhum registro encontrado",
            "info":              "Exibindo _END_ de _TOTAL_ registros",
            "infoEmpty":         "Exibindo nenhum registro",
            "infoFiltered":      "- Filtrados de _MAX_ de registros",
            "infoPostFix":       "",
            "thousands":         ".",
            "lengthMenu":        "Exibir _MENU_ registros",
            "loadingRecords":    "Carregando...",
            "processing":        "Processando...",
            "search":            "_INPUT_",
            "searchPlaceholder": "Pesquisar",
            "zeroRecords":       "Nenhum registro correspondente encontrado",
            "paginate": {
                "first":         "<i class='fa fa-angle-double-left'></i>",
                "last":          "<i class='fa fa-angle-double-right'></i>",
                "next":          "<i class='fa fa-angle-right'>",
                "previous":      "<i class='fa fa-angle-left'></i>"
            }
        }
    });
});
