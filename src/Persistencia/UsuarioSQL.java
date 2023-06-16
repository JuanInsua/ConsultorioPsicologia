package Persistencia;

import Exeption.UsuarioBuscadoException;
import Interfaz.I_PersistenciaSQL;
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
import java.util.TreeMap;

/**
 * Clase que representa una ventana de diálogo para interactuar con la base de datos de usuarios.
 */
public class UsuarioSQL extends JDialog implements I_PersistenciaSQL{
    private ConexionBBDD conexionBBDD=new ConexionBBDD();
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param elemento El objeto Usuario a registrar.
     */

    @Override
    public void registrar(Object elemento) {
        String sql="INSERT INTO usuario (nombreUser,dni,email,contrasenia,palabreRec,estado)"+"VALUES(?,?,?,?,?,?)";
        try {
            con=conexionBBDD.getConexion();
            pst=con.prepareStatement(sql);
            Usuario usuario=(Usuario)elemento;
            pst.setString(1,usuario.getNombre().toLowerCase());
            pst.setString(2,usuario.getDni().toLowerCase());
            pst.setString(3,usuario.getEmail().toLowerCase());
            pst.setString(4,usuario.getPassword());
            pst.setString(5,usuario.getPalabraRecuperacion().toLowerCase());
            pst.setBoolean(6,true);
            pst.execute();
            JOptionPane.showMessageDialog(this,
                    "Usuario Registrado con Exito"
            );
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Busca y devuelve un usuario en la base de datos según el número de documento.
     *
     * @param dni El número de documento del usuario a buscar.
     * @return El objeto Usuario correspondiente al número de documento especificado, o null si no se encuentra.
     */
    public Usuario buscarUsuarioDni (String dni) {
        Usuario usuario=null;
        String SQL = "SELECT * FROM usuario where dni = ?";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            pst.setString(1,dni);
            rs = pst.executeQuery();

            if (rs.next()) {
                usuario=new Usuario(rs.getString("nombreUser"),
                        rs.getString("email"),
                        rs.getString("dni"),
                        rs.getString("contrasenia"),
                        rs.getString("palabreRec"),
                        rs.getBoolean("estado")
                        );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return usuario;
    }

    public Usuario buscarUsuarioNombreUser(String nombreUser) {
        Usuario usuario=null;
        String SQL = "SELECT * FROM usuario where nombreUser = ?";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            pst.setString(1,nombreUser);
            rs = pst.executeQuery();

            if (rs.next()) {
                usuario=new Usuario(rs.getString("nombreUser"),
                        rs.getString("email"),
                        rs.getString("dni"),
                        rs.getString("contrasenia"),
                        rs.getString("palabreRec"),
                        rs.getBoolean("estado")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return usuario;
    }
    public Usuario buscarUsuarioEmail(String email) {
        Usuario usuario=null;
        String SQL = "SELECT * FROM usuario where email = ?";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            pst.setString(1,email);
            rs = pst.executeQuery();

            if (rs.next()) {
                usuario=new Usuario(rs.getString("nombreUser"),
                        rs.getString("email"),
                        rs.getString("dni"),
                        rs.getString("contrasenia"),
                        rs.getString("palabreRec"),
                        rs.getBoolean("estado")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return usuario;
    }
    /**
     * Devuelve una lista de todos los usuarios registrados en la base de datos.
     *
     * @return Una lista de objetos Usuario que representa todos los usuarios registrados.
     */
    public TreeMap listar() {
        TreeMap<String,Usuario> treeMapUsuarios=new TreeMap<>();
        String SQL = "SELECT * FROM usuario";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Usuario usuario=generarUsuario(rs);
                treeMapUsuarios.put(usuario.getDni(),usuario);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return treeMapUsuarios;
    }
    /**
     * Genera un objeto Usuario a partir de un ResultSet.
     *
     * @param rs El ResultSet que contiene los datos del usuario.
     * @return El objeto Usuario generado a partir de los datos del ResultSet.
     * @throws SQLException Si ocurre algún error al acceder a los datos del ResultSet.
     */
    private Usuario generarUsuario(ResultSet rs) throws SQLException {
        Usuario usuario=new Usuario(rs.getString("nombreUser"),
                rs.getString("email"),
                rs.getString("dni"),
                rs.getString("contrasenia"),
                rs.getString("palabreRec"),
                rs.getBoolean("estado")
        );
        return usuario;
    }

    /**
     * Modifica los datos de un usuario en la base de datos.
     *
     * @param elemento      El objeto Usuario con los nuevos datos.
     * @return true si la modificación se realiza correctamente, false en caso contrario.
     */
    @Override
    public boolean modificar(Object elemento) {
        String SQL = "UPDATE usuario SET nombreUser = ?, email = ?, contrasenia = ?,palabreRec = ?,estado =? where dni = ?";
        if (elemento!=null){
            try {
                con = conexionBBDD.getConexion();
                pst = con.prepareStatement(SQL);
                Usuario usuario=(Usuario)elemento;
                pst.setString(1, usuario.getNombre());
                pst.setString(2, usuario.getEmail());
                pst.setString(3, usuario.getPassword());
                pst.setString(4, usuario.getPalabraRecuperacion());
                pst.setBoolean(5, usuario.isEstado());
                pst.setString(6, usuario.getDni());
                pst.execute();
                JOptionPane.showMessageDialog(this,
                        "Usuario modificado con Exito"
                );
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    con.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return false;
    }
}
