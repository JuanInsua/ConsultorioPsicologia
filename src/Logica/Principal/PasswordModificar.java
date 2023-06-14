package Logica.Principal;

import Exeption.CampoVacioExeption;
import Interfaz.I_ValidacionCampo;
import Modelo.Usuario;
import Persistencia.UsuarioSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**

 Esta clase representa un diálogo para modificar una contraseña.

 Extiende de JDialog e implementa la interfaz I_ValidacionCampo.
 */
public class PasswordModificar extends JDialog implements I_ValidacionCampo {
    private JPanel panel1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JButton verButton;
    private JButton salirButton;
    private JLabel pwlabel;
    private JButton modificarButton;
    private UsuarioSQL usuarioSQL = new UsuarioSQL();

    /**

     Construye un objeto PasswordModificar.

     @param parent El marco padre del diálogo.
     @param usuario El objeto de usuario.
     */
    public PasswordModificar(Frame parent, Usuario usuario) {
        super(parent);
        setTitle("Usuario");
        setContentPane(panel1);
        setMinimumSize(new Dimension(680, 620));
        setModal(true);
        setLocationRelativeTo(parent);

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pwlabel.setText(String.valueOf(passwordField1.getPassword()));
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarPerfil(usuario);
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**

     Modifica el perfil de usuario.
     @param usuario El objeto de usuario a modificar.
     */
    private void modificarPerfil(Usuario usuario) {
        try {
            if (validacionCampo()) {
                validarPw();
                usuario.setPassword(String.valueOf(passwordField1.getPassword()));
                usuario.setPalabraRecuperacion(textField2.getText());
                usuarioSQL.modificar(usuario);
            }
        } catch (CampoVacioExeption ce) {
            JOptionPane.showMessageDialog(this,
                    ce.getMessage(),
                    "Intentar otra vez",
                    JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException re) {
            JOptionPane.showMessageDialog(this,
                    re.getMessage(),
                    "Intentar otra vez",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**

     Valida los campos del formulario.
     @return True si los campos del formulario son válidos, false en caso contrario.
     @throws CampoVacioExeption si alguno de los campos está vacío.
     */
    @Override
    public boolean validacionCampo() throws CampoVacioExeption {
        if (!textField2.getText().isEmpty() && !passwordField1.getText().isEmpty()) {
            return true;
        } else {
            throw new CampoVacioExeption();
        }
    }

    /**

     Valida el campo de la contraseña.
     @throws RuntimeException si la contraseña excede el máximo permitido de caracteres.
     */
    private void validarPw() throws RuntimeException {
        if (String.valueOf(passwordField1.getPassword()).length() > 15) {
            throw new RuntimeException("La contraseña excede el número máximo de caracteres permitidos, el máximo es 15.");
        }
    }
}
