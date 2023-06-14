package Modelo;

import java.util.Objects;

/**
 * La clase Sesion representa una sesión con un turno y un resumen.
 */
public class Sesion {
    private Turno turno;
    private String resumenSesion;

    /**
     * Crea una nueva instancia de Sesion sin argumentos.
     */
    public Sesion(){

    }

    /**
     * Crea una nueva instancia de Sesion con el turno y resumen especificados.
     *
     * @param turno El turno de la sesión.
     * @param resumenSesion El resumen de la sesión.
     */
    public Sesion(Turno turno, String resumenSesion){
        this.turno = turno;
        this.resumenSesion = resumenSesion;
    }

    /**
     * Obtiene el resumen de la sesión.
     *
     * @return El resumen de la sesión.
     */
    public String getResumenSesion() {
        return resumenSesion;
    }

    /**
     * Establece el resumen de la sesión.
     *
     * @param resumenSesion El nuevo resumen de la sesión.
     */
    public void setResumenSesion(String resumenSesion) {
        this.resumenSesion = resumenSesion;
    }

    /**
     * Obtiene el turno de la sesión.
     *
     * @return El turno de la sesión.
     */
    public Turno getTurno() {
        return turno;
    }

    /**
     * Devuelve una representación en cadena de la sesión.
     *
     * @return Una representación en cadena de la sesión.
     */
    @Override
    public String toString() {
        return "Sesion{" +
                "turno=" + turno +
                ", resumenSesion='" + resumenSesion + '\'' +
                '}';
    }

    /**
     * Compara esta sesión con otro objeto para verificar la igualdad.
     *
     * @param o El objeto a comparar.
     * @return true si las sesiones son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sesion sesion = (Sesion) o;
        return Objects.equals(turno, sesion.turno) && Objects.equals(resumenSesion, sesion.resumenSesion);
    }

    /**
     * Calcula el código hash de la sesión.
     *
     * @return El código hash de la sesión.
     */
    @Override
    public int hashCode() {
        return Objects.hash(turno, resumenSesion);
    }
}
