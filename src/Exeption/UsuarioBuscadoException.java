package Exeption;

import Archivo.ControladoraArchivo;
import Interfaz.I_GrabarExeption;

import java.util.Date;

/**
 * Esta clase representa una excepción personalizada lanzada cuando un usuario no es encontrado.
 */
public class UsuarioBuscadoException extends Personalizada implements I_GrabarExeption {

    /**
     * Crea una nueva instancia de UsuarioBuscadoException con el mensaje especificado.
     *
     * @param message el mensaje de la excepción
     */
    public UsuarioBuscadoException(String message) {
        super(message);
        grabarExeption();
    }

    @Override
    public void grabarExeption() {
        ControladoraArchivo.grabar(super.getFechaError(),super.getMessage());
    }

    /**
     * Devuelve una representación en cadena de la excepción.
     *
     * @return una cadena que contiene el nombre de la clase y el mensaje de la excepción
     */
    @Override
    public String toString() {
        return "UsuarioBuscadoException{" +
                "message='" + super.getMessage() + '\'' +
                ", fechaError=" + super.getFechaError() +
                '}';
    }
}

