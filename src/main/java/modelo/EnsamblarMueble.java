package modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class EnsamblarMueble {
    int idEnsambleMueble;
    LocalDate fecha;
    String mueble;
    String usuario;
    int estadoVenta;
}
