package utils.validaciones;

import static utils.validaciones.ValidacionDeTipos.isInt;

public class ValidacionEmsamblePiezas {

    public static boolean validarEnsamblePiezas(String[] parametros) {
        boolean flag = false;
        if (parametros.length == 3) {
            if (isInt(parametros[2])) {
                flag = true;
            }
        }
        return flag;
    }
}
