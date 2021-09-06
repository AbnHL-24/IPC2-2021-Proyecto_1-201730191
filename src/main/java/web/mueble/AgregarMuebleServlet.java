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
import java.util.List;

@WebServlet("/agregarMueble")
public class AgregarMuebleServlet extends HttpServlet {

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
        String nombre = req.getParameter("nombre");
        String precio = req.getParameter("precio");

        muebleDAO.crear(Mueble.builder().mueble(nombre).precio(Float.parseFloat(precio)).build());

        req.setAttribute("success", "Mueble ingresado con exito");
        req.getRequestDispatcher("listarMuebles").forward(req, resp);
    }
}
