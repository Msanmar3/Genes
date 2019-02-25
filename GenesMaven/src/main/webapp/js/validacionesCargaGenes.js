$(function () {
    $("form[name='formLoadGenes']").validate({
        rules: {
            origin: {
                required: true
            },
            firstName: {
                required: true
            },
            secondName: {
                required: true
            },
            uploadFile: {
                required: true
            }
        },
        messages: {
           origin: {
                required: "Introduzca el origen"
            },
            firstName: {
                required: "Introduzca la primera parte"
            },
            secondName: {
                required: "Introduzca la segunda vez"
            },
            uploadFile: {
                required: "Selecciona el fichero"
            }
        },
        errorElement: "div",
        errorPlacement: function (error, element) {
            error.addClass("has-error");
                error.insertAfter(element.parent("div"));
        },
        highlight: function (element, errorClass, validClass) {
            $(element).parents(".col-sm-12").addClass("has-error").removeClass("has-success");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parents(".col-sm-12").addClass("has-success").removeClass("has-error");
        }
        ,
        submitHandler: function (form) {
            form.submit();
        }
    });
});