package Exeption;

import Archivo.ControladoraArchivo;

import java.util.Date;

/**
 * La clase CampoVacioExeption representa una excepción personalizada que se lanza cuando se encuentra un campo vacío.
 * Extiende la clase RuntimeException, por lo que es una excepción no verificada.
 */
public class CampoVacioExeption extends RuntimeException {
    private Date fechaError;
    private String message;

    /**
     * Crea una nueva instancia de CampoVacioExeption con un mensaje predeterminado.
     * El mensaje predeterminado es "Error campo vacio, completar todos los campos".
     */
    public CampoVacioExeption() {
        setMessage("Error campo vacio, completar todos los campos");
        this.fechaError=new Date();
        ControladoraArchivo.grabar(this.getFechaError(),this.getMessage());
    }

    /**
     * Obtiene el mensaje de la excepción.
     *
     * @return El mensaje de la excepción.
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

    private void setFechaError(Date fechaError) {
        this.fechaError = fechaError;
    }

    /**
     * Devuelve una representación en cadena de la excepción.
     * Esta implementación llama al método toString de la superclase.
     *
     * @return Una representación en cadena de la excepción.
     */
    @Override
    public String toString() {
        return "CampoVacioExeption{" +
                "fechaError=" + fechaError +
                ", message='" + message + '\'' +
                '}';
    }
}
