package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection conexion = null;
    private final String url = "jdbc:mariadb://localhost:3306/MiMuebleriaBD";
    private final String usuario = "root";
    private final String password = "1235";

    private Conexion() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("No se pudo conecatar");
        }
    }

    public static Connection getConexion() {
        if (conexion == null) {
            new Conexion();
        }
        return conexion;
    }
}
