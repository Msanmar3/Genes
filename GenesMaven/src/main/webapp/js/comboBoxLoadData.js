$(document).ready(function () {

    $(document).on("change", "#species", function () {
        var keyword = $("#species").val();
        console.log(keyword);
        var params = {
            "keyword": keyword
        };
//llamada al fichero JSP con AJAX
        if (keyword != null) {
            $.ajax({
                data: params,
                url: 'servletSearchAuthors',
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
        }

    });

    $(document).on("change", "#authors", function () {
        var author = $("#authors").val();
        var specie = $("#species").val();

        console.log(author);
        console.log(specie);

        var params = {
            "author": author,
            "specie": specie
        };
//llamada al fichero JSP con AJAX
        if (author != null && specie != null) {
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
                    console.log("points!!!!" + points);
                    $("#iterations").html(points);
                }
            });
        }

    });
});