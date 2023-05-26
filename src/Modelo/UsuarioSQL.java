package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioSQL {
    ConexionBBDD conexionBBDD=new ConexionBBDD();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public boolean registrarUsuario(Usuario usuario) {
        String sql="INSERT INTO usuario (nombre,apellido,obraSocial,dni,email,contrasenia,palabreRec,estado)"+"VALUES(?,?,?,?,?,?,?,?)";
        try {
            con=conexionBBDD.getConexion();
            pst=con.prepareStatement(sql);
            pst.setString(1,usuario.getPaciente().getNombre());
            pst.setString(2,usuario.getPaciente().getApellido());
            pst.setString(3,usuario.getPaciente().getObraSocial());
            pst.setString(4,usuario.getPaciente().getDni());
            pst.setString(5,usuario.getPaciente().getEmail());
            pst.setString(6,usuario.getPassword());
            pst.setString(7,usuario.getPalabraRecuperacion());
            pst.setBoolean(8,true);
            pst.execute();
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }
    public Usuario buscarUsuario (String dni) {

        Usuario usuario=null;
        String SQL = "SELECT * FROM usuario where dni = ?";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            pst.setString(1,dni);
            rs = pst.executeQuery();

            if (rs.next()) {
                usuario=new Usuario(rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("dni"),
                        rs.getString("obraSocial"),
                        rs.getString("contrasenia"),
                        rs.getString("palabreRec"),
                        rs.getBoolean("estado")
                        );
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return usuario;
    }
}
