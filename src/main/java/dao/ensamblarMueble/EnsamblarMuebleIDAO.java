package dao.ensamblarMueble;

import dao.Conexion;
import modelo.DetalleFactura;
import modelo.EnsamblarMueble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnsamblarMuebleIDAO implements EnsamblarMuebleDAO{
    private static EnsamblarMuebleIDAO ensamblarMuebleIDAO = null;
    private Connection conexion = Conexion.getConexion();

    private EnsamblarMuebleIDAO() {
    }

    public static EnsamblarMuebleIDAO getEnsamblarMuebleIDAO() {
        if (ensamblarMuebleIDAO == null) {
            ensamblarMuebleIDAO = new EnsamblarMuebleIDAO();
        }
        return ensamblarMuebleIDAO;
    }

    @Override
    public void crear(EnsamblarMueble eM) {
        String consulta = "INSERT INTO EnsamblarMueble VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, eM.getIdEnsambleMueble());
            ps.setString(2, eM.getFecha().toString());
            ps.setString(3, eM.getMueble());
            ps.setString(4, eM.getUsuario());
            ps.setInt(5, eM.getEstadoVenta());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public EnsamblarMueble leer(String id) {
        String sql = "SELECT * FROM EnsamblarMueble WHERE id = ?";

        EnsamblarMueble ensamblarMueble = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ensamblarMueble = EnsamblarMueble.builder()
                            .idEnsambleMueble(rs.getInt("id"))
                            .fecha(LocalDate.parse(rs.getString("fecha")))
                            .mueble(rs.getString("mueble"))
                            .usuario(rs.getString("usuario"))
                            .estadoVenta(rs.getInt("estado_venta"))
                            .build();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ensamblarMueble;
    }

    @Override
    public void actualizar(EnsamblarMueble ensamblarMueble) {

    }

    @Override
    public void borrar(String id) {

    }

    @Override
    public List<EnsamblarMueble> getList() {
        String sql = "SELECT * FROM EnsamblarMueble";
        List<EnsamblarMueble> ensamblarMuebles = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ensamblarMuebles = new ArrayList();

            while (rs.next()) {
                ensamblarMuebles.add(EnsamblarMueble.builder()
                        .idEnsambleMueble(rs.getInt("id"))
                        .fecha(LocalDate.parse(rs.getString("fecha")))
                        .mueble(rs.getString("mueble"))
                        .usuario(rs.getString("usuario"))
                        .estadoVenta(rs.getInt("estado_venta"))
                        .build());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ensamblarMuebles;
    }

    @Override
    public boolean existe(String id) {
        String sql = "SELECT id FROM EnsamblarMueble WHERE id = ?";
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
