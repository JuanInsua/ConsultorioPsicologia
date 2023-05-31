package Modelo;

import java.util.Objects;

public class Psicologo extends Persona{
    private  String legajoProfesional;
    private OrientacionTeorica orientacionTeorica;
    private float tarifaPorHora,porcentajeTolerancia;
    private boolean estado;

    public Psicologo(String nombre, String apellido, String email, String dni, String legajoProfesional, OrientacionTeorica orientacionTeorica, float tarifaPorHora, float porcentajeTolerancia, boolean estado) {
        super(nombre, apellido, email, dni);
        this.legajoProfesional = legajoProfesional;
        this.orientacionTeorica = orientacionTeorica;
        this.tarifaPorHora = tarifaPorHora;
        this.porcentajeTolerancia = porcentajeTolerancia;
        this.estado = estado;
    }

    public String getLegajoProfesional() {
        return legajoProfesional;
    }
    public void setLegajoProfesional(String legajoProfesional) {
        this.legajoProfesional = legajoProfesional;
    }
    public OrientacionTeorica getOrientacionTeorica() {
        return orientacionTeorica;
    }
    public void setOrientacionTeorica(OrientacionTeorica orientacionTeorica) {
        this.orientacionTeorica = orientacionTeorica;
    }
    public float getTarifaPorHora() {
        return tarifaPorHora;
    }
    public void setTarifaPorHora(float tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }
    public float getPorcentajeTolerancia() {
        return porcentajeTolerancia;
    }
    public void setPorcentajeTolerancia(float porcentajeTolerancia) {
        this.porcentajeTolerancia = porcentajeTolerancia;
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
        if (!super.equals(o)) return false;
        Psicologo psicologo = (Psicologo) o;
        return Float.compare(psicologo.tarifaPorHora, tarifaPorHora) == 0 && Float.compare(psicologo.porcentajeTolerancia, porcentajeTolerancia) == 0 && estado == psicologo.estado && Objects.equals(legajoProfesional, psicologo.legajoProfesional) && orientacionTeorica == psicologo.orientacionTeorica;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), legajoProfesional, orientacionTeorica, tarifaPorHora, porcentajeTolerancia, estado);
    }
    @Override
    public String toString() {
        return "Psicologo{" +
                "legajoProfesional='" + legajoProfesional + '\'' +
                ", orientacionTeorica=" + orientacionTeorica +
                ", tarifaPorHora=" + tarifaPorHora +
                ", porcentajeTolerancia=" + porcentajeTolerancia +
                ", estado=" + estado +
                '}';
    }
}
