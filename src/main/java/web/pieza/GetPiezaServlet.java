package web.pieza;

import dao.pieza.PiezaDAO;
import dao.pieza.PiezaIDAO;
import modelo.Pieza;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getPieza")
public class GetPiezaServlet extends HttpServlet {

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

        Pieza pieza = piezaDAO.leer(id);
        req.setAttribute("pieza", pieza);
        req.getRequestDispatcher("fabrica/piezas/listPiezas.jsp").forward(req, resp);
    }
}
