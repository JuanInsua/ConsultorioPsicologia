package Exeption;

import java.util.Objects;

public class TurnoExeption extends Exception{
    private String mensaje;
    public TurnoExeption(String mensaje){
        this.mensaje=mensaje;
    }
    public String getMensaje(){
        return mensaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurnoExeption that = (TurnoExeption) o;
        return Objects.equals(mensaje, that.mensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mensaje);
    }

    @Override
    public String toString() {
        return "TurnosExeption{" +
                "mensaje='" + mensaje + '\'' +
                '}';
    }
}
