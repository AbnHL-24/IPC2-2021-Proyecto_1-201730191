package web.pieza;

import dao.CRUD;
import dao.pieza.PiezaIDAO;
import modelo.Pieza;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/agregarPieza")
public class AgregarPiezaServlet extends HttpServlet {

    private final CRUD<Pieza> piezaDAO = PiezaIDAO.getPiezaIDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        Float costo = Float.parseFloat(req.getParameter("costo"));
        Integer stock = Integer.parseInt(req.getParameter("stock"));

        Pieza pieza = Pieza.builder().existencia(stock).costo(costo).tipo(nombre).build();
        piezaDAO.crear(pieza);

        req.setAttribute("success", "Pieza ingresada correctamente");
        req.getRequestDispatcher("listarPiezas").forward(req, resp);
    }
}
