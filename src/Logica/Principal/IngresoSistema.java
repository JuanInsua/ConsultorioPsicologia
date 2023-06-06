package Logica.Principal;

import Modelo.Usuario;
import Persistencia.UsuarioSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**

 The IngresoSistema class represents a dialog window for user login.

 It extends JDialog and provides fields for entering username and password,

 as well as buttons for logging in, returning to the previous window,

 and recovering a forgotten password.
 */
public class IngresoSistema extends JDialog {
    private JPanel ingresoSistema;
    private JTextField textField1;
    private JTextField textField2;
    private JButton volverButton;
    private JButton ingresarButton;
    private JPasswordField passwordField1;
    private JButton olvideMiContraseniaButton;

    /**

     Constructs a new IngresoSistema dialog window.

     @param parent the parent JFrame
     */
    public IngresoSistema(JFrame parent) {
        super(parent);
        setTitle("Log In to System");
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
                RecuperarCuenta recuperarCuenta = new RecuperarCuenta(parent);
            }
        });
        setVisible(true);
    }

    /**

     Logs in the user by calling the logearUsuario method if the input fields are valid.
     */
    private void logearUsuario() {
        if (validacionCamposIngreso()) {
            validarIngresoUsuario(textField1.getText(), passwordField1.getText());
        }
    }
    /**

     Validates the input fields for user login.
     @return true if the fields are valid, false otherwise
     */
    private boolean validacionCamposIngreso() {
        boolean rta = true;
        if (textField1.getText().isEmpty() || passwordField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all the fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            rta = false;
        }
        return rta;
    }
    /**

     Validates user login by checking the email and password.
     If the login is successful, it opens a new window based on the user's role.
     @param email the user's email
     @param password the user's password
     */
    public void validarIngresoUsuario(String email, String password) {
        if (email.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
            dispose();
            VistaAdmin vistaAdmin = new VistaAdmin(null);
        } else if (email.equalsIgnoreCase("psico") && password.equalsIgnoreCase("psico")) {
            dispose();
            VistaPsicologo vistaPsicologo= new VistaPsicologo(null);
        }else{
            UsuarioSQL usuarioSQLIngreso = new UsuarioSQL();
            Usuario usuarioBusqueda = usuarioSQLIngreso.buscarUsuarioPasswordEmail(email, password);
            if (usuarioBusqueda == null) {
                JOptionPane.showMessageDialog(this,
                        "The user is not registered",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
            } else if (usuarioBusqueda.isEstado()) {
                dispose();
                VistaUsuario vistaUsuario = new VistaUsuario(null, usuarioBusqueda.getPaciente().getNombre(),usuarioBusqueda.getPaciente().getDni());
            }
                else {
                    JOptionPane.showMessageDialog(this,
                            "The user has been deactivated",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
