package Exeption;

import Archivo.ControladoraArchivo;

import java.util.Date;
import java.util.Objects;

/**
 * Esta clase representa una excepción personalizada para el manejo de errores relacionados con turnos.
 */
public class TurnoExeption extends Exception {

    private String mensaje;
    private Date fechaError;


    /**
     * Construye una nueva instancia de la clase TurnoExeption con el mensaje de error especificado.
     *
     * @param mensaje el mensaje de error para esta excepción
     */
    public TurnoExeption(String mensaje) {
        this.mensaje = mensaje;
        this.fechaError=new Date();
        ControladoraArchivo.grabar(this.getFechaError(),this.getMessage());
    }

    /**
     * Obtiene el mensaje de error asociado con esta excepción.
     *
     * @return el mensaje de error
     */
    public String getMensaje() {
        return mensaje;
    }

    public Date getFechaError() {
        return fechaError;
    }

    /**
     * Devuelve una representación en forma de cadena de esta excepción.
     *
     * @return una cadena que representa la excepción
     */
    @Override
    public String toString() {
        return "TurnoExeption{" +
                "mensaje='" + mensaje + '\'' +
                ", fechaError=" + fechaError +
                '}';
    }
}

