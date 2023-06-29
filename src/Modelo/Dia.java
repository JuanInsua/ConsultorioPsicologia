package Modelo;

import java.util.ArrayList;

/**
 * La clase Dia representa un día de la semana en un calendario.
 * Contiene una lista de turnos y un nombre para el día.
 */
public class Dia {
    private ArrayList<Turno> turnos;
    private String nombreDia;

    /**
     * Crea un nuevo objeto Dia con el nombre especificado.
     *
     * @param nombreDia El nombre del día.
     */
    public Dia(String nombreDia) {
        turnos = new ArrayList<>();
        this.nombreDia = nombreDia;
    }

    /**
     * Obtiene el nombre del día.
     *
     * @return El nombre del día.
     */
    public String getNombreDia() {
        return nombreDia;
    }

    public ArrayList<Turno> getTurnos() {
        return turnos;
    }

    /**
     * Establece el nombre del día.
     *
     * @param nombreDia El nuevo nombre del día.
     */
    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    /**
     * Agrega un turno a la lista de turnos del día.
     *
     * @param turno El turno a agregar.
     */
    public void agregarTurno(Turno turno) {
        turnos.add(turno);
    }

    /**
     * Devuelve una representación en cadena del objeto Dia.
     *
     * @return Una representación en cadena de la clase Dia.
     */
    @Override
    public String toString() {
        return "Dia{" +
                "nombreDia='" + nombreDia + '\'' +
                '}';
    }
}

