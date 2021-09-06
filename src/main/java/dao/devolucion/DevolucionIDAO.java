package dao.devolucion;

import dao.Conexion;
import modelo.Devolucion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevolucionIDAO implements DevolucionDAO{
    private static DevolucionIDAO devolucionIDAO = null;
    private Connection conexion = Conexion.getConexion();

    private DevolucionIDAO() {
    }

    public static DevolucionIDAO getDevolucionIDAO() {
        if (devolucionIDAO == null) {
            devolucionIDAO = new DevolucionIDAO();
        }
        return devolucionIDAO;
    }

    @Override
    public void crear(Devolucion d) {
        String consulta = "INSERT INTO Devoluciones VALUES(?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, d.getIdDevolucion());
            ps.setInt(2, d.getIdFactura());
            ps.setInt(3, d.getCodigoEnsamble());
            ps.setInt(4, d.getPiezaDefectuosa());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Devolucion leer(String id) {
        String sql = "SELECT * FROM Devoluciones WHERE id = ?";

        Devolucion devolucion = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    devolucion = Devolucion.builder()
                            .idDevolucion(rs.getInt("id"))
                            .idFactura(rs.getInt("id_factura"))
                            .codigoEnsamble(rs.getInt("codigo_ensamble"))
                            .piezaDefectuosa(rs.getInt("pieza_defectuosa"))
                            .build();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return devolucion;
    }

    @Override
    public void actualizar(Devolucion devolucion) {

    }

    @Override
    public void borrar(String id) {

    }

    @Override
    public List<Devolucion> getList() {
        String sql = "SELECT * FROM Devoluciones";
        List<Devolucion> devolucions = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            devolucions = new ArrayList();

            while (rs.next()) {
                devolucions.add(Devolucion.builder()
                        .idDevolucion(rs.getInt("id"))
                        .idFactura(rs.getInt("id_factura"))
                        .codigoEnsamble(rs.getInt("codigo_ensamble"))
                        .piezaDefectuosa(rs.getInt("pieza_defectuosa"))
                        .build());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return devolucions;
    }

    @Override
    public boolean existe(String id) {
        String sql = "SELECT id FROM Devoluciones WHERE id = ?";
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
