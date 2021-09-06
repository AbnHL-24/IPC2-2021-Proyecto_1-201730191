package modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PiezaEnsamble {
    int idPiezaParaEnsamble;
    String mubele;
    String pieza;
    String cantidad;
}
