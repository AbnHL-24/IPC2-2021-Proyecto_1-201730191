package modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Usuario {
    String usuario;
    String password;
    int tipoUsuario;
    int estadoUsuario;
}
