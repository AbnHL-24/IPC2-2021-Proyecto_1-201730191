package dao.mueble;

import dao.Conexion;
import modelo.Mueble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MuebleIDAO implements MuebleDAO{
    private static MuebleIDAO muebleIDAO = null;
    private Connection conexion = Conexion.getConexion();

    private MuebleIDAO() {
    }

    public static MuebleIDAO getMuebleIDAO() {
        if (muebleIDAO == null) {
            muebleIDAO = new MuebleIDAO();
        }
        return muebleIDAO;
    }

    @Override
    public void crear(Mueble m) {
        String consulta = "INSERT INTO Mueble VALUES(?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, m.getMueble());
            ps.setInt(2, m.getPrecio());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Mueble leer(String id) {
        return null;
    }

    @Override
    public void actualizar(Mueble mueble) {

    }

    @Override
    public void borrar(String id) {

    }

    @Override
    public List<Mueble> getList() {
        return null;
    }

    @Override
    public boolean existe(String id) {
        return false;
    }
}
