package web.mueble;

import dao.CRUD;
import dao.mueble.MuebleIDAO;
import modelo.Mueble;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/mueblesEnsamblar")
public class ListarMueblesEnsambleServlet extends HttpServlet {

    private final CRUD<Mueble> muebleCRUD = MuebleIDAO.getMuebleIDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Mueble> muebles = muebleCRUD.getList();
        req.setAttribute("muebles", muebles);
        req.getRequestDispatcher("fabrica/muebles/listMueblesEnsamble.jsp").forward(req, resp);
    }
}
