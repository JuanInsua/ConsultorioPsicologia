package Logica.CRUD;

import Modelo.Usuario;
import Persistencia.UsuarioSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**

 This class represents a user administration window.

 It extends JDialog, making it a dialog window.
 */
public class CrudUsuario extends JDialog {

    // User interface components
    private JTable table1;
    private JPanel crudUsuario;
    private JButton listarUsuariosButton;
    private JButton VOLVERButton;
    private JButton modificarButton;
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

    UsuarioSQL usuarioSQL = new UsuarioSQL();

    /**

     Constructor of the CrudUsuario class.

     @param parent The parent frame for the dialog window.
     */
    public CrudUsuario(Frame parent) {
        super(parent);
        setTitle("Administration");
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
                usuarioSQL.modificarClientes(setUsuarioModificar(), textField7.getText());
            }
        });
        setVisible(true);
    }

    /**

     Clears the user fields in the user interface.
     */
    private void limpiarCamposUsuario() {
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
    /**

     Creates a User object from the data in the user interface fields.
     @return A User object with the entered data in the interface.
     */
    private Usuario setUsuarioModificar() {
        Usuario usuarioBuscado = null;
        if (validacionCamposModificarUsuarios(textField1.getText(),
                textField2.getText(), textField3.getText(),
                textField4.getText(), textField5.getText(),
                textField6.getText(), textField9.getText(),
                textField8.getText())) {
            Boolean estado;
            if (textField8.getText().equalsIgnoreCase("true")) {
                estado = true;
            } else {
                estado = false;
            }
            usuarioBuscado = new Usuario(textField1.getText(),
                    textField2.getText(), textField5.getText(),
                    textField4.getText(), textField3.getText(),
                    textField9.getText(), textField6.getText(),
                    estado);
        }
        return usuarioBuscado;
    }
    /**

     Performs validation of the necessary fields to modify a user.
     @param nombre The user's name.
     @param apellido The user's last name.
     @param obraSocial The user's health insurance.
     @param dni The user's ID number.
     @param email The user's email.
     @param palabraRec The user's recovery word.
     @param password The user's password.
     @param estado The user's status.
     @return true if all fields are valid, false otherwise.
     */
    private boolean validacionCamposModificarUsuarios(String nombre, String apellido, String obraSocial, String dni, String email, String palabraRec, String password, String estado) {
        boolean rta = true;
        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || obraSocial.isEmpty() || password.isEmpty() || dni.isEmpty() || estado.isEmpty() || palabraRec.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            rta = false;
        }
        return rta;
    }
    /**

     Searches for a user by ID number and displays the data in the user interface fields.
     */
    public void getBuscarPorDni() {
        try {
            Usuario usuarioBuscado = usuarioSQL.buscarUsuario(textField7.getText());
            textField1.setText(usuarioBuscado.getPaciente().getNombre());
            textField2.setText(usuarioBuscado.getPaciente().getApellido());
            textField3.setText(usuarioBuscado.getPaciente().getObraSocial());
            textField4.setText(usuarioBuscado.getPaciente().getDni());
            textField5.setText(usuarioBuscado.getPaciente().getEmail());
            textField6.setText(usuarioBuscado.getPalabraRecuperacion());
            textField9.setText(usuarioBuscado.getPassword());
            String estado;
            if (usuarioBuscado.isEstado()) {
                estado = "true";
            } else {
                estado = "false";
            }
            textField8.setText(estado);
        } catch (RuntimeException r) {
            JOptionPane.showMessageDialog(this, "Error in the search", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }
}
