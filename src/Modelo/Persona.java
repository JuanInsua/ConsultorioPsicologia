package Modelo;

import java.util.Date;
import java.util.Objects;

/**
 * The `Persona` class represents a person with basic personal information.
 */
public class Persona {

    /**
     * The name of the person.
     */
    private String nombre;

    /**
     * The last name of the person.
     */
    private String apellido;

    /**
     * The email address of the person.
     */
    private String email;

    /**
     * The identification number of the person.
     */
    private String dni;

    /**
     * Constructs a new instance of the `Persona` class with default values.
     */
    public Persona() {
    }

    /**
     * Constructs a new instance of the `Persona` class with the specified personal information.
     *
     * @param nombre   the name of the person
     * @param email    the email address of the person
     * @param dni      the identification number of the person
     */
    public Persona(String nombre, String email, String dni) {
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
    }

    /**
     * Returns the name of the person.
     *
     * @return the name of the person
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of the person.
     *
     * @param nombre the name of the person
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Returns the last name of the person.
     *
     * @return the last name of the person
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets the last name of the person.
     *
     * @param apellido the last name of the person
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Returns the email address of the person.
     *
     * @return the email address of the person
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the person.
     *
     * @param email the email address of the person
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the identification number of the person.
     *
     * @return the identification number of the person
     */
    public String getDni() {
        return dni;
    }

    /**
     * Sets the identification number of the person.
     *
     * @param dni the identification number of the person
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Checks if this `Persona` object is equal to another object.
     *
     * @param o the object to compare this `Persona` to
     * @return `true` if the objects are equal, `false` otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(nombre, persona.nombre) && Objects.equals(apellido, persona.apellido) &&
                Objects.equals(email, persona.email) && Objects.equals(dni, persona.dni);
    }

    /**
     * Returns the hash code value for this `Persona` object.
     *
     * @return the hash code value for this `Persona` object
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, email, dni);
    }

    /**
     * Returns a string representation of the `Persona` object.
     *
     * @return a string representation of the `Persona` object
     */
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}
