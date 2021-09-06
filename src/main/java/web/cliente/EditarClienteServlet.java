package web.cliente;

import dao.cliente.ClienteIDAO;
import modelo.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editarCliente")
public class EditarClienteServlet extends HttpServlet {

    private final ClienteIDAO clienteDAO = ClienteIDAO.getClienteIDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nit = request.getParameter("nit");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String municipio = request.getParameter("municipio");
        String departamento = request.getParameter("departamento");

        Cliente cliente = Cliente.builder()
                .nombre(nombre)
                .nitCliente(nit)
                .direccion(direccion)
                .build();
        if (municipio != null) cliente.setMunicipio(municipio);
        if (departamento != null) cliente.setDepartamento(departamento);

        clienteDAO.actualizar(cliente);

        request.setAttribute("success", "Se edito el cliente correctamente");
        request.getRequestDispatcher("listarClientes").forward(request, response);

    }
}
