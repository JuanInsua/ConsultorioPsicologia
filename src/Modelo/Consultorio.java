package Modelo;

import Persistencia.TurnoSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * The Consultorio class represents a medical clinic.
 * It manages the calendar and scheduling of appointments.
 */
public class Consultorio {
    ArrayList<Dia> calendario;
    TurnoSQL turnoSQL = new TurnoSQL();

    /**
     * Constructs a Consultorio object with an empty calendar.
     * The calendar is initialized with days of the week: Monday to Friday.
     */
    public Consultorio() {
        this.calendario = new ArrayList<>();
        Dia lunes = new Dia("lunes");
        Dia martes = new Dia("martes");
        Dia miercoles = new Dia("miercoles");
        Dia jueves = new Dia("jueves");
        Dia viernes = new Dia("viernes");
        calendario.add(0, lunes);
        calendario.add(1, martes);
        calendario.add(2, miercoles);
        calendario.add(3, jueves);
        calendario.add(4, viernes);
    }

    /**
     * Loads appointments from the database and populates the calendar.
     * Existing appointments are added to the corresponding day in the calendar.
     */
    public void cargarCalendario() {
        ArrayList<Turno> turnos = turnoSQL.listarTurnos();
        for (Turno turno : turnos) {
            int indexDia = diaTurno(turno.getFechaConsulta());
            calendario.get(indexDia).agregarTurno(turno);
        }
    }

    /**
     * Maps a day string to its corresponding index in the calendar.
     *
     * @param diaInput the name of the day
     * @return the index of the day in the calendar (0-4 for Monday-Friday)
     */
    public int diaTurno(String diaInput) {
        int indexDia = 0;
        switch (diaInput) {
            case "lunes":
                indexDia = 0;
                break;
            case "martes":
                indexDia = 1;
                break;
            case "miercoles":
                indexDia = 2;
                break;
            case "jueves":
                indexDia = 3;
                break;
            case "viernes":
                indexDia = 4;
                break;
        }
        return indexDia;
    }

    /**
     * Maps a time slot string to its corresponding index in the calendar.
     *
     * @param horarioInput the time slot string
     * @return the index of the time slot in the calendar (0-4 for 8a9-12a13)
     */
    public int horarioTurno(String horarioInput) {
        int indexTurno = 0;
        switch (horarioInput) {
            case "8a9":
                indexTurno = 0;
                break;
            case "9a10":
                indexTurno = 1;
                break;
            case "10a11":
                indexTurno = 2;
                break;
            case "11a12":
                indexTurno = 3;
                break;
            case "12a13":
                indexTurno = 4;
                break;
        }
        return indexTurno;
    }

    /**
     * Retrieves the list of appointments for a given day.
     *
     * @param indexDia the index of the day in the calendar (0-4 for Monday-Friday)
     * @return the list of appointments for the specified day
     */
    public ArrayList<Turno> buscarDia(int indexDia) {
        cargarCalendario();
        return calendario.get(indexDia).turnos;
    }
    public ArrayList<Turno> listarTurnosUsuario(String dniUsuario){
        ArrayList<Turno>turnos=turnoSQL.listarTurnos();
        ArrayList<Turno>turnosUsuario=new ArrayList<>();
        for (int i=0;i<turnos.size();i++){
            if (turnos.get(i).getDniUsuario().equalsIgnoreCase(dniUsuario)){
                turnosUsuario.add(turnos.get(i));
            }
        }
        return turnosUsuario;
    }
}
