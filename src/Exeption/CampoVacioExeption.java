package Exeption;

import Archivo.ControladoraArchivo;
import Interfaz.I_GrabarExeption;

import java.util.Date;

/**
 * La clase CampoVacioExeption representa una excepción personalizada que se lanza cuando se encuentra un campo vacío.
 * Extiende la clase RuntimeException, por lo que es una excepción no verificada.
 */
public class CampoVacioExeption extends Personalizada  {

    /**
     * Crea una nueva instancia de CampoVacioExeption con un mensaje predeterminado.
     * El mensaje predeterminado es "Error campo vacio, completar todos los campos".
     */
    public CampoVacioExeption() {
        super("Error campo vacio, completar todos los campos");
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
                "fechaError="  + super.getFechaError()+
                ", message='"  + super.getMessage()+'\'' +
                '}';
    }
}
