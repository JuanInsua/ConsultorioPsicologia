package Exeption;

import Archivo.ControladoraArchivo;
import Interfaz.I_GrabarExeption;

import java.util.Date;

/**
 * Esta clase representa una excepción personalizada lanzada cuando un usuario no es encontrado.
 */
public class UsuarioBuscadoException extends RuntimeException implements I_GrabarExeption {

    private String message;
    private Date fechaError;

    /**
     * Crea una nueva instancia de UsuarioBuscadoException con el mensaje especificado.
     *
     * @param message el mensaje de la excepción
     */
    public UsuarioBuscadoException(String message) {
        setMessage(message);
        setFechaError(new Date());
        grabarExeption();
    }

    @Override
    public void grabarExeption() {
        ControladoraArchivo.grabar(this.getFechaError(),this.getMessage());
    }

    /**
     * Obtiene el mensaje asociado con esta excepción.
     *
     * @return el mensaje de la excepción
     */
    @Override
    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public Date getFechaError() {
        return fechaError;
    }

    public void setFechaError(Date fechaError) {
        this.fechaError = fechaError;
    }

    /**
     * Devuelve una representación en cadena de la excepción.
     *
     * @return una cadena que contiene el nombre de la clase y el mensaje de la excepción
     */
    @Override
    public String toString() {
        return "UsuarioBuscadoException{" +
                "message='" + message + '\'' +
                ", fechaError=" + fechaError +
                '}';
    }
}

