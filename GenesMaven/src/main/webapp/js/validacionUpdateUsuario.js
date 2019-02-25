$(function () {
    $("form[name='formUpdateUser']").validate({
        rules: {
            name: {
                required: true
            },
            email: {
                required: true,
                email: true
            },
            rol: {
                required: true
            }
        },
        messages: {
            name: {
                required: "Introduzca el usuario"
            },
            email: {
                required: "Introduce el email"
            },
            rol: {
                required: "Selecciona rol"
            }
        },
        errorElement: "div",
        errorPlacement: function (error, element) {
            // Add the `help-block` class to the error element
            error.addClass("has-error");
//            if (element.prop("type") === "checkbox") {
                error.insertAfter(element.parent("div"));
//            } else {
//                error.insertAfter(element);
//            }
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