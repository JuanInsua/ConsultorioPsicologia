package Modelo;

import java.util.Objects;

public class Usuario {
    private Paciente paciente;
    private String password;
    private String palabraRecuperacion;
    private boolean estado;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String email, String dni, String obraSocial, String password, String palabraRecuperacion, boolean estado) {
        this.paciente = new Paciente(nombre, apellido, email, dni, obraSocial);
        this.password = password;
        this.palabraRecuperacion = palabraRecuperacion;
        this.estado = estado;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPalabraRecuperacion() {
        return palabraRecuperacion;
    }
    public void setPalabraRecuperacion(String palabraRecuperacion) {
        this.palabraRecuperacion = palabraRecuperacion;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return estado == usuario.estado && Objects.equals(paciente, usuario.paciente) && Objects.equals(password, usuario.password) && Objects.equals(palabraRecuperacion, usuario.palabraRecuperacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paciente, password, palabraRecuperacion, estado);
    }

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
