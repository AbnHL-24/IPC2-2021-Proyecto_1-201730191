package utils.validaciones;


import static utils.validaciones.ValidacionDeTipos.*;

public class ValidacionUsuario {

    public static boolean validarUsuario (String[] parametros) {
        boolean flag  = false;

        if (parametros.length == 3) {
            if (isInt(parametros[2])) {
                flag = true;
            }
        }
        return flag;
    }
}
