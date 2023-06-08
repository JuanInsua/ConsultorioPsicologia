package Exeption;

import java.util.Objects;

public class DiaExeption extends Exception{
    private String mensaje;
    public DiaExeption(String mensaje){
        this.mensaje=mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return "DiaExeption{" +
                "mensaje='" + mensaje + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaExeption that = (DiaExeption) o;
        return Objects.equals(mensaje, that.mensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mensaje);
    }
}
