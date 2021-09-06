package dao.pieza;

import dao.Conexion;
import dao.ensamblarMueble.EnsamblarMuebleIDAO;
import modelo.Pieza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String consulta = "INSERT INTO Pieza VALUES(?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, p.getIdPieza());
            ps.setString(2, p.getTipo());
            ps.setInt(3, p.getCosto());
            ps.setInt(4, p.getExistencia());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Pieza leer(String id) {
        return null;
    }

    @Override
    public void actualizar(Pieza pieza) {

    }

    @Override
    public void borrar(String id) {

    }

    @Override
    public List<Pieza> getList() {
        return null;
    }

    @Override
    public boolean existe(String id) {
        return false;
    }
}
