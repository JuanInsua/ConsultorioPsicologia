package Modelo;

import java.util.Objects;
/**
 * The Usuario class represents a user in the system.
 */
public class Usuario {
    private Paciente paciente;
    private String password;
    private String palabraRecuperacion;
    private boolean estado;

    /**
     * Constructs a new Usuario object with default values.
     */
    public Usuario() {
    }

    /**
     * Constructs a new Usuario object with the specified parameters.
     *
     * @param nombre              the nombre of the Paciente
     * @param apellido            the apellido of the Paciente
     * @param email               the email of the Paciente
     * @param dni                 the dni of the Paciente
     * @param obraSocial          the obra social of the Paciente
     * @param password            the password of the Usuario
     * @param palabraRecuperacion the recovery word of the Usuario
     * @param estado              the state of the Usuario
     */
    public Usuario(String nombre, String apellido, String email, String dni, String obraSocial, String password, String palabraRecuperacion, boolean estado) {
        this.paciente = new Paciente(nombre, apellido, email, dni, obraSocial);
        this.password = password;
        this.palabraRecuperacion = palabraRecuperacion;
        this.estado = estado;
    }

    /**
     * Returns the Paciente object associated with this Usuario.
     *
     * @return the Paciente object
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Sets the Paciente object associated with this Usuario.
     *
     * @param paciente the Paciente object
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Returns the password of this Usuario.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of this Usuario.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the recovery word of this Usuario.
     *
     * @return the recovery word
     */
    public String getPalabraRecuperacion() {
        return palabraRecuperacion;
    }

    /**
     * Sets the recovery word of this Usuario.
     *
     * @param palabraRecuperacion the recovery word
     */
    public void setPalabraRecuperacion(String palabraRecuperacion) {
        this.palabraRecuperacion = palabraRecuperacion;
    }

    /**
     * Returns the state of this Usuario.
     *
     * @return the state
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Sets the state of this Usuario.
     *
     * @param estado the state
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return estado == usuario.estado && Objects.equals(paciente, usuario.paciente) && Objects.equals(password, usuario.password) && Objects.equals(palabraRecuperacion, usuario.palabraRecuperacion);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(paciente, password, palabraRecuperacion, estado);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return super.toString()+"Usuario{" +
                "paciente=" + paciente +
                ", password='" + password + '\'' +
                ", palabraRecuperacion='" + palabraRecuperacion + '\'' +
                ", estado=" + estado +
                '}';
    }
}
