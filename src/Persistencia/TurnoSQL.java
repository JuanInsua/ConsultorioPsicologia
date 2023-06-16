package Persistencia;

import Interfaz.I_PersistenciaSQL;
import Modelo.Estado;
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

 Esta clase representa una implementación de la interfaz I_PersistenciaSQL y

 se utiliza para realizar operaciones de persistencia en la tabla "turno" de una base de datos.

 Extiende la clase JDialog para proporcionar una ventana de diálogo y utiliza un tipo genérico T.
 */
public class TurnoSQL <T> extends JDialog implements I_PersistenciaSQL {

    private final ConexionBBDD conexionBBDD = new ConexionBBDD();
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    /**

     Registra un elemento en la tabla "turno".
     @param elemento El elemento a registrar.
     */
    @Override
    public void registrar(Object elemento) {
        String sql = "INSERT INTO turno (dniUsuario, motivoConsulta, fechaConsulta, horarioConsulta, estado)"
                + " VALUES(?, ?, ?, ?, ?)";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(sql);
            Turno turno = (Turno) elemento;
            pst.setString(1, turno.getDniUsuario());
            pst.setString(2, turno.getMotivoConsulta());
            pst.setString(3, turno.getFechaConsulta());
            pst.setString(4, turno.getHorarioConsulta());
            pst.setString(5, turno.getEstado().name());
            pst.execute();
            JOptionPane.showMessageDialog(this, "Turno agregado con éxito");
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

     Retorna una lista de turnos almacenados en la tabla "turno".
     @return Una lista de objetos Turno.
     */
    public ArrayList<Turno> listar() {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return listaTurnos;
    }
    /**

     Genera un objeto Turno a partir de un ResultSet.
     @param rs El ResultSet que contiene los datos del turno.
     @return Un objeto Turno.
     @throws SQLException Si ocurre algún error al acceder a los datos del ResultSet.
     */
    private Turno generarTurno(ResultSet rs) throws SQLException {
        Turno turno = new Turno(rs.getString("dniUsuario"),
                rs.getString("motivoConsulta"),
                rs.getString("fechaConsulta"),
                rs.getString("horarioConsulta"));
        if (rs.getString("estado").equalsIgnoreCase("activado")){
            turno.setEstado(Estado.ACTIVADO);
        } else if (rs.getString("estado").equalsIgnoreCase("cancelado")){
            turno.setEstado(Estado.CANCELADO);
        } else if (rs.getString("estado").equalsIgnoreCase("atendido")){
            turno.setEstado(Estado.ATENDIDO);
        }
        return turno;
    }
    /**

     Modifica un elemento en la tabla "turno".
     @param elemento El elemento a modificar.
     @return true si la modificación fue exitosa, false en caso contrario.
     */
    @Override
    public boolean modificar(Object elemento) {
        String SQL = "UPDATE turno SET dniUsuario = ?, motivoConsulta = ?, fechaConsulta = ?, horarioConsulta = ?, estado = ? WHERE fechaConsulta = ? AND horarioConsulta = ?";
        if (elemento != null) {
            try {
                con = conexionBBDD.getConexion();
                pst = con.prepareStatement(SQL);
                Turno turno = (Turno) elemento;
                pst.setString(1, turno.getDniUsuario());
                pst.setString(2, turno.getMotivoConsulta());
                pst.setString(3, turno.getFechaConsulta());
                pst.setString(4, turno.getHorarioConsulta());
                pst.setString(5, turno.getEstado().name());
                pst.setString(6, turno.getFechaConsulta());
                pst.setString(7, turno.getHorarioConsulta());
                pst.execute();
                JOptionPane.showMessageDialog(this, "Turno modificado con éxito");
                return true;
            } catch (SQLException e) {
                System.out.println("Error al modificar el turno: " + e.getMessage());
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return false;
    }
    /**

     Busca un turno en la tabla "turno" por fecha y horario.
     @param fecha La fecha de consulta.
     @param horario El horario de consulta.
     @return El objeto Turno encontrado, o null si no se encuentra.
     */
    public Turno buscarTurno(String fecha, String horario) {
        Turno turno = null;
        String SQL = "SELECT * FROM turno WHERE fechaConsulta = ? AND horarioConsulta = ?";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            pst.setString(1, fecha);
            pst.setString(2, horario);
            rs = pst.executeQuery();
            if (rs.next()) {
                turno = new Turno(rs.getString("dniUsuario"),
                        rs.getString("motivoConsulta"),
                        rs.getString("fechaConsulta"),
                        rs.getString("horarioConsulta"));
                if (rs.getString("estado").equalsIgnoreCase("activado")) {
                    turno.setEstado(Estado.ACTIVADO);
                } else if (rs.getString("estado").equalsIgnoreCase("cancelado")) {
                    turno.setEstado(Estado.CANCELADO);
                } else if (rs.getString("estado").equalsIgnoreCase("atendido")) {
                    turno.setEstado(Estado.ATENDIDO);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return turno;
    }
    /**

     Modifica el estado de un turno en la tabla "turno".
     @param turnoBuscado El turno a modificar.
     @param estado El nuevo estado del turno.
     @return true si la modificación fue exitosa, false en caso contrario.
     */
    public boolean modificarEstado(Turno turnoBuscado, String estado) {
        String SQL = "UPDATE turno SET estado = ? WHERE fechaConsulta = ? AND horarioConsulta = ?";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            pst.setString(1, estado);
            pst.setString(2, turnoBuscado.getFechaConsulta());
            pst.setString(3, turnoBuscado.getHorarioConsulta());
            pst.execute();
            return true;
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
