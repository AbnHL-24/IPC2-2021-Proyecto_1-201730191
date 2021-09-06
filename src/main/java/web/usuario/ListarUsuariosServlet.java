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
import java.util.List;

@WebServlet("/listarUsuarios")
public class ListarUsuariosServlet extends HttpServlet {

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
        List<Usuario> usuarioList = userDAO.getList();
        req.setAttribute("usuarios", usuarioList);
        req.getRequestDispatcher("admon/users/listUsuarios.jsp").forward(req, resp);
    }
}
