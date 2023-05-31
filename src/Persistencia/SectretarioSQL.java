package Persistencia;

import Modelo.Persona;
import Modelo.Secretario;
import Modelo.TierSecretario;
import Modelo.TipoContrato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectretarioSQL {
    private ConexionBBDD conexionBBDD=new ConexionBBDD();
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private List<Secretario> secretarios=listarSecretarios();
    public boolean registrarSecretario(Secretario secretario) {
        String sql="INSERT INTO secretario (nombre,apellido,email,dni,sueldo,tipoContrato,tierSecretario,estado)"+"VALUES(?,?,?,?,?,?,?,?)";
        try {
            con=conexionBBDD.getConexion();
            pst=con.prepareStatement(sql);
            pst.setString(1,secretario.getNombre().toLowerCase());
            pst.setString(2,secretario.getApellido().toLowerCase());
            pst.setString(3,secretario.getEmail().toLowerCase());
            pst.setString(4,secretario.getDni().toLowerCase());
            pst.setFloat(5,secretario.getSueldo());
            pst.setString(6,secretario.getTipoContrato().toString().toLowerCase());
            pst.setString(6,secretario.getTierSecretario().toString().toLowerCase());
            pst.setBoolean(7,true);
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
    public Secretario buscarSecretario (String dni) {
        Secretario secretario=null;
        String SQL = "SELECT * FROM secretario where dni = ?";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            pst.setString(1,dni);
            rs = pst.executeQuery();
            if (rs.next()) {
                secretario=generarSecretario(rs);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return secretario;
    }

    private TierSecretario validarTierSecretario(String tierSecretarioBuscado){
        TierSecretario tierSecretario;
        if (tierSecretarioBuscado.equalsIgnoreCase("TIER_SECRETARIOA")){
            tierSecretario= TierSecretario.TIER_SECRETARIOA;
        }else if (tierSecretarioBuscado.equalsIgnoreCase("TIER_SECRETARIOB")){
            tierSecretario= TierSecretario.TIER_SECRETARIOB;
        }else{
            tierSecretario= TierSecretario.TIER_SECRETARIOC;
        }
        return tierSecretario;
    }
    private TipoContrato validarTipoContrato(String tipoContratoBuscado){
        TipoContrato tipoContrato;
        if (tipoContratoBuscado.equalsIgnoreCase("TIER_SECRETARIOA")){
            tipoContrato= TipoContrato.CONTRATOA;
        }else if (tipoContratoBuscado.equalsIgnoreCase("TIER_SECRETARIOB")){
            tipoContrato= TipoContrato.CONTRATOB;
        }else{
            tipoContrato= TipoContrato.CONTRATOC;
        }
        return tipoContrato;
    }
    public Secretario buscarSecretarioDniEmail (String dni,String email) {
        Secretario secretario=null;
        int flag=0;
        int i=0;
        if(secretarios!=null){
            while (!secretarios.isEmpty() && flag==0 && i<secretarios.size()){
                if(secretarios.get(i).getDni().equalsIgnoreCase(dni) && secretarios.get(i).getEmail().equalsIgnoreCase(email)){
                    secretario=secretarios.get(i);
                    flag=1;
                }
                i++;
            }
        }
        return secretario;
    }
    public List listarSecretarios () {
        List<Secretario> listaUsuarios = new ArrayList<>();
        String SQL = "SELECT * FROM secretario";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Secretario secretario=generarSecretario(rs);
                listaUsuarios.add(secretario);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return listaUsuarios;
    }
    private Secretario generarSecretario(ResultSet rs) throws SQLException {
        Secretario secretario = new Secretario(rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getString("dni"),
                rs.getFloat("sueldo"),
                validarTipoContrato(rs.getString("tipoContrato")),
                validarTierSecretario(rs.getString("tierSecretario")),
                rs.getBoolean("estado")
        );
        return secretario;
    }
}
