package Modelo;

import java.util.Objects;

public class Secretario extends Persona{
    private Float sueldo;
    private TipoContrato tipoContrato;
    private TierSecretario tierSecretario;
    private boolean estado;

    public Secretario(){

    }
    public Secretario(String nombre, String apellido, String email, String dni, Float sueldo, TipoContrato tipoContrato, TierSecretario tierSecretario, boolean estado) {
        super(nombre, apellido, email, dni);
        this.sueldo = sueldo;
        this.tipoContrato=tipoContrato;
        this.tierSecretario=tierSecretario;
        this.estado = estado;
    }
    public Float getSueldo() {
        return sueldo;
    }
    public void setSueldo(Float sueldo) {
        this.sueldo = sueldo;
    }
    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }
    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    public TierSecretario getTierSecretario() {
        return tierSecretario;
    }
    public void setTierSecretario(TierSecretario tierSecretario) {
        this.tierSecretario = tierSecretario;
    }
    public boolean getEstado() {
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
        Secretario that = (Secretario) o;
        return Objects.equals(sueldo, that.sueldo) && tipoContrato == that.tipoContrato && tierSecretario == that.tierSecretario && Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sueldo, tipoContrato, tierSecretario, estado);
    }

    @Override
    public String toString() {
        return super.toString()+"Secretario{" +
                "sueldo=" + sueldo +
                ", tipoContrato=" + tipoContrato +
                ", tierSecretario=" + tierSecretario +
                ", estado=" + estado +
                '}';
    }
}
