package Exeption;

import Archivo.ControladoraArchivo;

import java.util.Date;

public class CaracteresMotivoInvalidosException extends RuntimeException{
    private String message;
    private Date fechaError;

    public CaracteresMotivoInvalidosException(String message){
        setMessage(message);
        this.fechaError=new Date();
        ControladoraArchivo.grabar(this.getFechaError(),this.getMessage());
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Date getFechaError() {
        return fechaError;
    }

    private void setFechaError(Date fechaError) {
        this.fechaError = fechaError;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CaracteresMotivoInvalidosException{" +
                "message='" + message + '\'' +
                ", fechaError=" + fechaError +
                '}';
    }
}
