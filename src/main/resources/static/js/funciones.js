$(document).ready(function () {

    //cargar jquery data table
    $('#listaUsuarios').DataTable({
        //para cambiar el lenguaje a español
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros",
            "zeroRecords": "No se encontraron resultados",
            "info": "Mostrando _TOTAL_ registros",
            "infoEmpty": "Mostrando _TOTAL_ registros",
            "infoFiltered": "(Registro total: _MAX_)",
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
            $('.myForm #idUsuarioEdit').val(usuario.idUsuario);
            $('.myForm #nombreEdit').val(usuario.nombre);
            $('.myForm #apellidoEdit').val(usuario.apellido);
            $('.myForm #emailEdit').val(usuario.email);
            $('.myForm #rolesEdit').val(usuario.idRol);
        });
        $('.myForm #editModal').find('.modal-header').css('background', '#0d6efd');
        $('.myForm #editModal').find('.modal-header').css('color', 'white');
        $('.myForm #editModal').find('.close').css('color', 'white');
        $('.myForm #editModal').modal();
    });

});


//mostrar modal
function confirmaEliminar(idUsuario) {
    $("#idUsuarioDelete").val(idUsuario);
    $('#deleteModal').find('.modal-header').css('background', '#0d6efd');
    $('#deleteModal').find('.modal-header').css('color', 'white'); 
    $('#deleteModal').find('.close').css('color', 'white');
    $('#deleteModal').modal('show');
}

function eliminarUsuario() {
    var idUsuario = $("#idUsuarioDelete").val();
    window.location = "eliminarUsuario/" + idUsuario;
}