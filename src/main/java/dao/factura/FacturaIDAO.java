package dao.factura;

import dao.Conexion;
import dao.detalleFactura.DetalleFacturaIDAO;
import modelo.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FacturaIDAO implements FacturaDAO{
    private static FacturaIDAO facturaIDAO = null;
    private Connection conexion = Conexion.getConexion();

    private FacturaIDAO() {
    }

    public static FacturaIDAO getFacturaIDAO() {
        if (facturaIDAO == null) {
            facturaIDAO = new FacturaIDAO();
        }
        return facturaIDAO;
    }

    @Override
    public void crear(Factura f) {
        String consulta = "INSERT INTO Factura VALUES(?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, f.getIdFactura());
            ps.setString(2, f.getNitCliente());
            ps.setString(3, f.getFecha().toString());
            ps.setString(4, f.getVendedor());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Factura leer(String id) {
        return null;
    }

    @Override
    public void actualizar(Factura factura) {

    }

    @Override
    public void borrar(String id) {

    }

    @Override
    public List<Factura> getList() {
        return null;
    }

    @Override
    public boolean existe(String id) {
        return false;
    }
}
