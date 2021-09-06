package dao.pieza;

import dao.CRUD;
import modelo.Pieza;

public interface PiezaDAO extends CRUD<Pieza> {

    boolean existe(String id, String precio);
    Pieza leer(String id, String precio);
}
