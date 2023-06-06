package Modelo;

import java.util.ArrayList;

public class Dia {
    ArrayList<Turno>turnos;
    private String nombreDia;
    public Dia(String nombreDia){
        turnos=new ArrayList<>();
        this.nombreDia=nombreDia;
    }
    public String getNombreDia() {
        return nombreDia;
    }
    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }
    public void agregarTurno(Turno turno){
        turnos.add(turno);
    }

    @Override
    public String toString() {
        return "Dia{" +
                "nombreDia='" + nombreDia + '\'' +
                '}';
    }
}
