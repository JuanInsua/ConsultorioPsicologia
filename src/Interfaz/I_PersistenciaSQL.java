package Interfaz;

import java.util.ArrayList;

public interface I_PersistenciaSQL <T> {
    void registrar(T elemento);
    ArrayList<T> listar();
    boolean modificar(T elemento);
}
