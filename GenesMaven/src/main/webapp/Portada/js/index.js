
    $(document).ready(function () {
        $("#formLogin").validate({
            rules: {
                user: "required",
                password: "required"
            },
            messages: {
                user: "Por favor introduzca su nombre",
                password:"Por favor introduzca la contraseña."              
            },
            errorElement: "div",
            errorPlacement: function (error, element) {
                // Add the `help-block` class to the error element
                error.addClass("help-block");

                if (element.prop("type") === "checkbox") {
                    error.insertAfter(element.parent("label"));
                } else {
                    error.insertAfter(element);
                }
            },
            highlight: function (element, errorClass, validClass) {
                $(element).parents(".form-group").addClass("has-error").removeClass("has-success");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).parents(".form-group").addClass("has-success").removeClass("has-error");
            }

        });

        $("#reset").click(function () {
            var validator = $("#formLogin").validate();
            validator.resetForm();
        });

    });

