package modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DetalleEnsamble {
    int idDetalleEnsamble;
    int idEnsamble;
    int idPieza;
    int costoVenta;
}
