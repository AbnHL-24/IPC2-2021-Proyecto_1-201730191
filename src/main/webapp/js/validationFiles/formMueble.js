$('document').ready(function () {

    $.validator.addMethod("notEmpty", value => {
        return value.trim().length !== 0;
    }, "El campo no puede contener solo espacios en blanco");

    $("#form-mueble").validate({

        rules: {
            nombre: {
                required: true,
                notEmpty: true
            },
            precio: {
                required: true,
                number: true,
                min: 1
            }
        }
    });
});