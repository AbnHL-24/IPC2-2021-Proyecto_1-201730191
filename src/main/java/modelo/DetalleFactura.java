package modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DetalleFactura {
    int idDetalle;
    int idFactura;
    int idEnsmable;
    int precioVenta;
}
