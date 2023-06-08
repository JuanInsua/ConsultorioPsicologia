package Persistencia;

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
 * Clase que representa una ventana de diálogo para interactuar con la base de datos de usuarios.
 */
public class UsuarioSQL extends JDialog {
    private ConexionBBDD conexionBBDD=new ConexionBBDD();
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param usuario El objeto Usuario a registrar.
     */
    public void registrarUsuario(Usuario usuario) {
        String sql="INSERT INTO usuario (nombre,apellido,obraSocial,dni,email,contrasenia,palabreRec,estado)"+"VALUES(?,?,?,?,?,?,?,?)";
        try {
            con=conexionBBDD.getConexion();
            pst=con.prepareStatement(sql);
            pst.setString(1,usuario.getPaciente().getNombre().toLowerCase());
            pst.setString(2,usuario.getPaciente().getApellido().toLowerCase());
            pst.setString(3,usuario.getPaciente().getObraSocial().toLowerCase());
            pst.setString(4,usuario.getPaciente().getDni().toLowerCase());
            pst.setString(5,usuario.getPaciente().getEmail().toLowerCase());
            pst.setString(6,usuario.getPassword());
            pst.setString(7,usuario.getPalabraRecuperacion().toLowerCase());
            pst.setBoolean(8,true);
            pst.execute();
            JOptionPane.showMessageDialog(this,
                    "Usuario Registrado con Exito"
            );
        }catch (Exception e){
            System.out.println(e.toString());
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }
    /**
     * Busca y devuelve un usuario en la base de datos según el número de documento.
     *
     * @param dni El número de documento del usuario a buscar.
     * @return El objeto Usuario correspondiente al número de documento especificado, o null si no se encuentra.
     */
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
            throw new RuntimeException(e.toString());
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        return usuario;
    }
    /**
     * Busca y devuelve un usuario en la base de datos según el email y la contraseña especificados.
     *
     * @param input    El email del usuario a buscar.
     * @param password La contraseña del usuario a buscar.
     * @return El objeto Usuario correspondiente al email y contraseña especificados, o null si no se encuentra.
     */
    public Usuario buscarUsuarioPasswordEmail (String input,String password) {
        Usuario usuario=null;
        List <Usuario>usuarios=listarUsuarios();
        int flag=0;
        int i=0;
        if(usuarios!=null){
            while (!usuarios.isEmpty() && flag==0 && i<usuarios.size()){
                if((usuarios.get(i).getPaciente().getEmail().equalsIgnoreCase(input) || usuarios.get(i).getPaciente().getDni().equalsIgnoreCase(input))  && usuarios.get(i).getPassword().equalsIgnoreCase(password)){
                    usuario=usuarios.get(i);
                    flag=1;
                }
                i++;
            }
        }
        return usuario;
    }
    /**
     * Busca y devuelve la contraseña de un usuario en la base de datos según la palabra de recuperación
     * y el email especificados.
     *
     * @param palabraRec La palabra de recuperación del usuario.
     * @param input      El email del usuario.
     * @return La contraseña del usuario correspondiente a la palabra de recuperación y email especificados,
     * o null si no se encuentra.
     */
    public String buscarRetornarPw(String palabraRec,String input){
        String passwordBuscada=null;
        List <Usuario>usuarios=listarUsuarios();
        int flag=0;
        int i=0;
        if(usuarios!=null){
            while (!usuarios.isEmpty() && flag==0 && i<usuarios.size()){
                if( (usuarios.get(i).getPaciente().getEmail().equalsIgnoreCase(input) || usuarios.get(i).getPaciente().getDni().equalsIgnoreCase(input)) && usuarios.get(i).getPalabraRecuperacion().equalsIgnoreCase(palabraRec) ){
                    if (usuarios.get(i).isEstado()){
                        passwordBuscada="PASSWORD: "+usuarios.get(i).getPassword();

                    }else {
                        passwordBuscada="Usuario esta de baja";
                    }
                    flag=1;
                }else{
                    passwordBuscada="Usuario no existe";
                }
                i++;
            }
        }
        return passwordBuscada;
    }
    /**
     * Devuelve una lista de todos los usuarios registrados en la base de datos.
     *
     * @return Una lista de objetos Usuario que representa todos los usuarios registrados.
     */
    public List listarUsuarios (){
        ArrayList<Usuario>listaUsuarios=new ArrayList<>();
        String SQL = "SELECT * FROM usuario";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Usuario usuario=generarUsuario(rs);
                listaUsuarios.add(usuario);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        return listaUsuarios;
    }
    /**
     * Genera un objeto Usuario a partir de un ResultSet.
     *
     * @param rs El ResultSet que contiene los datos del usuario.
     * @return El objeto Usuario generado a partir de los datos del ResultSet.
     * @throws SQLException Si ocurre algún error al acceder a los datos del ResultSet.
     */
    private Usuario generarUsuario(ResultSet rs) throws SQLException {
        Usuario usuario=new Usuario(rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getString("dni"),
                rs.getString("obraSocial"),
                rs.getString("contrasenia"),
                rs.getString("palabreRec"),
                rs.getBoolean("estado")
        );
        return usuario;
    }
    /**
     * Modifica los datos de un usuario en la base de datos.
     *
     * @param usuario      El objeto Usuario con los nuevos datos.
     * @param dniBusqueda  El número de documento del usuario a modificar.
     * @return true si la modificación se realiza correctamente, false en caso contrario.
     */
    public  boolean modificarClientes (Usuario usuario,String dniBusqueda) {

        String SQL = "UPDATE usuario SET nombre = ?, apellido = ?, obraSocial = ?, dni = ?, email = ?, contrasenia = ?,palabreRec = ?,estado =? where dni = ?";

        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            pst.setString(1, usuario.getPaciente().getNombre());
            pst.setString(2, usuario.getPaciente().getApellido());
            pst.setString(3, usuario.getPaciente().getObraSocial());
            pst.setString(4, usuario.getPaciente().getDni());
            pst.setString(5, usuario.getPaciente().getEmail());
            pst.setString(6, usuario.getPassword());
            pst.setString(7, usuario.getPalabraRecuperacion());
            pst.setBoolean(8, usuario.isEstado());
            pst.setString(9, dniBusqueda);
            pst.execute();
            JOptionPane.showMessageDialog(this,
                    "Usuario modificado con Exito"
            );
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return false;
    }

}
