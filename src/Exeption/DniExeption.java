package Exeption;

import Archivo.ControladoraArchivo;

import java.util.Date;

public class DniExeption extends RuntimeException{
    private String message;
    private Date fechaError;

    public DniExeption(String message){
        setMessage(message);
        this.fechaError=new Date();
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

    @Override
    public String toString() {
        return "DniExeption{" +
                "message='" + message + '\'' +
                ", fechaError=" + fechaError +
                '}';
    }
}
