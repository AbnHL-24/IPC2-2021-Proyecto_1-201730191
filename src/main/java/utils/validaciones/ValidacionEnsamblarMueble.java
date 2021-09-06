package utils.validaciones;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static utils.validaciones.ValidacionDeTipos.isFecha;
import static utils.validaciones.ValidacionDeTipos.isInt;

public class ValidacionEnsamblarMueble {

    public static boolean validarEnsamblarMueble(String[] parametros) {
        boolean flag = false;
        if (parametros.length == 3) {
            if (isFecha(parametros[2])) {
                flag = true;
            }
        }
        return flag;
    }
}
