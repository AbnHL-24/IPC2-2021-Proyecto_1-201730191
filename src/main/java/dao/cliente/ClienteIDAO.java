package dao.cliente;

import dao.Conexion;
import modelo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteIDAO implements ClienteDAO {
    private static ClienteIDAO clienteIDAO = null;
    private Connection connection = Conexion.getConexion();

    private ClienteIDAO() {
    }

    public static ClienteIDAO getClienteIDAO() {
        if (clienteIDAO == null) {
            clienteIDAO = new ClienteIDAO();
        }
        return clienteIDAO;
    }

    @Override
    public void crear(Cliente c) {
        String consulta = "INSERT INTO Cliente VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(consulta)) {
            ps.setString(1, c.getNitCliente());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getMunicipio());
            ps.setString(5, c.getDepartamento());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("");
        }
    }

    @Override
    public Cliente leer(String nit_cliente) {
        String sql = "SELECT * FROM Cliente WHERE nit_cliente = ?";

        Cliente cliente = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nit_cliente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = Cliente.builder()
                            .nitCliente(rs.getString("nit_cliente"))
                            .nombre(rs.getString("nombre"))
                            .direccion(rs.getString("direccion"))
                            .municipio((rs.getString("municipio")) == null? "": rs.getString("municipio"))
                            .departamento(rs.getString("departamento") == null ? "": rs.getString("departamento"))
                            .build();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }

    @Override
    public void actualizar(Cliente c) {
        
    }

    @Override
    public void borrar(String id) {

    }

    @Override
    public List<Cliente> getList() {
        String sql = "SELECT * FROM Cliente";
        List<Cliente> clientes = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            clientes = new ArrayList();

            while (rs.next()) {
                clientes.add(Cliente.builder()
                        .nitCliente(rs.getString("nit_cliente"))
                        .nombre(rs.getString("nombre"))
                        .direccion(rs.getString("direccion"))
                        .municipio((rs.getString("municipio")) == null? "": rs.getString("municipio"))
                        .departamento(rs.getString("departamento") == null ? "": rs.getString("departamento"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public boolean existe(String id) {
        String sql = "SELECT nit_cliente FROM Cliente WHERE nit_cliente = ?";
        boolean flag = false;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
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
