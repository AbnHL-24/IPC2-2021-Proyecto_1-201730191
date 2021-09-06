package dao.usuario;

import dao.CRUD;
import modelo.Usuario;

import java.util.List;

public interface UsuarioDAO extends CRUD<Usuario> {
    List<Usuario> getAdmins();
}
