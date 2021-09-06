package web;

import dao.usuario.UsuarioIDAO;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    private final UsuarioIDAO userDAO = UsuarioIDAO.getUsuarioIDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Usuario> admins = userDAO.getAdmins();

        if (admins.isEmpty()) {
            resp.sendRedirect("admon/archivoEntrada.jsp");
        } else {

            HttpSession sesion = req.getSession();
            Usuario user = (Usuario) sesion.getAttribute("user");
            if (sesion.getAttribute("user") != null) {

                switch (user.getTipoUsuario()) {
                    case 1 -> resp.sendRedirect("admon/inicioAdmon.jsp");
                    case 3 -> resp.sendRedirect("fabrica/inicioFabrica.jsp");
                    case 2 -> resp.sendRedirect("ventas/inicioVentas.jsp");
                }
            } else {
                resp.sendRedirect("login.jsp");
            }
        }
    }
}
