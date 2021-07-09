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

            $('.myFormEdit #idUnidadEdit').val(unidad.idUsuario);
            $('.myFormEdit #dominioEdit').val(unidad.dominio);
            $('.myFormEdit #descripcionEdit').val(unidad.descripcion);
            $('.myFormEdit #tipoUnidadEdit').val(unidad.tipoUnidad.idTipoUnidad);
            $('.myFormEdit #estadoUnidadEdit').val(unidad.estadoUnidad.idEstadoUnidad);
            //$('.myFormEdit #tipoUnidadEdit option[value=' + unidad.tipoUnidad.idTipoUnidad + ']').prop('selected', 'selected');
            //$('.myFormEdit #estadoUnidadEdit option[value=' + unidad.estadoUnidad.idEstadoUnidad + ']').prop('selected', 'selected');
        });
        $('.myFormEdit #editModal').find('.modal-header').css('background', '#0d6efd');
        $('.myFormEdit #editModal').find('.modal-header').css('color', 'white');
        $('.myFormEdit #editModal').find('.close').css('color', 'white');
        $('.myFormEdit #editModal').modal();
    });
    $('.table .deleteBtn').on('click', function (event) {

        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (unidad) {

            $('.myFormDelete #idUnidadDelete').val(unidad.idTipoUnidad);
            $('.myFormDelete #deleteModal').modal();
        });
        $('#deleteModal').find('.modal-header').css('background', '#0d6efd');
        $('#deleteModal').find('.modal-header').css('color', 'white');
        $('#deleteModal').find('.close').css('color', 'white');
    });
});
