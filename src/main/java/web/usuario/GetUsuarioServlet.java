package web.usuario;

import dao.CRUD;
import dao.usuario.UsuarioDAO;
import dao.usuario.UsuarioIDAO;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getUsuario")
public class GetUsuarioServlet extends HttpServlet {

    private final CRUD<Usuario> userDAO = UsuarioIDAO.getUsuarioIDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        req.setAttribute("usuario", userDAO.leer(username));
        req.getRequestDispatcher("admon/users/listUsuarios.jsp").forward(req, resp);
    }
}
