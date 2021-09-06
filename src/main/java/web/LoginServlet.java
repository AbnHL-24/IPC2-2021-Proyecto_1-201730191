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
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        validarCredenciales(username, password, req, resp);
    }

    private void validarCredenciales(String username, String password, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (userDAO.existe(username)) {
            Usuario user = userDAO.leer(username);

            if (!Objects.isNull(user) && user.getPassword().equals(password)) {

                if (user.getEstadoUsuario() == 1) {
                    HttpSession sesion = req.getSession();
                    sesion.setMaxInactiveInterval(3600);
                    sesion.setAttribute("user", user);

                    switch (user.getTipoUsuario()) {
                        case 1: { resp.sendRedirect("admon/inicioAdmon.jsp");}
                        case 2: { resp.sendRedirect("ventas/inicioVentas.jsp");}
                        case 3: {resp.sendRedirect("fabrica/inicioFabrica.jsp");}
                    }
                } else {
                    req.setAttribute("errorLogin", "El usuario esta deshabilitado");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("errorLogin", "Credenciales incorrectas");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorLogin", "Credenciales incorrectas");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }
}
