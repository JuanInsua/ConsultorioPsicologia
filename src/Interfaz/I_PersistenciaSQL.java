package Interfaz;

import java.util.ArrayList;
import java.util.TreeMap;

public interface I_PersistenciaSQL <T> {
    void registrar(T elemento);
    boolean modificar(T elemento);
}
