package Modelo;

import Persistencia.TurnoSQL;

import javax.swing.*;
import java.util.ArrayList;
public class Consultorio {
    ArrayList<Dia> calendario;
    TurnoSQL turnoSQL=new TurnoSQL();
    public Consultorio() {
        this.calendario = new ArrayList<>();
        Dia lunes=new Dia("lunes");
        Dia martes=new Dia("martes");
        Dia miercoles=new Dia("miercoles");
        Dia jueves=new Dia("jueves");
        Dia viernes=new Dia("viernes");
        calendario.add(0,lunes);
        calendario.add(1,martes);
        calendario.add(2,miercoles);
        calendario.add(3,jueves);
        calendario.add(4,viernes);
    }
    public void cargarCalendario() {
       ArrayList<Turno> turnos= turnoSQL.listarTurnos();
        for (Turno turno : turnos) {
            int indexDia = diaTurno(turno.getFechaConsulta());
            calendario.get(indexDia).agregarTurno(turno);
        }
    }
    public int diaTurno(String diaInput){
        int indexDia=0;
        switch (diaInput){
            case"lunes":
                indexDia=0;
                break;
            case"martes":
                indexDia=1;
                break;
            case"miercoles":
                indexDia=2;
                break;
            case"jueves":
                indexDia=3;
                break;
            case"viernes":
                indexDia=4;
                break;
        }
        return indexDia;
    }
    public int horarioTurno(String horarioInput){
        int indexTurno=0;
        switch (horarioInput){
            case "8a9":indexTurno=0;
                break;
            case "9a10":indexTurno=1;
                break;
            case "10a11":indexTurno=2;
                break;
            case "11a12":indexTurno=3;
                break;
            case "12a13":indexTurno=4;
                break;
        }
        return indexTurno;
    }

    public ArrayList<Turno> buscarDia(int indexDia) {
        cargarCalendario();
        return calendario.get(indexDia).turnos;
    }
}
