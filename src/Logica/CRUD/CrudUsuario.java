package Logica.CRUD;

import Modelo.Usuario;
import Persistencia.UsuarioSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CrudUsuario extends JDialog {
    private JTable table1;
    private JPanel crudUsuario;
    private JButton listarUsuariosButton;
    private JButton VOLVERButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton buscarButton;
    private JTextField textField8;
    private JTextField textField9;
    UsuarioSQL usuarioSQL=new UsuarioSQL();
    public CrudUsuario(Frame parent) {
        super(parent);
        setTitle("Administracion");
        setContentPane(crudUsuario);
        setMinimumSize(new Dimension(1420, 820));
        setModal(true);
        setLocationRelativeTo(parent);

        VOLVERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        listarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(usuarioSQL.listarEnTabla());
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCamposUsuario();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBuscarPorDni();
            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioSQL.modificarClientes(setUsuarioModificar(),textField7.getText());
            }
        });
        setVisible(true);
    }
    private void limpiarCamposUsuario(){
        textField1.setText("");
        textField2.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
        textField8.setText("");
        textField9.setText("");
    }
    private Usuario setUsuarioModificar(){
        Usuario usuarioBuscado=null;
        if(validacionCamposModificarUsuarios(textField1.getText(),textField2.getText(),textField3.getText(),textField4.getText(),textField5.getText(),textField6.getText(),textField9.getText(),textField8.getText())){
            Boolean estado;
            if (textField8.getText().equalsIgnoreCase("true")){
                estado=true;
            }else {
                estado=false;
            }
            usuarioBuscado=new Usuario(textField1.getText(),
                    textField2.getText(),textField5.getText(),
                    textField4.getText(),textField3.getText(),
                    textField9.getText(),textField6.getText(),
                    estado);
        }
        return usuarioBuscado;
    }
    private boolean validacionCamposModificarUsuarios(String nombre,String apellido,String obraSocial,String dni,String email,String palabraRec,String password,String estado){
        boolean rta=true;
        if(nombre.isEmpty()||apellido.isEmpty()||email.isEmpty()||obraSocial.isEmpty()||password.isEmpty()||dni.isEmpty()||estado.isEmpty()||palabraRec.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Por favor completar, todos los campos",
                    "Intentelo otra vez",
                    JOptionPane.ERROR_MESSAGE);
            rta=false;
        }
        return rta;
    }
    public void getBuscarPorDni(){
        try{
            Usuario usuarioBuscado=usuarioSQL.buscarUsuario(textField7.getText());
            textField1.setText(usuarioBuscado.getPaciente().getNombre());
            textField2.setText(usuarioBuscado.getPaciente().getApellido());
            textField3.setText(usuarioBuscado.getPaciente().getObraSocial());
            textField4.setText(usuarioBuscado.getPaciente().getDni());
            textField5.setText(usuarioBuscado.getPaciente().getEmail());
            textField6.setText(usuarioBuscado.getPalabraRecuperacion());
            textField9.setText(usuarioBuscado.getPassword());
            String estado;
            if (usuarioBuscado.isEstado()){
                estado="true";
            }else {
                estado="false";
            }
            textField8.setText(estado);
        }
        catch (RuntimeException r){
            JOptionPane.showMessageDialog(this,
                    "Error en la busqueda",
                    "Intentelo otra vez",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
