package Persistencia;

import Modelo.Turno;
import Modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The TurnoSQL class represents a database handler for managing Turno objects.
 * It extends the JDialog class.
 */
public class TurnoSQL extends JDialog {

    private final ConexionBBDD conexionBBDD = new ConexionBBDD();
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    /**
     * Registers a Turno object in the database.
     *
     * @param turno The Turno object to be registered.
     */
    public void registrarTurno(Turno turno) {
        String sql = "INSERT INTO turno (dniUsuario, motivoConsulta, fechaConsulta, horarioConsulta, estado)"
                + "VALUES(?, ?, ?, ?, ?)";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(sql);
            pst.setString(1, turno.getDniUsuario());
            pst.setString(2, turno.getMotivoConsulta());
            pst.setString(3, turno.getFechaConsulta());
            pst.setString(4, turno.getHorarioConsulta());
            pst.setBoolean(5, true);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Turno agregado con Exito");
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Retrieves a list of Turno objects from the database.
     *
     * @return An ArrayList of Turno objects.
     */
    public ArrayList<Turno> listarTurnos() {
        ArrayList<Turno> listaTurnos = new ArrayList<>();
        String SQL = "SELECT * FROM turno";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Turno turno = generarTurno(rs);
                listaTurnos.add(turno);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return listaTurnos;
    }

    /**
     * Generates a Turno object from the provided ResultSet.
     *
     * @param rs The ResultSet containing the Turno information.
     * @return The generated Turno object.
     * @throws SQLException If an SQL error occurs while retrieving data from the ResultSet.
     */
    private Turno generarTurno(ResultSet rs) throws SQLException {
        Turno turno = new Turno(rs.getString("dniUsuario"),
                rs.getString("motivoConsulta"),
                rs.getString("fechaConsulta"),
                rs.getString("horarioConsulta"),
                rs.getBoolean("estado"));
        return turno;
    }


}
