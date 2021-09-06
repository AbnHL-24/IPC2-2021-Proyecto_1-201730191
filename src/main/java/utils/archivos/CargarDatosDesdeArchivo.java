package utils.archivos;

import dao.cliente.ClienteIDAO;
import dao.ensamblarMueble.EnsamblarMuebleIDAO;
import dao.mueble.MuebleIDAO;
import dao.pieza.PiezaIDAO;
import dao.piezaEnsamble.PiezaEnsambleIDAO;
import dao.usuario.UsuarioIDAO;
import modelo.*;

import java.rmi.server.UID;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static utils.validaciones.ValidacionCliente.validarCliente;
import static utils.validaciones.ValidacionEmsamblePiezas.validarEnsamblePiezas;
import static utils.validaciones.ValidacionMueble.validarMueble;
import static utils.validaciones.ValidacionPieza.validarPieza;
import static utils.validaciones.ValidacionUsuario.validarUsuario;

/**
 * Clase que se encarga de leer el archivo de datos del sistema anterior.
 * @author abnerhl
 */
public class CargarDatosDesdeArchivo {

    /**
     * Clase para cargar datos desde un archivo
     * @param s cadena con todos los datos.
     */
    public void cargarDatos(String s) {
        List<String> datos = new ArrayList<String>(Arrays.asList(s.split("\n")));

        for (int i = 0; i < datos.size(); i++) {
            int indice = i + 1;
            //valida que alguna linea carezca del parentesis de cierre.
            if (!datos.get(i).endsWith(")") | !datos.get(i).contains("(")) {
                continue;
            }
            //Retorna un arreglo de argumentos
            String[] parametros = obtenerParametros(datos.get(i));

            if (datos.get(i).toUpperCase().startsWith("USUARIO")) {
                if (validarUsuario(parametros)) {
                    Usuario usuario = Usuario.builder()
                            .usuario(String.valueOf(parametros[0]))
                            .password(String.valueOf(parametros[1]))
                            .tipoUsuario(Integer.parseInt(parametros[2]))
                            .estadoUsuario(1)
                            .build();
                    UsuarioIDAO usuarioIDAO = UsuarioIDAO.getUsuarioIDAO();
                    usuarioIDAO.crear(usuario);
                }

            } else if (datos.get(i).toUpperCase().startsWith("PIEZA")) {
                PiezaIDAO piezaIDAO = PiezaIDAO.getPiezaIDAO();
                Pieza pieza = null;
                if (validarPieza(parametros)) {
                    if (!piezaIDAO.existe(parametros[0], parametros[1])) {
                        pieza = Pieza.builder()
                            .tipo(String.valueOf(parametros[0]))
                            .costo(Float.parseFloat(parametros[1]))
                            .existencia(1)
                                .build();
                        piezaIDAO.crear(pieza);
                    } else {
                        Pieza piezaAnterior = piezaIDAO.leer(parametros[0], parametros[1]);
                        pieza = Pieza.builder()
                                .tipo(String.valueOf(parametros[0]))
                                .costo(Float.parseFloat(parametros[1]))
                                .existencia(piezaAnterior.getExistencia() + 1)
                                .build();
                        piezaIDAO.actualizar(pieza);
                    }
                }

            } else if (datos.get(i).toUpperCase().startsWith("MUEBLE")) {
                MuebleIDAO muebleIDAO = MuebleIDAO.getMuebleIDAO();
                System.out.println(parametros[0]);
                try {
                    System.out.println(datos.get(i));
                    if (validarMueble(parametros)) {
                        Mueble mueble = Mueble.builder()
                                .mueble(parametros[0])
                                .precio(Float.parseFloat(parametros[1]))
                                .build();
                        muebleIDAO.crear(mueble);
                    }
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }

            } else if (datos.get(i).toUpperCase().startsWith("ENSAMBLE_PIEZAS")) {
                MuebleIDAO muebleIDAO = MuebleIDAO.getMuebleIDAO();
                PiezaIDAO piezaIDAO = PiezaIDAO.getPiezaIDAO();
                PiezaEnsambleIDAO piezaEnsambleIDAO = PiezaEnsambleIDAO.getPiezaEnsambleIDAO();

                if (validarEnsamblePiezas(parametros)) {
                    if (muebleIDAO.existe(parametros[0])) {
                        if (piezaIDAO.existe(parametros[1])) {
                            if (!piezaEnsambleIDAO.existe(parametros[0], parametros[1])) {
                                PiezaEnsamble piezaEnsamble = PiezaEnsamble.builder()
                                        .mubele(parametros[0])
                                        .pieza(parametros[1])
                                        .cantidad(parametros[2])
                                        .build();
                                piezaEnsambleIDAO.crear(piezaEnsamble);
                            }
                        }
                    }
                }

            } else if (datos.get(i).toUpperCase().startsWith("ENSAMBLAR_MUEBLE")) {
                MuebleIDAO muebleIDAO = MuebleIDAO.getMuebleIDAO();
                UsuarioIDAO usuarioIDAO = UsuarioIDAO.getUsuarioIDAO();
                EnsamblarMuebleIDAO ensamblarMuebleIDAO = EnsamblarMuebleIDAO.getEnsamblarMuebleIDAO();
                try {
                    if (usuarioIDAO.existe(parametros[1])) {
                        EnsamblarMueble ensamblarMueble = EnsamblarMueble.builder()
                                .mueble(parametros[0])
                                .usuario(parametros[1])
                                .fecha(LocalDate.parse(parametros[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                                .estadoVenta(1)
                                .build();
                        ensamblarMuebleIDAO.crear(ensamblarMueble);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (datos.get(i).toUpperCase().startsWith("CLIENTE")) {
                ClienteIDAO clienteIDAO = ClienteIDAO.getClienteIDAO();
                //si no existe el nit cree al usuario.
                if (!clienteIDAO.existe(parametros[1])) {
                    if (validarCliente(parametros)) {
                        Cliente cliente = Cliente.builder()
                                .nombre(String.valueOf(parametros[0]))
                                .nitCliente(String.valueOf(parametros[1]))
                                .direccion(String.valueOf(parametros[2]))
                                .build();
                        if (parametros.length == 5) {
                            cliente.setMunicipio(String.valueOf(parametros[3]));
                            cliente.setDepartamento(String.valueOf(parametros[4]));
                        }
                        clienteIDAO.crear(cliente);
                    }
                }

            }
        }

    }

    /**
     * Este metodo obtiene la cadena 'dato' para separarla en los parametros del objeto a crear.
     * La cadena 'dato' es obtenida de la lectura de archivos.
     * Rompe en un arreglo lo que esta dentro de parentesis y separado por comas.
     * Los parametros seran usados para crear el objeto en cuestion correspondiente.
     * @param dato Cadena que contiene los parametros en una sola linea de texto.
     * @return Retorna los parametros en forma de arreglo de tipo String, lista para usar en la creacion de un objeto.
     */
    private String[] obtenerParametros(String dato) {
        // A indiceAbrirParentesis le sumamos uno porque el metodo substring incluye al primer indice.
        int indiceAbrirParentesis = dato.indexOf("(") +1;
        // A indiceAbrirParentesis no le sumamos uno porque el metodo substring excluye al segundo indice.
        int indiceCerrarParentesis = dato.indexOf(")");
        String datos = dato.substring(indiceAbrirParentesis, indiceCerrarParentesis);
        String[] parametros = datos.split(",");
        return parametros;
    }
}
