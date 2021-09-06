$('document').ready(function () {

    $.validator.addMethod("notEmpty", value => {
        return value.trim().length !== 0;
    }, "El campo no puede contener solo espacios en blanco");

    $("#form-login").validate({

        rules: {
            username: {
                required: true,
                notEmpty: true
            },
            password: {
                required: true,
                notEmpty: true
            }
        },
        messages: {
            username: {
                required: "El nombre de usuario es obligatorio"
            },
            password: {
                required: "La contraseña es obligatoria"
            }
        }
    });
});