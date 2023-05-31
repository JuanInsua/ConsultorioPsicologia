package Logica.Principal;

import Modelo.Usuario;
import Persistencia.UsuarioSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroForm extends JDialog {
    private JButton REGISTRARButton;
    private JButton SALIRButton;
    private JPanel registroForm;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField5;
    UsuarioSQL usuarioSQL=new UsuarioSQL();
    public RegistroForm (JFrame parent){
     super(parent);
     setTitle("Crear nuevo usuario");
     setContentPane(registroForm);
     setMinimumSize(new Dimension(680,620));
     setModal(true);
     setLocationRelativeTo(parent);
        //Boton Salir
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //Boton Registrar
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registroConValidacionCampos();
            }
        });
        setVisible(true);
    }

    //Registro con validacion de campos del formulario.
    private void registroConValidacionCampos() {
        //Extraemos todos los campos del formulario a variables locales
        String nombre = textField1.getText();
        String apellido = textField2.getText();
        String email = textField3.getText();
        String dni = textField5.getText();
        String password = String.valueOf(passwordField1.getPassword());
        String palabraRecuperacion = textField6.getText();
        String obraSocial = textField7.getText();
        //Validamos que todos los campos esten completos y devolvemos true si estan completos y flase con una exception si faltan campos por completar.
        if (validacionCampos(nombre, apellido, email, dni, password, palabraRecuperacion, obraSocial)) {
            //Una vez completos todos los campos, generamos un objeto usuario con los datos del formulario.
            Usuario usuario = new Usuario(nombre, apellido, email, dni, obraSocial, password, palabraRecuperacion, true);
            if (validacionUsuarioEnBD(usuario)) {
                usuarioSQL.registrarUsuario(usuario);
                dispose();
            }
        }else {
            JOptionPane.showMessageDialog(this,
                    "Error al registrar nuevo usuario",
                    "Intentalo otra vez",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    //Validacion campos del Fromulario RegistroForm
    private boolean validacionCampos(String nombre,String apellido,String email,String fechaNacimiento,String password,String palabraRecuperacion,String obraSocial){
        boolean rta=true;
        if(nombre.isEmpty()||apellido.isEmpty()||email.isEmpty()||fechaNacimiento.isEmpty()||password.isEmpty()||palabraRecuperacion.isEmpty()||obraSocial.isEmpty() ){
            JOptionPane.showMessageDialog(this,
                    "Por favor completar, todos los campos",
                    "Intentelo otra vez",
                    JOptionPane.ERROR_MESSAGE);
            rta=false;
        }
        return rta;
    }
    //Validacion Usuario a ingresar, no se encuentre registrado previamente
    private boolean validacionUsuarioEnBD(Usuario usuarioIngreso){
        boolean rta=true;
        UsuarioSQL usuarioBuscar=new UsuarioSQL();
        Usuario comparar=usuarioBuscar.buscarUsuario(usuarioIngreso.getPaciente().getDni());
        if (comparar!=null && comparar.getPaciente().getDni().equalsIgnoreCase(usuarioIngreso.getPaciente().getDni())){
            JOptionPane.showMessageDialog(this,
                    "El usuario ya fue registrado",
                    "Intentelo otra vez",
                    JOptionPane.ERROR_MESSAGE);
            rta=false;
        }
        return rta;
    }
}
