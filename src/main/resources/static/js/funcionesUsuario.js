$(document).ready(function () {

//cargar jquery data table
    $('#listaUsuarios').DataTable({
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
            $('.myFormEdit #rolesEdit').val('');
            for (const rol of usuario.roles) {
                $('.myFormEdit #rolesEdit option[value=' + rol.idRol + ']').prop('selected', 'selected');
            }
        });
        $('.myFormEdit #editModal').find('.modal-header').css('background', '#0d6efd');
        $('.myFormEdit #editModal').find('.modal-header').css('color', 'white');
        $('.myFormEdit #editModal').find('.close').css('color', 'white');
        $('.myFormEdit #editModal').modal();
    });

    $('.createBtn').on('click', function (event) {

        $('#formUsuarios').trigger("reset");

        $('#createModal').modal();

        $('.myFormCreate #createModal').find('.modal-header').css('background', '#28a745');
        $('.myFormCreate #createModal').find('.modal-header').css('color', 'white');
        $('.myFormCreate #createModal').find('.close').css('color', 'white');
    });

    $('.table .deleteBtn').on('click', function (event) {

        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (usuario) {

            $('.myFormDelete #idUsuarioDelete').val(usuario.idUsuario);
            $('.myFormDelete #emailDelete').val(usuario.email + "?");
            $('.myFormDelete #deleteModal').modal();
        });
        $('#deleteModal').find('.modal-header').css('background', '#dc3545');
        $('#deleteModal').find('.modal-header').css('color', 'white');
        $('#deleteModal').find('.close').css('color', 'white');

        //funcion para auto ajustar inputs
        let width = $('#temporally');
        $('#emailDelete').css('width', width.css('width'));
        $('#emailDelete').val(width.text());
    });
});
