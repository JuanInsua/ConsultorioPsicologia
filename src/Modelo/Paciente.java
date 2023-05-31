package Modelo;

import java.util.Objects;

public class Paciente extends Persona{
    private String obraSocial;
    public Paciente(){

    }
    public Paciente(String obraSocial) {
        this.obraSocial = obraSocial;
    }
    public Paciente(String nombre, String apellido, String email , String dni, String obraSocial) {
        super(nombre, apellido, email, dni);
        this.obraSocial = obraSocial;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(obraSocial, paciente.obraSocial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), obraSocial);
    }

    @Override
    public String toString() {
        return super.toString()+"Paciente{" +
                "obraSocial='" + obraSocial + '\'' +
                '}';
    }
}
