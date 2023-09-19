<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
    /* Estilo personalizado para el sidebar */
    .sidebar {
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        z-index: 100;
        padding: 20px;
        background-color: #f8f9fa;
        width: 250px;
        transition: all 0.3s;
    }

    /* Ajusta el contenido principal para dar espacio al sidebar */
    .content {
        margin-left: 250px; /* Ancho del sidebar */
    }

    .bodyContent{
        margin-left: 250px; /* Ancho del sidebar */
        padding: 20px;
    }

</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    function mostrarFormulario(formularioId) {
        var formularioN = $('#' + formularioId);

        if (formularioN.is(":visible")) {
            formularioN.hide();
        } else {

            $('.formulario').hide();
            formularioN.show();
        }
    }

</script>