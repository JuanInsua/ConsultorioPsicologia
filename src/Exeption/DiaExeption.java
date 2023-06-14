package Exeption;

import java.util.Objects;

/**
 * Representa una clase de excepción personalizada para manejar excepciones relacionadas con los días.
 */
public class DiaExeption extends Exception {

    private String mensaje;

    /**
     * Construye una nueva instancia de la clase DiaExeption con el mensaje de error especificado.
     *
     * @param mensaje El mensaje de error asociado con la excepción.
     */
    public DiaExeption(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Recupera el mensaje de error asociado con la excepción.
     *
     * @return El mensaje de error.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Devuelve una representación en cadena del objeto DiaExeption.
     *
     * @return Una representación en cadena del objeto, que incluye el mensaje de error.
     */
    @Override
    public String toString() {
        return "DiaExeption{" +
                "mensaje='" + mensaje + '\'' +
                '}';
    }

    /**
     * Comprueba si este objeto DiaExeption es igual a otro objeto.
     *
     * @param o El objeto a comparar.
     * @return True si los objetos son iguales; false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaExeption that = (DiaExeption) o;
        return Objects.equals(mensaje, that.mensaje);
    }

    /**
     * Devuelve el valor de código hash para el objeto DiaExeption.
     *
     * @return El valor de código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(mensaje);
    }
}