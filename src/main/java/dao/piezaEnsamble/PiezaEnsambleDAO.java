package dao.piezaEnsamble;

import dao.CRUD;
import modelo.PiezaEnsamble;

public interface PiezaEnsambleDAO extends CRUD<PiezaEnsamble> {
    boolean existe(String mueble, String pieza);
}
