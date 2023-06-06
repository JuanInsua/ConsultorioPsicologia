package Modelo;

import java.util.Objects;

public class Turno {
    private String motivoConsulta,fechaConsulta,horarioConsulta,dniUsuario;
    private boolean estado;
    public Turno(){

    }
    public Turno(String dniUsuario,String motivoConsulta, String fechaConsulta, String horarioConsulta, boolean estado) {
        this.dniUsuario=dniUsuario;
        this.motivoConsulta = motivoConsulta;
        this.fechaConsulta = fechaConsulta;
        this.horarioConsulta = horarioConsulta;
        this.estado = estado;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(String horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "motivoConsulta='" + motivoConsulta + '\'' +
                ", fechaConsulta='" + fechaConsulta + '\'' +
                ", horarioConsulta='" + horarioConsulta + '\'' +
                ", estado=" + estado +
                ", dniUsuario=" + dniUsuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return estado == turno.estado && dniUsuario.equalsIgnoreCase(turno.dniUsuario) && Objects.equals(motivoConsulta, turno.motivoConsulta) && Objects.equals(fechaConsulta, turno.fechaConsulta) && Objects.equals(horarioConsulta, turno.horarioConsulta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(motivoConsulta, fechaConsulta, horarioConsulta, estado, dniUsuario);
    }
}
