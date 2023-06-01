package Modelo;

import java.util.Objects;


/**
 * The `Paciente` class represents a patient and extends the `Persona` class.
 */
public class Paciente extends Persona {

    /**
     * The obra social of the patient.
     */
    private String obraSocial;

    /**
     * Creates a new instance of the `Paciente` class with default values.
     */
    public Paciente() {

    }

    /**
     * Creates a new instance of the `Paciente` class with the specified obra social.
     *
     * @param obraSocial the obra social of the patient
     */
    public Paciente(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    /**
     * Creates a new instance of the `Paciente` class with the specified nombre, apellido, email, dni, and obra social.
     *
     * @param nombre      the nombre of the patient
     * @param apellido    the apellido of the patient
     * @param email       the email of the patient
     * @param dni         the dni of the patient
     * @param obraSocial  the obra social of the patient
     */
    public Paciente(String nombre, String apellido, String email, String dni, String obraSocial) {
        super(nombre, apellido, email, dni);
        this.obraSocial = obraSocial;
    }

    /**
     * Retrieves the obra social of the patient.
     *
     * @return the obra social of the patient
     */
    public String getObraSocial() {
        return obraSocial;
    }

    /**
     * Sets the obra social of the patient.
     *
     * @param obraSocial the obra social of the patient
     */
    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two patients are considered equal if they have the same obra social and other attributes inherited from `Persona`.
     *
     * @param o the reference object with which to compare
     * @return true if this patient is the same as the o argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(obraSocial, paciente.obraSocial);
    }

    /**
     * Returns a hash code value for the patient.
     *
     * @return a hash code value for this patient
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), obraSocial);
    }

    /**
     * Returns a string representation of the patient, including inherited attributes from `Persona`.
     *
     * @return a string representation of the patient
     */
    @Override
    public String toString() {
        return super.toString() + "Paciente{" +
                "obraSocial='" + obraSocial + '\'' +
                '}';
    }
}