$(document).ready(function () {

    $(document).on("change", "#species", function () {
        var keyword = $("#species").val();
        console.log(keyword);
        var params = {
            "keyword": keyword
        };
//llamada al fichero JSP con AJAX
        $.ajax({
            data: params,
            url: 'servletSearchIterations',
            dataType: 'text',
            type: 'post',
            beforeSend: function () {
//mostramos gif "cargando"
                $('#loading_spinner').show();
            },
            success: function (response) {
//escondemos gif
                $('#loading_spinner').fadeOut("slow");
//mostramos salida del PHP
                var points = response.toString() + "";

                $("#iterations").html(points);
            }
        });

    });
    
    $(document).on("change", "#iterations", function () {
        var keyword = $("#iterations").val();
        console.log(keyword);
        var params = {
            "keyword": keyword
        };
//llamada al fichero JSP con AJAX
        $.ajax({
            data: params,
            url: 'servletSearchAuthores',
            dataType: 'text',
            type: 'post',
            beforeSend: function () {
//mostramos gif "cargando"
                $('#loading_spinner').show();
            },
            success: function (response) {
//escondemos gif
                $('#loading_spinner').fadeOut("slow");
//mostramos salida del JSP
                var points = response.toString() + "";

                $("#authors").html(points);
            }
        });

    });
});