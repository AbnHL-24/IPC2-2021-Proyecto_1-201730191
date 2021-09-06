package utils.validaciones;

import static utils.validaciones.ValidacionDeTipos.*;

public class ValidacionMueble {

    public static boolean validarMueble(String[] parametros) {
        boolean flag = false;
        if (parametros.length == 2) {
            if (isFloat(parametros[1])) {
                flag = true;
            }
        }        return flag;
    }
}
