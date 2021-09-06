package dao.usuario;

import dao.Conexion;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public Usuario leer(String username) {
        String sql = "SELECT * FROM Usuario WHERE usuario = ?";

        Usuario user = null;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, username);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = Usuario.builder()
                            .usuario(rs.getString("usuario"))
                            .password(rs.getString("password"))
                            .tipoUsuario(rs.getInt("tipo_de_usuario"))
                            .estadoUsuario(rs.getInt("estado"))
                            .build();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return user;
    }

    @Override
    public void actualizar(Usuario usuario) {
        String sql = "UPDATE Usuario SET tipo_de_usuario = ?, password=?, estado=? WHERE usuario = ?";
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, usuario.getTipoUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getEstadoUsuario());
            ps.setString(4, usuario.getUsuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void borrar(String id) {

    }

    @Override
    public List<Usuario> getList() {
        String sql = "SELECT * FROM Usuario";
        List<Usuario> users = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            users = new ArrayList();

            while (rs.next()) {
                users.add(Usuario.builder()
                        .usuario(rs.getString("usuario"))
                        .password(rs.getString("password"))
                        .estadoUsuario(rs.getInt("estado"))
                        .tipoUsuario(rs.getInt("tipo_de_usuario"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return users;
    }

    @Override
    public boolean existe(String id) {
        String sql = "SELECT COUNT(*) FROM Usuario where usuario = ?";
        boolean flag = false;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            try ( ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    flag = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return flag;
    }

    @Override
    public List<Usuario> getAdmins() {
        String sql = "SELECT * FROM Usuario WHERE tipo_de_usuario = ?";
        List<Usuario> users = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setInt(1, 1);
            try ( ResultSet rs = ps.executeQuery()) {
                users = new ArrayList();

                while (rs.next()) {
                    users.add(Usuario.builder()
                            .usuario(rs.getString("usuario"))
                            .password(rs.getString("password"))
                            .estadoUsuario(rs.getInt("estado"))
                            .tipoUsuario(rs.getInt("tipo_de_usuario"))
                            .build());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return users;
    }
}
