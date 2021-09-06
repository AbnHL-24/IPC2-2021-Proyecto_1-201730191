package modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Devolucion {
    int idDevolucion;
    int idFactura;
    int codigoEnsamble;
    int piezaDefectuosa;
}
