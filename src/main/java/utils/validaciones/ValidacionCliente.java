package utils.validaciones;

import static utils.validaciones.ValidacionDeTipos.isFloat;

public class ValidacionCliente {

    public static boolean validarCliente(String[] parametros) {
        boolean flag = false;
        if (parametros.length == 3) {
            flag = true;
        } else if (parametros.length == 5) {
            flag = true;
        }
        return flag;
    }
}
