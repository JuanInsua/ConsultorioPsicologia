package Exeption;

import Archivo.ControladoraArchivo;

import java.util.Date;
import java.util.Objects;

/**
 * Representa una clase de excepción personalizada para manejar excepciones relacionadas con los días.
 */
public class DiaExeption extends Exception {

    private String mensaje;
    private Date fechaError;

    /**
     * Construye una nueva instancia de la clase DiaExeption con el mensaje de error especificado.
     *
     * @param mensaje El mensaje de error asociado con la excepción.
     */
    public DiaExeption(String mensaje) {
        this.mensaje = mensaje;
        fechaError=new Date();
        ControladoraArchivo.grabar(this.getFechaError(),this.getMessage());
    }

    /**
     * Recupera el mensaje de error asociado con la excepción.
     *
     * @return El mensaje de error.
     */
    public String getMensaje() {
        return mensaje;
    }

    public Date getFechaError() {
        return fechaError;
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
                ", fechaError=" + fechaError +
                '}';
    }
}