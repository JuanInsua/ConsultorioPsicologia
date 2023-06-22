package Exeption;

import Archivo.ControladoraArchivo;
import Interfaz.I_GrabarExeption;

import java.util.Date;

public class DniExeption extends RuntimeException implements I_GrabarExeption {
    private String message;
    private Date fechaError;

    public DniExeption(String message){
        setMessage(message);
        setFechaError(new Date());
        grabarExeption();
    }

    @Override
    public void grabarExeption() {
        ControladoraArchivo.grabar(this.getFechaError(),this.getMessage());
    }

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

    @Override
    public String toString() {
        return "DniExeption{" +
                "message='" + message + '\'' +
                ", fechaError=" + fechaError +
                '}';
    }
}
