package dao.mueble;

import dao.Conexion;
import modelo.Mueble;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            ps.setFloat(2, m.getPrecio());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Mueble leer(String id) {
        String sql = "SELECT * FROM Mueble WHERE mueble = ?";

        Mueble mueble = null;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mueble = Mueble.builder()
                            .mueble(rs.getString("mueble"))
                            .precio(rs.getFloat("precio"))
                            .build();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return mueble;
    }

    @Override
    public void actualizar(Mueble mueble) {
        String sql = "UPDATE Mueble SET precio = ? WHERE mueble = ?";
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setFloat(1, mueble.getPrecio());
            ps.setString(2, mueble.getMueble());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void borrar(String id) {

    }

    @Override
    public List<Mueble> getList() {
        String sql = "SELECT * FROM Mueble";
        List<Mueble> muebles = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
                muebles = new ArrayList();

                while (rs.next()) {
                    muebles.add(Mueble.builder()
                            .mueble(rs.getString("mueble"))
                            .precio(rs.getFloat("precio"))
                            .build());
                }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return muebles;
    }

    @Override
    public boolean existe(String id) {
        String sql = "SELECT mueble FROM Mueble where mueble = ?";
        boolean flag = false;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            try ( ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    flag = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return flag;
    }
}
