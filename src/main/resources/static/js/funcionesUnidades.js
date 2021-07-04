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
        $.get(href, function (usuario) {

            $('.myFormEdit #idUsuarioEdit').val(usuario.idUsuario);
            $('.myFormEdit #nombreEdit').val(usuario.nombre);
            $('.myFormEdit #apellidoEdit').val(usuario.apellido);
            $('.myFormEdit #emailEdit').val(usuario.email);
            $('.myForm #rolesEdit').val('');
            for (const rol of usuario.roles) {
                $('.myFormEdit #rolesEdit option[value=' + rol.idRol + ']').prop('selected', 'selected')
            }
        });
        $('.myFormEdit #editModal').find('.modal-header').css('background', '#0d6efd');
        $('.myFormEdit #editModal').find('.modal-header').css('color', 'white');
        $('.myFormEdit #editModal').find('.close').css('color', 'white');
        $('.myFormEdit #editModal').modal();
    });
    $('.table .deleteBtn').on('click', function (event) {

        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (usuario) {

            $('.myFormDelete #idUsuarioDelete').val(usuario.idUsuario);
            $('.myFormDelete #deleteModal').modal();
        });
        $('#deleteModal').find('.modal-header').css('background', '#0d6efd');
        $('#deleteModal').find('.modal-header').css('color', 'white');
        $('#deleteModal').find('.close').css('color', 'white');
    });
});
