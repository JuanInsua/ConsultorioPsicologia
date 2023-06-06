package Persistencia;

import Modelo.Turno;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TurnoSQL extends JDialog {
    private final ConexionBBDD conexionBBDD=new ConexionBBDD();
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public void registrarTurno(Turno turno) {
        String sql="INSERT INTO turno (dniUsuario,motivoConsulta,fechaConsulta,horarioConsulta,estado)"+"VALUES(?,?,?,?,?)";
        try {
            con=conexionBBDD.getConexion();
            pst=con.prepareStatement(sql);
            pst.setString(1,turno.getDniUsuario());
            pst.setString(2,turno.getMotivoConsulta());
            pst.setString(3,turno.getFechaConsulta());
            pst.setString(4,turno.getHorarioConsulta());
            pst.setBoolean(5,true);
            pst.execute();
            JOptionPane.showMessageDialog(this,
                    "Usuario Registrado con Exito"
            );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public ArrayList<Turno> listarTurnos () {
        ArrayList<Turno> listaTurnos = new ArrayList<>();
        String SQL = "SELECT * FROM turno";
        try {
            Connection con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Turno turno=generarTurno(rs);
                listaTurnos.add(turno);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception e){
                //System.out.println(e.toString());
            }
        }
        return listaTurnos;
    }

    private Turno generarTurno(ResultSet rs) throws SQLException {
        Turno turno = new Turno(rs.getString("dniUsuario"),
                rs.getString("motivoConsulta"),
                rs.getString("fechaConsulta"),
                rs.getString("horarioConsulta"),
                rs.getBoolean("estado"));
        return turno;
    }
}
