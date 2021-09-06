$('document').ready(function () {

    $.validator.addMethod("notEmpty", value => {
        return value.trim().length !== 0;
    }, "El campo no puede contener solo espacios en blanco");

    $("#form-pieza").validate({

        rules: {
            nombre: {
                required: true,
                notEmpty: true
            },
            costo: {
                required: true,
                number: true,
                min: 1
            },
            stock: {
                required: true,
                number: true,
                min: 0
            }
        }
    });
});