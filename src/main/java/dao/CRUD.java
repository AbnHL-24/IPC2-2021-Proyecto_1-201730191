package dao;

import java.util.List;

public interface CRUD<T> {

    void crear(T t);
    T leer(String id);
    void actualizar(T t);
    void borrar(String id);
    List<T> getList();
    boolean existe(String id);
}
