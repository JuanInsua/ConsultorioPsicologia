package Exeption;

import Archivo.ControladoraArchivo;
import Interfaz.I_GrabarExeption;

import java.util.Date;
import java.util.Objects;

/**
 * Esta clase representa una excepción personalizada para el manejo de errores relacionados con turnos.
 */
public class TurnoExeption extends Personalizada implements I_GrabarExeption {


    /**
     * Construye una nueva instancia de la clase TurnoExeption con el mensaje de error especificado.
     *
     * @param mensaje el mensaje de error para esta excepción
     */
    public TurnoExeption(String mensaje) {
        super(mensaje);
        grabarExeption();
    }

    @Override
    public void grabarExeption() {
        ControladoraArchivo.grabar(super.getFechaError(),super.getMessage());
    }


    /**
     * Devuelve una representación en forma de cadena de esta excepción.
     *
     * @return una cadena que representa la excepción
     */
    @Override
    public String toString() {
        return "TurnoExeption{" +
                "mensaje='" + super.getMessage() + '\'' +
                ", fechaError=" + super.getFechaError() +
                '}';
    }
}

