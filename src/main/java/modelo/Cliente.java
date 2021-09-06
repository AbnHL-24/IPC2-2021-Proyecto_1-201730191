package modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Cliente {
    String nitCliente;
    String nombre;
    String direccion;
    String municipio;
    String departamento;
}
