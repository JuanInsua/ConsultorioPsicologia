package Modelo;

import java.util.Objects;

/**
 * The `Paciente` class represents a patient and extends the `Persona` class.
 */
public class Paciente extends Persona {

    /**
     * Creates a new instance of the `Paciente` class with default values.
     */
    public Paciente() {

    }
    /**
     * Creates a new instance of the `Paciente` class with the specified nombre, apellido, email, dni, and obra social.
     *
     * @param nombreUsuario      the nombre of the patient

     * @param email       the email of the patient
     * @param dni         the dni of the patient
     */
    public Paciente(String nombreUsuario , String email, String dni) {
        super(nombreUsuario, email, dni);
    }
    /**
     * Returns a string representation of the patient, including inherited attributes from `Persona`.
     *
     * @return a string representation of the patient
     */
    @Override
    public String toString() {
        return super.toString();
    }
}