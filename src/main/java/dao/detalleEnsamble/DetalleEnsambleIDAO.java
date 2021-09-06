package dao.detalleEnsamble;

import dao.Conexion;
import modelo.DetalleEnsamble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleEnsambleIDAO implements DetalleEnsambleDAO{
    private static DetalleEnsambleIDAO detalleEnsambleIDAO = null;
    private Connection connecxion = Conexion.getConexion();

    private DetalleEnsambleIDAO() {
    }

    public static DetalleEnsambleIDAO getDetalleEnsambleIDAO() {
        if (detalleEnsambleIDAO == null) {
            detalleEnsambleIDAO = new DetalleEnsambleIDAO();
        }
        return detalleEnsambleIDAO;
    }

    @Override
    public void crear(DetalleEnsamble dE) {
        String consulta = "INSERT INTO DetalleEnsamble(id_ensamble, id_pieza, costo_venta) VALUES(?, ?, ?)";

        try (PreparedStatement ps = connecxion.prepareStatement(consulta)) {
            ps.setInt(1, dE.getIdEnsamble());
            ps.setInt(2, dE.getIdPieza());
            ps.setInt(3, dE.getCostoVenta());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public DetalleEnsamble leer(String id) {
        String sql = "SELECT * FROM DetalleEnsamble WHERE id = ?";

        DetalleEnsamble detalleEnsamble = null;

        try (PreparedStatement ps = connecxion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    detalleEnsamble = DetalleEnsamble.builder()
                            .idDetalleEnsamble(rs.getInt("id"))
                            .idEnsamble(rs.getInt("id_ensamble"))
                            .idPieza(rs.getInt("id_pieza"))
                            .costoVenta(rs.getInt("costo_venta"))
                            .build();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return detalleEnsamble;
    }

    @Override
    public void actualizar(DetalleEnsamble detalleEnsamble) {

    }

    @Override
    public void borrar(String id) {

    }

    @Override
    public List<DetalleEnsamble> getList() {
        String sql = "SELECT * FROM DetalleEnsamble";
        List<DetalleEnsamble> detalleEnsambles = null;

        try (PreparedStatement ps = connecxion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            detalleEnsambles = new ArrayList();

            while (rs.next()) {
                detalleEnsambles.add(DetalleEnsamble.builder()
                        .idDetalleEnsamble(rs.getInt("id"))
                        .idEnsamble(rs.getInt("id_ensamble"))
                        .idPieza(rs.getInt("id_pieza"))
                        .costoVenta(rs.getInt("costo_venta"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalleEnsambles;
    }

    @Override
    public boolean existe(String id) {
        String sql = "SELECT id FROM DetalleEnsamble WHERE id = ?";
        boolean flag = false;

        try (PreparedStatement ps = connecxion.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs =  ps.executeQuery()) {
                if (rs.next()) {
                    flag = true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;
    }
}
