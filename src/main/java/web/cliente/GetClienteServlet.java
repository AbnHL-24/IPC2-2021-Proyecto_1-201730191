package web.cliente;

import dao.cliente.ClienteIDAO;
import modelo.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getCliente")
public class GetClienteServlet extends HttpServlet {

    private final ClienteIDAO clienteDAO = ClienteIDAO.getClienteIDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nit = req.getParameter("nit");

        Cliente cliente = clienteDAO.leer(nit);

        req.setAttribute("cliente", cliente);
        req.getRequestDispatcher("admon/clientes/clientes.jsp").forward(req, resp);
    }
}
