package Modelo;

import Interfaz.I_ToJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * La clase Turno representa un turno de consulta médica.
 */
public class Turno implements I_ToJson {
    private String motivoConsulta, fechaConsulta, horarioConsulta, dniUsuario;
    private Estado estado;

    /**
     * Crea una instancia vacía de la clase Turno.
     */
    public Turno() {
    }

    /**
     * Crea una instancia de la clase Turno con los datos proporcionados.
     *
     * @param dniUsuario      el DNI del usuario que solicita el turno
     * @param motivoConsulta  el motivo de la consulta
     * @param fechaConsulta   la fecha de la consulta
     * @param horarioConsulta el horario de la consulta
     */
    public Turno(String dniUsuario, String motivoConsulta, String fechaConsulta, String horarioConsulta) {
        this.dniUsuario = dniUsuario;
        this.motivoConsulta = motivoConsulta;
        this.fechaConsulta = fechaConsulta;
        this.horarioConsulta = horarioConsulta;
    }

    /**
     * Establece el estado del turno.
     *
     * @param estado el estado del turno
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el estado del turno.
     *
     * @return el estado del turno
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Obtiene el DNI del usuario.
     *
     * @return el DNI del usuario
     */
    public String getDniUsuario() {
        return dniUsuario;
    }

    /**
     * Obtiene el motivo de la consulta.
     *
     * @return el motivo de la consulta
     */
    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    /**
     * Establece el motivo de la consulta.
     *
     * @param motivoConsulta el motivo de la consulta
     */
    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    /**
     * Obtiene la fecha de la consulta.
     *
     * @return la fecha de la consulta
     */
    public String getFechaConsulta() {
        return fechaConsulta;
    }

    /**
     * Establece la fecha de la consulta.
     *
     * @param fechaConsulta la fecha de la consulta
     */
    public void setFechaConsulta(String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    /**
     * Obtiene el horario de la consulta.
     *
     * @return el horario de la consulta
     */
    public String getHorarioConsulta() {
        return horarioConsulta;
    }

    /**
     * Establece el horario de la consulta.
     *
     * @param horarioConsulta el horario de la consulta
     */
    public void setHorarioConsulta(String horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    /**
     * Devuelve una representación en cadena de caracteres del objeto Turno.
     *
     * @return una representación en cadena de caracteres del objeto Turno
     */
    @Override
    public String toString() {
        return "Turno{" +
                "motivoConsulta='" + motivoConsulta + '\'' +
                ", fechaConsulta='" + fechaConsulta + '\'' +
                ", horarioConsulta='" + horarioConsulta + '\'' +
                ", dniUsuario='" + dniUsuario + '\'' +
                '}';
    }

    /**
     * Compara si el objeto especificado es igual a este objeto Turno.
     *
     * @param o el objeto a comparar
     * @return true si el objeto especificado es igual a este objeto Turno, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return dniUsuario.equalsIgnoreCase(turno.dniUsuario) &&
                Objects.equals(motivoConsulta, turno.motivoConsulta) &&
                Objects.equals(fechaConsulta, turno.fechaConsulta) &&
                Objects.equals(horarioConsulta, turno.horarioConsulta);
    }

    /**
     * Devuelve el valor hash para este objeto Turno.
     *
     * @return el valor hash para este objeto Turno
     */
    @Override
    public int hashCode() {
        return Objects.hash(motivoConsulta, fechaConsulta, horarioConsulta, dniUsuario);
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObjectTurno=new JSONObject();
        try {
            jsonObjectTurno.put("fechaConsulta",this.getFechaConsulta());
            jsonObjectTurno.put("horarioConsulta",this.getHorarioConsulta());
            jsonObjectTurno.put("motivoConsulta",this.getMotivoConsulta());
            jsonObjectTurno.put("dniUsuario",this.getDniUsuario());
        }catch (JSONException je){
            System.out.println(je.getMessage());
        }
        return jsonObjectTurno;
    }
}
