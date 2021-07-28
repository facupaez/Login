$(document).ready(function () {

//cargar jquery data table
    $('#listaUnidades').DataTable({
//para cambiar el lenguaje a español
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros",
            "zeroRecords": "No se encontraron resultados",
            "info": "Mostrando _TOTAL_ registros",
            "infoEmpty": "Mostrando _TOTAL_ registros",
            "infoFiltered": "(Registros totales: _MAX_)",
            "sSearch": "Buscar:",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "Último",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },
            "sProcessing": ""
        }
    });
    $('.table .editBtn').on('click', function (event) {

        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (unidad) {

            $('.myFormEdit #idUnidadEdit').val(unidad.idUnidad);
            $('.myFormEdit #dominioEdit').val(unidad.dominio);
            $('.myFormEdit #tipoUnidadEdit').val(unidad.tipoUnidad.idTipoUnidad);
            $('.myFormEdit #estadoUnidadEdit').val(unidad.estadoUnidad.idEstadoUnidad);
            $('.myFormEdit #descripcionEdit').val(unidad.descripcion);
            $('.myFormEdit #editModal').modal();

        });
        $('.myFormEdit #editModal').find('.modal-header').css('background', '#0d6efd');
        $('.myFormEdit #editModal').find('.modal-header').css('color', 'white');
        $('.myFormEdit #editModal').find('.close').css('color', 'white');
    });

    $('.createBtn').on('click', function (event) {

        $('#formUnidades').trigger("reset");

        $('#createModal').modal();

        $('.myFormCreate #createModal').find('.modal-header').css('background', '#28a745');
        $('.myFormCreate #createModal').find('.modal-header').css('color', 'white');
        $('.myFormCreate #createModal').find('.close').css('color', 'white');
    });

    $('.table .deleteBtn').on('click', function (event) {

        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (unidad) {

            $('.myFormDelete #idUnidadDelete').val(unidad.idUnidad);
            $('.myFormDelete #dominioUnidadDelete').val(unidad.dominio + "?");
            $('.myFormDelete #deleteModal').modal();
        });
        $('#deleteModal').find('.modal-header').css('background', '#dc3545');
        $('#deleteModal').find('.modal-header').css('color', 'white');
        $('#deleteModal').find('.close').css('color', 'white');

        //funcion para auto ajustar inputs
        let width = $('#temporally');
        $('#dominioUnidadDelete').css('width', width.css('width'));
        $('#dominioUnidadDelete').val(width.text());
    });
});
