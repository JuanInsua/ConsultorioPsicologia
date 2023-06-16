package Modelo;

import java.util.Date;
import java.util.Objects;
/**
 * La clase abstracta Persona representa a una persona genérica.
 */
public abstract class Persona {
    private String nombre;
    private String email;
    private String dni;

    /**
     * Constructor por defecto de la clase Persona.
     */
    public Persona() {
    }

    /**
     * Constructor de la clase Persona.
     *
     * @param nombre El nombre de la persona.
     * @param email  El email de la persona.
     * @param dni    El DNI de la persona.
     */
    public Persona(String nombre, String email, String dni) {
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param nombre El nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el email de la persona.
     *
     * @return El email de la persona.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email de la persona.
     *
     * @param email El email de la persona.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el DNI de la persona.
     *
     * @return El DNI de la persona.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI de la persona.
     *
     * @param dni El DNI de la persona.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Comprueba si esta instancia de Persona es igual a otro objeto.
     *
     * @param o El objeto con el que se desea comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(nombre, persona.nombre) &&
                Objects.equals(email, persona.email) &&
                Objects.equals(dni, persona.dni);
    }

    /**
     * Calcula el código hash de esta instancia de Persona.
     *
     * @return El código hash calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, email, dni);
    }

    /**
     * Devuelve una representación en forma de cadena de esta instancia de Persona.
     *
     * @return La representación en forma de cadena de la persona.
     */
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}

