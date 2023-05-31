package Logica.Principal;

import Modelo.Usuario;
import Persistencia.UsuarioSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngresoSistema extends JDialog {
    private JPanel ingresoSistema;
    private JTextField textField1;
    private JTextField textField2;
    private JButton volverButton;
    private JButton ingresarButton;
    private JPasswordField passwordField1;
    private JButton olvideMiContraseniaButton;

    public IngresoSistema (JFrame parent) {
        super(parent);
        setTitle("Ingresar al Sistema");
        setContentPane(ingresoSistema);
        setMinimumSize(new Dimension(680, 620));
        setModal(true);
        setLocationRelativeTo(parent);

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logearUsuario();
            }
        });
        olvideMiContraseniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RecuperarCuenta recuperarCuenta=new RecuperarCuenta(parent);
            }
        });
        setVisible(true);
    }
    private void logearUsuario(){
        if(validacionCamposIngreso()){
            validarIngresoUsuario(textField1.getText(),passwordField1.getText());
        }
    }
    private boolean validacionCamposIngreso(){
        boolean rta=true;
        if(textField1.getText().isEmpty()||passwordField1.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Por favor completar, todos los campos",
                    "Intentelo otra vez",
                    JOptionPane.ERROR_MESSAGE);
            rta=false;
        }
        return rta;
    }
    public void validarIngresoUsuario(String email , String password) {
        if (email.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
            dispose();
            VistaAdmin vistaAdmin=new VistaAdmin(null);
        } else {
            UsuarioSQL usuarioSQLIngreso = new UsuarioSQL();
            Usuario usuarioBusqueda = usuarioSQLIngreso.buscarUsuarioPasswordEmail(email,password);
            if (usuarioBusqueda != null && usuarioBusqueda.isEstado()) {
                dispose();
                VistaUsuario vistaUsuario=new VistaUsuario(null,usuarioBusqueda.getPaciente().getNombre());
            } else {
                JOptionPane.showMessageDialog(this,
                        "El usuario no esta registrado, o esta de baja",
                        "Intentelo otra vez",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
