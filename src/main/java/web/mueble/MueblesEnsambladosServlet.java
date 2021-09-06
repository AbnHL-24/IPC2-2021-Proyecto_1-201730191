package web.mueble;

import dao.CRUD;
import dao.ensamblarMueble.EnsamblarMuebleIDAO;
import modelo.EnsamblarMueble;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/mueblesEnsamblados")
public class MueblesEnsambladosServlet extends HttpServlet {

    private final CRUD<EnsamblarMueble> ensamblarMuebleCRUD = EnsamblarMuebleIDAO.getEnsamblarMuebleIDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EnsamblarMueble> mueblesEnsamblados = ensamblarMuebleCRUD.getList();
        req.setAttribute("ensambles", mueblesEnsamblados);
        req.getRequestDispatcher("fabrica/muebles/ensambles.jsp").forward(req, resp);
    }
}
