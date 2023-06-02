package Logica.Principal;

import Persistencia.UsuarioSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**

 The RecuperarCuenta class represents a dialog window for recovering an account in the system.

 It extends the JDialog class.
 */
public class RecuperarCuenta extends JDialog {
    private JPanel recuperarCuenta;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton SALIRButton;
    private JButton recuperarButton;
    private JTextField textField2;

    /**

     Creates a new instance of RecuperarCuenta.

     @param parent The parent JFrame of the dialog window.
     */
    public RecuperarCuenta(JFrame parent) {
        super(parent);
        setTitle("Log In to the System");
        setContentPane(recuperarCuenta);
        setMinimumSize(new Dimension(980, 520));
        setModal(true);
        setLocationRelativeTo(parent);

        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        recuperarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarRetornoConValidacion();
            }
        });
        setVisible(true);
    }

    /**

     Private method used to search and return the password with validation.
     If the text fields are not empty, a search is performed in the database and the recovered password is displayed.
     If the fields are empty, an error message is shown.
     */
    private void buscarRetornoConValidacion() {
        if (!textField1.getText().isEmpty() && !textField2.getText().isEmpty()) {
            UsuarioSQL usuarioSQL = new UsuarioSQL();
            String passwordRecuperada = usuarioSQL.buscarRetornarPw(textField1.getText(), textField2.getText());
            JOptionPane.showMessageDialog(this, "" + passwordRecuperada);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all the fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
