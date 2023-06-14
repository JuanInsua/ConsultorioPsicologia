package Exeption;

import java.util.Objects;

/**
 * Esta clase representa una excepción personalizada para el manejo de errores relacionados con turnos.
 */
public class TurnoExeption extends Exception {

    private String mensaje;

    /**
     * Construye una nueva instancia de la clase TurnoExeption con el mensaje de error especificado.
     *
     * @param mensaje el mensaje de error para esta excepción
     */
    public TurnoExeption(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el mensaje de error asociado con esta excepción.
     *
     * @return el mensaje de error
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Compara esta excepción con otro objeto para determinar si son iguales.
     *
     * @param o el objeto a comparar
     * @return true si el objeto dado es igual a esta excepción, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurnoExeption that = (TurnoExeption) o;
        return Objects.equals(mensaje, that.mensaje);
    }

    /**
     * Devuelve el valor hash de esta excepción.
     *
     * @return el valor hash de la excepción
     */
    @Override
    public int hashCode() {
        return Objects.hash(mensaje);
    }

    /**
     * Devuelve una representación en forma de cadena de esta excepción.
     *
     * @return una cadena que representa la excepción
     */
    @Override
    public String toString() {
        return "TurnosExeption{" +
                "mensaje='" + mensaje + '\'' +
                '}';
    }
}

