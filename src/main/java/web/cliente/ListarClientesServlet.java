package web.cliente;

import dao.cliente.ClienteIDAO;
import modelo.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listarClientes")
public class ListarClientesServlet extends HttpServlet {

    private final ClienteIDAO clienteDAO = ClienteIDAO.getClienteIDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cliente> clientes = clienteDAO.getList();

        req.setAttribute("clientes", clientes);
        req.getRequestDispatcher("admon/clientes/clientes.jsp").forward(req, resp);
    }
}
