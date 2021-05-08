$(document).ready(function () {
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
            "sProcessing": "",
        }
    });
});

function confirmaEliminar(idUsuario) {
    $('#deleteModal').modal('show');
    $("#userIdHiddenInput").val(idUsuario);
}

function eliminarUsuario() {
    var idUsuario = $("#userIdHiddenInput").val();
    window.location = "eliminarUsuario/" + idUsuario;
}

function cerrarModal() {
    $('#deleteModal').modal('hide');
}
