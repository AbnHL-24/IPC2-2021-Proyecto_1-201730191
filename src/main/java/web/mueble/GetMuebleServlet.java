package web.mueble;

import dao.mueble.MuebleDAO;
import dao.mueble.MuebleIDAO;
import modelo.Mueble;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getMueble")
public class GetMuebleServlet extends HttpServlet {

    private MuebleDAO muebleDAO = MuebleIDAO.getMuebleIDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("id");

        Mueble mueble = muebleDAO.leer(nombre);
        req.setAttribute("mueble", mueble);
        req.getRequestDispatcher("admon/muebles/listMuebles.jsp").forward(req, resp);
    }
}
