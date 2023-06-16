package Persistencia;

import Interfaz.I_PersistenciaSQL;
import Modelo.Sesion;
import Modelo.Turno;
import Modelo.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Esta clase representa una sesión de SQL.
 * Extiende la clase JDialog e implementa la interfaz I_PersistenciaSQL.
 */
public class SesionSQL extends JDialog implements I_PersistenciaSQL {

    private final ConexionBBDD conexionBBDD = new ConexionBBDD();
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    /**
     * Registra un objeto en la base de datos.
     *
     * @param elemento El objeto a registrar.
     */
    @Override
    public void registrar(Object elemento) {
        String sql = "INSERT INTO sesion (dniUsuario, motivoConsulta, fechaConsulta, horarioConsulta, descripcionPsicologo)"
                + " VALUES (?, ?, ?, ?, ?)";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(sql);
            Sesion sesion = (Sesion) elemento;
            pst.setString(1, sesion.getTurno().getDniUsuario());
            pst.setString(2, sesion.getTurno().getMotivoConsulta());
            pst.setString(3, sesion.getTurno().getFechaConsulta());
            pst.setString(4, sesion.getTurno().getHorarioConsulta());
            pst.setString(5, sesion.getResumenSesion());
            pst.execute();
            JOptionPane.showMessageDialog(this, "Sesión generada con éxito");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Obtiene una lista de todas las sesiones almacenadas en la base de datos.
     *
     * @return La lista de sesiones.
     */
    public HashSet listar() {
        HashSet listaSesiones = new HashSet();
        String SQL = "SELECT * FROM sesion";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Sesion sesion = generarSesion(rs);
                listaSesiones.add(sesion);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return listaSesiones;
    }

    /**
     * Genera una instancia de la clase Sesion a partir de un ResultSet.
     *
     * @param rs El ResultSet con los datos de la sesión.
     * @return La instancia de Sesion generada.
     * @throws SQLException Si ocurre algún error al acceder a los datos del ResultSet.
     */
    private Sesion generarSesion(ResultSet rs) throws SQLException {
        Turno turno = new Turno(rs.getString("dniUsuario"),
                rs.getString("motivoConsulta"),
                rs.getString("fechaConsulta"),
                rs.getString("horarioConsulta"));
        Sesion sesion = new Sesion(turno, rs.getString("descripcionPsicologo"));
        return sesion;
    }

    /**
     * Modifica un objeto en la base de datos.
     *
     * @param elemento El objeto a modificar.
     * @return true si la modificación se realizó con éxito, false en caso contrario.
     */
    @Override
    public boolean modificar(Object elemento) {
        String SQL = "UPDATE turno SET dniUsuario = ?, fechaConsulta = ?, horarioConsulta = ?, descripcionPsicologo = ? WHERE fechaConsulta = ? AND horarioConsulta = ?";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            Sesion sesion = (Sesion) elemento;
            pst.setString(1, sesion.getTurno().getDniUsuario());
            pst.setString(2, sesion.getTurno().getFechaConsulta());
            pst.setString(3, sesion.getTurno().getHorarioConsulta());
            pst.setString(4, sesion.getResumenSesion());
            pst.setString(5, sesion.getTurno().getFechaConsulta());
            pst.setString(6, sesion.getTurno().getHorarioConsulta());
            pst.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
}
