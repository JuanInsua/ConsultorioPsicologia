package Exeption;

import Archivo.ControladoraArchivo;
import Interfaz.I_GrabarExeption;

import java.util.Date;
import java.util.Objects;

/**
 * Representa una clase de excepción personalizada para manejar excepciones relacionadas con los días.
 */
public class DiaExeption extends Personalizada implements I_GrabarExeption {

    /**
     * Construye una nueva instancia de la clase DiaExeption con el mensaje de error especificado.
     *
     * @param mensaje El mensaje de error asociado con la excepción.
     */
    public DiaExeption(String mensaje) {
        super(mensaje);
        grabarExeption();
    }

    @Override
    public void grabarExeption() {
        ControladoraArchivo.grabar(super.getFechaError(),super.getMessage());
    }


    /**
     * Devuelve una representación en cadena del objeto DiaExeption.
     *
     * @return Una representación en cadena del objeto, que incluye el mensaje de error.
     */
    @Override
    public String toString() {
        return "DiaExeption{" +
                "mensaje='" + super.getMessage() + '\'' +
                ", fechaError=" + super.getFechaError() +
                '}';
    }
}