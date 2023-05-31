package Persistencia;

import Modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PsicologoSQL {
    private ConexionBBDD conexionBBDD=new ConexionBBDD();
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private List <Psicologo>psicologos=listarPsicologos();
    public boolean registrarPsicologo(Psicologo psicologo) {
        String sql="INSERT INTO psicologo (nombre,apellido,email,dni,legajoProfesional,orientacionTeorica,tarifaPorHora,porcentajeTolerancia,estado)"+"VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            con=conexionBBDD.getConexion();
            pst=con.prepareStatement(sql);
            pst.setString(1,psicologo.getNombre().toLowerCase());
            pst.setString(2,psicologo.getApellido().toLowerCase());
            pst.setString(3,psicologo.getEmail().toLowerCase());
            pst.setString(4,psicologo.getDni().toLowerCase());
            pst.setString(5,psicologo.getLegajoProfesional());
            pst.setString(6,psicologo.getOrientacionTeorica().toString().toLowerCase());
            pst.setFloat(7,psicologo.getTarifaPorHora());
            pst.setFloat(7,psicologo.getPorcentajeTolerancia());
            pst.setBoolean(9,true);
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
    public Psicologo buscarSecretario (String legajoProfesional) {
        Psicologo psicologo=null;
        String SQL = "SELECT * FROM psicologo where legajoProfesional = ?";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            pst.setString(1,legajoProfesional);
            rs = pst.executeQuery();
            if (rs.next()) {

                rs.getString("tierSecretario");
                psicologo=generarPsicologo(rs);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return psicologo;
    }

    private OrientacionTeorica validarOrientacionTeorica(String tierSecretarioBuscado){
        OrientacionTeorica orientacionTeorica;
        if (tierSecretarioBuscado.equalsIgnoreCase("COGNITIVOCONDUCTUAL")){
            orientacionTeorica= OrientacionTeorica.COGNITIVOCONDUCTUAL;
        }else if (tierSecretarioBuscado.equalsIgnoreCase("NEUROPSICOLOGIA")){
            orientacionTeorica= OrientacionTeorica.NEUROPSICOLOGIA;
        }else if (tierSecretarioBuscado.equalsIgnoreCase("PSICOANALITICA")){
            orientacionTeorica= OrientacionTeorica.PSICOANALITICA;
        }else{
            orientacionTeorica= OrientacionTeorica.SISTEMICABREVE;
        }
        return orientacionTeorica;
    }
    public Psicologo buscarPsicologoDniLegajo (String dni,String legajoProfesional) {
        Psicologo psicologo=null;
        int flag=0;
        int i=0;
        if(psicologos!=null){
            while (!psicologos.isEmpty() && flag==0 && i<psicologos.size()){
                if(psicologos.get(i).getDni().equalsIgnoreCase(dni) && psicologos.get(i).getLegajoProfesional().equalsIgnoreCase(legajoProfesional)){
                    psicologo=psicologos.get(i);
                    flag=1;
                }
                i++;
            }
        }
        return psicologo;
    }
    public List listarPsicologos () {
        List<Psicologo> listaPsicologos = new ArrayList<>();
        String SQL = "SELECT * FROM psicologo";
        try {
            con = conexionBBDD.getConexion();
            pst = con.prepareStatement(SQL);
            rs = pst.executeQuery();

            while (rs.next()) {
                Psicologo psicologo=generarPsicologo(rs);
                listaPsicologos.add(psicologo);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return listaPsicologos;
    }
    private Psicologo generarPsicologo(ResultSet rs) throws SQLException {
        Psicologo psicologo  =new Psicologo(rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getString("dni"),
                rs.getString("legajoProfesional"),
                validarOrientacionTeorica(rs.getString("orientacionTeorica")),
                rs.getFloat("tarifaPorHora"),
                rs.getFloat("porcentajeTolerancia"),
                rs.getBoolean("estado")
        );
        return psicologo;
    }
}
