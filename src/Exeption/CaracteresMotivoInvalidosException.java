package Exeption;

import Archivo.ControladoraArchivo;
import Interfaz.I_GrabarExeption;

import java.util.Date;

public class CaracteresMotivoInvalidosException extends Personalizada implements I_GrabarExeption {

    public CaracteresMotivoInvalidosException(String message){
        super(message);
        grabarExeption();
    }
    @Override
    public void grabarExeption() {
        ControladoraArchivo.grabar(super.getFechaError(),super.getMessage());
    }


    @Override
    public String toString() {
        return "CaracteresMotivoInvalidosException{" +
                "message='" + super.getMessage() + '\'' +
                ", fechaError=" + super.getFechaError() +
                '}';
    }
}
