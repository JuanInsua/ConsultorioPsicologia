package Exeption;

import Archivo.ControladoraArchivo;
import Interfaz.I_GrabarExeption;

import java.util.Date;

public class DniExeption extends Personalizada implements I_GrabarExeption {

    public DniExeption(String message){
        super(message);
        grabarExeption();
    }

    @Override
    public void grabarExeption() {
        ControladoraArchivo.grabar(super.getFechaError(),super.getMessage());
    }

    @Override
    public String toString() {
        return "DniExeption{" +
                "message='"  + super.getMessage()+ '\'' +
                ", fechaError="  +super.getFechaError()+
                '}';
    }
}
