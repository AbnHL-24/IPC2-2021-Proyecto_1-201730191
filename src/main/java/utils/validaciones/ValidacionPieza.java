package utils.validaciones;

import static utils.validaciones.ValidacionDeTipos.*;

public class ValidacionPieza {

    public static boolean validarPieza(String[] parametros) {
        boolean flag = false;
        if (parametros.length == 2) {
            if (isFloat(parametros[1])) {
                flag = true;
            }
        }
        return flag;
    }
}
