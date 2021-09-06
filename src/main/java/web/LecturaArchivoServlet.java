package web;

import utils.archivos.CargarDatosDesdeArchivo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet("/lecturaArchivo")
@MultipartConfig
public class LecturaArchivoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    //Procesa la solicitud HTML ya sea desde un metodo POST o GET
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("archivoEntrada");

        String texto = obtenerTexto(filePart.getInputStream());
        texto = texto.replaceAll("\"", "");

        CargarDatosDesdeArchivo cargarDatos = new CargarDatosDesdeArchivo();
        cargarDatos.cargarDatos(texto);

        request.setAttribute("nice", "La lectura del archivo finalizo sin errores");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    public String obtenerTexto(InputStream inputStream) throws UnsupportedEncodingException {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        try {
            for (int result = bis.read(); result != -1; result = bis.read()) {
                buf.write((byte) result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf.toString("UTF-8");
    }

}
