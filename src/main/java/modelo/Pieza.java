package modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Pieza {
    int idPieza;
    String tipo;
    float costo;
    int existencia;
}
