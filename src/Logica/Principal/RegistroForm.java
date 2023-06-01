package Logica.Principal;

import Modelo.Usuario;
import Persistencia.UsuarioSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**

 Class that represents a user registration window.

 Extends the JDialog class to create a modal dialog window.
 */
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
    UsuarioSQL usuarioSQL = new UsuarioSQL();

    /**

     Constructor of the RegistroForm class.

     @param parent Parent JFrame that calls this dialog window.
     */
    public RegistroForm(JFrame parent) {
        super(parent);
        setTitle("Create new user");
        setContentPane(registroForm);
        setMinimumSize(new Dimension(680, 620));
        setModal(true);
        setLocationRelativeTo(parent);

// Exit Button
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

// Register Button
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registroConValidacionCampos();
            }
        });

        setVisible(true);
    }

    /**

     Method that performs user registration with field validation.
     */
    private void registroConValidacionCampos() {
        String nombre = textField1.getText();
        String apellido = textField2.getText();
        String email = textField3.getText();
        String dni = textField5.getText();
        String password = String.valueOf(passwordField1.getPassword());
        String palabraRecuperacion = textField6.getText();
        String obraSocial = textField7.getText();

        if (validacionCampos(nombre, apellido, email, dni, password, palabraRecuperacion, obraSocial)) {
            Usuario usuario = new Usuario(nombre, apellido, email, dni, obraSocial, password, palabraRecuperacion, true);
            if (validacionUsuarioEnBD(usuario)) {
                usuarioSQL.registrarUsuario(usuario);
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error registering new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**

     Method that validates the registration fields.
     @param nombre User's name.
     @param apellido User's last name.
     @param email User's email.
     @param fechaNacimiento User's date of birth.
     @param password User's password.
     @param palabraRecuperacion User's recovery word.
     @param obraSocial User's health insurance.
     @return true if all fields are filled, false otherwise.
     */
    private boolean validacionCampos(String nombre, String apellido, String email, String fechaNacimiento,
                                     String password, String palabraRecuperacion, String obraSocial) {
        boolean rta = true;
        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || fechaNacimiento.isEmpty() ||
                password.isEmpty() || palabraRecuperacion.isEmpty() || obraSocial.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            rta = false;
        }
        return rta;
    }
    /**

     Method that validates if a user already exists in the database.
     @param usuarioIngreso User to validate.
     @return true if the user does not exist in the database, false otherwise.
     */
    private boolean validacionUsuarioEnBD(Usuario usuarioIngreso) {
        boolean rta = true;
        UsuarioSQL usuarioBuscar = new UsuarioSQL();
        Usuario comparar = usuarioBuscar.buscarUsuario(usuarioIngreso.getPaciente().getDni());
        if (comparar != null && comparar.getPaciente().getDni().equalsIgnoreCase(usuarioIngreso.getPaciente().getDni())) {
            JOptionPane.showMessageDialog(this,
                    "The user has already been registered",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            rta = false;
        }
        return rta;
    }
}
