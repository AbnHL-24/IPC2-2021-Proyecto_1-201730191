package web.pieza;

import dao.CRUD;
import dao.pieza.PiezaDAO;
import dao.pieza.PiezaIDAO;
import modelo.Pieza;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/actualizarPieza")
public class ActualizarPiezaServlet extends HttpServlet {

    private final PiezaDAO piezaDAO = PiezaIDAO.getPiezaIDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Integer stock = Integer.parseInt(req.getParameter("stock"));

        Pieza pieza = piezaDAO.leer(id);
        pieza.setExistencia(stock);

        piezaDAO.actualizar(pieza);

        req.setAttribute("success", "Pieza actualizada correctamente");
        req.getRequestDispatcher("listarPiezas").forward(req, resp);
    }
}
