package dao.piezaEnsamble;

import dao.Conexion;
import modelo.PiezaEnsamble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PiezaEnsambleIDAO implements PiezaEnsambleDAO{
    private static PiezaEnsambleIDAO piezaEnsambleIDAO = null;
    private Connection conexion = Conexion.getConexion();

    private PiezaEnsambleIDAO() {
    }

    public static PiezaEnsambleIDAO getPiezaEnsambleIDAO() {
        if (piezaEnsambleIDAO == null) {
            piezaEnsambleIDAO = new PiezaEnsambleIDAO();
        }
        return piezaEnsambleIDAO;
    }

    @Override
    public void crear(PiezaEnsamble pE) {
        String consulta = "INSERT INTO PiezaParaEnsamble VALUES(?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, pE.getIdPiezaParaEnsamble());
            ps.setString(2, pE.getMubele().toString());
            ps.setString(3, pE.getPieza());
            ps.setString(4, pE.getCantidad());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public PiezaEnsamble leer(String id) {
        return null;
    }

    @Override
    public void actualizar(PiezaEnsamble piezaEnsamble) {

    }

    @Override
    public void borrar(String id) {

    }

    @Override
    public List<PiezaEnsamble> getList() {
        return null;
    }

    @Override
    public boolean existe(String id) {
        return false;
    }
}
