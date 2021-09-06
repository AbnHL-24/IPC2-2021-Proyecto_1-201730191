package dao.pieza;

import dao.Conexion;
import dao.ensamblarMueble.EnsamblarMuebleIDAO;
import modelo.Pieza;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PiezaIDAO implements PiezaDAO{
    private static PiezaIDAO piezaIDAO = null;
    private Connection conexion = Conexion.getConexion();

    private PiezaIDAO() {
    }

    public static PiezaIDAO getPiezaIDAO() {
        if (piezaIDAO == null) {
            piezaIDAO = new PiezaIDAO();
        }
        return piezaIDAO;
    }

    @Override
    public void crear(Pieza p) {
        String consulta = "INSERT INTO Pieza(tipo, costo, existencias) VALUES(?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, p.getTipo());
            ps.setFloat(2, p.getCosto());
            ps.setInt(3, p.getExistencia());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        }
    }

    @Override
    public Pieza leer(String id) {
        String sql = "SELECT * FROM Pieza WHERE id = ?";

        Pieza pieza = null;
        try ( PreparedStatement ps = conexion.prepareStatement(sql) ) {
            ps.setString(1, id);

            try ( ResultSet rs = ps.executeQuery() ) {
                if (rs.next()) {
                    pieza = Pieza.builder()
                            .idPieza(rs.getInt("id"))
                            .tipo(rs.getString("tipo"))
                            .costo(rs.getFloat("costo"))
                            .existencia(rs.getInt("existencias"))
                            .build();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return pieza;
    }

    @Override
    public void actualizar(Pieza p) {
        String sql = "UPDATE Pieza SET tipo = ?, costo = ?, existencias = ? WHERE id = ?";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, p.getTipo());
            ps.setFloat(2, p.getCosto());
            ps.setInt(3, p.getExistencia());
            ps.setInt(4, p.getIdPieza());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("No se pudo editar la pieza");
        }
    }

    @Override
    public void borrar(String id) {

    }

    @Override
    public List<Pieza> getList() {
        String sql = "SELECT * FROM Pieza";
        List<Pieza> piezas = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            piezas = new ArrayList();

            while (rs.next()) {
                piezas.add(Pieza.builder()
                        .idPieza(rs.getInt("id"))
                        .tipo(rs.getString("tipo"))
                        .costo(rs.getFloat("costo"))
                        .existencia(rs.getInt("existencias"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return piezas;
    }

    @Override
    public boolean existe(String id) {
        String sql = "SELECT id FROM Pieza WHERE id = ?";
        boolean flag = false;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs =  ps.executeQuery()) {
                if (rs.next()) {
                    flag = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public boolean existe(String id, String precio) {
        String sql = "SELECT id FROM Pieza WHERE tipo = ? AND costo = ?";
        boolean flag = false;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setFloat(2, Float.parseFloat(precio));
            try (ResultSet rs =  ps.executeQuery()) {
                if (rs.next()) {
                    flag = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public Pieza leer(String id, String precio) {
        String sql = "SELECT * FROM Pieza WHERE tipo = ? AND costo = ?";

        Pieza pieza = null;
        try ( PreparedStatement ps = conexion.prepareStatement(sql) ) {
            ps.setString(1, id);
            ps.setFloat(2, Float.parseFloat(precio));

            try ( ResultSet rs = ps.executeQuery() ) {
                if (rs.next()) {
                    pieza = Pieza.builder()
                            .idPieza(rs.getInt("id"))
                            .tipo(rs.getString("tipo"))
                            .costo(rs.getFloat("costo"))
                            .existencia(rs.getInt("existencias"))
                            .build();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return pieza;
    }
}
