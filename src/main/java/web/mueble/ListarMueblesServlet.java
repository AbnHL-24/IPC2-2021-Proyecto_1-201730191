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

@WebServlet("/listarMuebles")
public class ListarMueblesServlet extends HttpServlet {

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
        List<Mueble> muebleList = muebleDAO.getList();
        req.setAttribute("muebles", muebleList);
        req.getRequestDispatcher("admon/muebles/listMuebles.jsp").forward(req, resp);
    }
}
