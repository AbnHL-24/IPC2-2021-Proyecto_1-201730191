package dao.piezaEnsamble;

import dao.Conexion;
import modelo.PiezaEnsamble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String consulta = "INSERT INTO PiezaParaEnsamble(mueble, pieza, cantidad) VALUES(?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, pE.getMubele());
            ps.setString(2, pE.getPieza());
            ps.setString(3, pE.getCantidad());
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

    public boolean existe(String mueble, String pieza) {
        String sql = "SELECT id FROM PiezaParaEnsamble WHERE mueble = ? AND pieza = ?";
        boolean flag = false;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, mueble);
            ps.setString(2, pieza);
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
}
