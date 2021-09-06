package modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Factura {
    int idFactura;
    String nitCliente;
    LocalDate fecha;
    String vendedor;
}
