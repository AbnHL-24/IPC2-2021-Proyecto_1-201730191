package dao.usuario;

import dao.Conexion;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UsuarioIDAO implements UsuarioDAO{
    private static UsuarioIDAO usuarioIDAO = null;
    private Connection conexion = Conexion.getConexion();

    private UsuarioIDAO() {
    }

    public static UsuarioIDAO getUsuarioIDAO() {
        if (usuarioIDAO == null) {
            usuarioIDAO = new UsuarioIDAO();
        }
        return usuarioIDAO;
    }

    @Override
    public void crear(Usuario u) {
        String consulta = "INSERT INTO Usuario VALUES(?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getPassword());
            ps.setInt(3, u.getTipoUsuario() );
            ps.setInt(4, u.getEstadoUsuario());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Usuario leer(String id) {
        return null;
    }

    @Override
    public void actualizar(Usuario usuario) {

    }

    @Override
    public void borrar(String id) {

    }

    @Override
    public List<Usuario> getList() {
        return null;
    }

    @Override
    public boolean existe(String id) {
        return false;
    }
}
