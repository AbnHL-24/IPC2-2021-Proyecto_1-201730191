package dao.detalleFactura;

import dao.Conexion;
import modelo.DetalleFactura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleFacturaIDAO implements DetalleFacturaDAO{
    private static DetalleFacturaIDAO detalleFacturaIDAO = null;
    private Connection conexion = Conexion.getConexion();

    private DetalleFacturaIDAO() {
    }

    public static DetalleFacturaIDAO getDetalleFacturaIDAO() {
        if (detalleFacturaIDAO == null) {
            detalleFacturaIDAO = new DetalleFacturaIDAO();
        }
        return detalleFacturaIDAO;
    }

    @Override
    public void crear(DetalleFactura dF) {
        String consulta = "INSERT INTO DetalleFactura VALUES(?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, dF.getIdDetalle());
            ps.setInt(2, dF.getIdFactura());
            ps.setInt(3, dF.getIdEnsmable());
            ps.setInt(4, dF.getPrecioVenta());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public DetalleFactura leer(String id) {
        String sql = "SELECT * FROM DetalleFactura WHERE id_detalle = ?";

        DetalleFactura detalleFactura = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    detalleFactura = DetalleFactura.builder()
                            .idDetalle(rs.getInt("id_detalle"))
                            .idFactura(rs.getInt("id_factura"))
                            .idEnsmable(rs.getInt("id_ensamble"))
                            .precioVenta(rs.getInt("precio_venta"))
                            .build();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return detalleFactura;
    }

    @Override
    public void actualizar(DetalleFactura detalleFactura) {

    }

    @Override
    public void borrar(String id) {

    }

    @Override
    public List<DetalleFactura> getList() {
        String sql = "SELECT * FROM DetalleFactura";
        List<DetalleFactura> detalleFacturas = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            detalleFacturas = new ArrayList();

            while (rs.next()) {
                detalleFacturas.add(DetalleFactura.builder()
                        .idDetalle(rs.getInt("id_detalle"))
                        .idFactura(rs.getInt("id_factura"))
                        .idEnsmable(rs.getInt("id_ensamble"))
                        .precioVenta(rs.getInt("precio_venta"))
                        .build());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return detalleFacturas;
    }

    @Override
    public boolean existe(String id) {
        String sql = "SELECT id_detalle FROM DetalleFactura WHERE id_detalle = ?";
        boolean flag = false;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
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
