$('document').ready(function () {

    $.validator.addMethod("notEmpty", value => {
        return value.trim().length !== 0;
    }, "El campo no puede contener solo espacios en blanco");

    $("#form-cliente").validate({

        rules: {
            nit: {
                required: true,
                number: true
            },
            nombre: {
                required: true,
                notEmpty: true
            },
            direccion: {
                required: true,
                notEmpty: true
            }
        }
    });
});