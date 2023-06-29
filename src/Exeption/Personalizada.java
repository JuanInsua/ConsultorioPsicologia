package Exeption;

import java.util.Date;

public class Personalizada extends RuntimeException {
    private String message;
    private Date fechaError;
    public Personalizada() {
    }
    public Personalizada(String message) {
        this.message = message;
        setFechaHorario(new Date());
    }
    @Override
    public String getMessage() {
        return message;
    }
    public Date getFechaError() {
        return fechaError;
    }

    public void setFechaHorario(Date fechaHorario) {
        this.fechaError = fechaHorario;
    }

    @Override
    public String toString() {
        return "Personalizada{" +
                "message='" + message + '\'' +
                ", fechaHorario=" + fechaError +
                '}';
    }
}
