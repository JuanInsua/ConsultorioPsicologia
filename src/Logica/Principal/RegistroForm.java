package Logica.Principal;

import Exeption.CampoVacioExeption;
import Exeption.DniExeption;
import Exeption.UsuarioBuscadoException;
import Interfaz.I_ValidacionCampo;
import Modelo.Usuario;
import Persistencia.UsuarioSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**

 Esta clase representa un diálogo de formulario de registro que permite crear un nuevo usuario.

 Se extiende de JDialog e implementa I_ValidacionCampo.
 */
public class RegistroForm extends JDialog implements I_ValidacionCampo {
    private JButton REGISTRARButton;
    private JButton SALIRButton;
    private JPanel registroForm;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton verButton;
    private JLabel pwlabel;
    UsuarioSQL usuarioSQL = new UsuarioSQL();

    /**

     Construye un nuevo diálogo de RegistroForm con el JFrame padre especificado.

     @param parent El JFrame padre del diálogo.
     */
    public RegistroForm(JFrame parent) {
        super(parent);
        setTitle("Crear nuevo usuario");
        setContentPane(registroForm);
        setMinimumSize(new Dimension(680, 620));
        setModal(true);
        setLocationRelativeTo(parent);

        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registroConValidacionCampos();
            }
        });

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pwlabel.setText(passwordField1.getText());
            }
        });
        setVisible(true);
    }

    /**

     Realiza el registro con validación de campos.
     */
    private void registroConValidacionCampos() {
        try {
            if (validacionCampo()) {
                if (validacionDNI()) {
                    validacionPw();
                    Usuario usuario = new Usuario(textField1.getText(), textField2.getText(), textField3.getText(),
                            String.valueOf(passwordField1.getPassword()), textField4.getText(), true);
                    if (validacionUsuarioEnBD(usuario)) {
                        usuarioSQL.registrar(usuario);
                        dispose();
                    }
                }
            }
        } catch (CampoVacioExeption ce) {
            JOptionPane.showMessageDialog(this,
                    ce.getMessage(),
                    "Inténtalo de nuevo",
                    JOptionPane.ERROR_MESSAGE);
        } catch (DniExeption ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Inténtalo de nuevo",
                    JOptionPane.ERROR_MESSAGE);
        } catch (UsuarioBuscadoException ue) {
            JOptionPane.showMessageDialog(this,
                    ue.getMessage(),
                    "Inténtalo de nuevo",
                    JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException re) {
            JOptionPane.showMessageDialog(this,
                    re.getMessage(),
                    "Inténtalo de nuevo",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**

     Valida si todos los campos están completos.
     @return true si todos los campos están completos, false en caso contrario.
     @throws CampoVacioExeption si algún campo está vacío.
     */
    @Override
    public boolean validacionCampo() throws CampoVacioExeption {
        if (!textField1.getText().isEmpty() && !textField2.getText().isEmpty() && !textField3.getText().isEmpty() &&
                !String.valueOf(passwordField1.getPassword()).isEmpty() && validacionDNI()) {
            return true;
        } else {
            throw new CampoVacioExeption();
        }
    }

    /**

     Valida el formato del campo DNI.
     @return true si el formato del DNI es válido, false en caso contrario.
     @throws DniExeption si el formato del DNI es inválido.
     */
    private boolean validacionDNI() throws DniExeption {
        if (textField3.getText().getBytes().length >= 7 && textField3.getText().getBytes().length < 9) {
            return true;
        } else {
            throw new DniExeption("DNI fuera de formato, ingresar 7 u 8 números");
        }
    }

    /**

     Valida la longitud del campo de contraseña.
     @throws RuntimeException si la longitud de la contraseña excede el límite máximo.
     */
    private void validacionPw() throws RuntimeException {
        if (String.valueOf(passwordField1.getPassword()).length() > 15) {
            throw new RuntimeException("Contraseña excedida de caracteres, máximo 15");
        }
    }

    /**

     Valida si el usuario ya existe en la base de datos.
     @param usuarioIngreso El usuario a validar.
     @return true si el usuario no se encuentra en la base de datos, false en caso contrario.
     @throws UsuarioBuscadoException si el usuario se encuentra en la base de datos.
     */
    private boolean validacionUsuarioEnBD(Usuario usuarioIngreso) throws UsuarioBuscadoException {
        UsuarioSQL usuarioBuscar = new UsuarioSQL();
        Usuario compararDni = usuarioBuscar.buscarUsuarioDni(usuarioIngreso.getPaciente().getDni());
        if (compararDni != null && compararDni.getPaciente().getDni().equalsIgnoreCase(usuarioIngreso.getPaciente().getDni())) {
            throw new UsuarioBuscadoException("El DNI ya fue registrado");
        }
        Usuario compararNombreUser = usuarioBuscar.buscarUsuarioNombreUser(usuarioIngreso.getPaciente().getNombre());
        if (compararNombreUser != null && compararNombreUser.getPaciente().getNombre().equalsIgnoreCase(usuarioIngreso.getPaciente().getNombre())) {
            throw new UsuarioBuscadoException("El Nombre de Usuario ya fue registrado");
        }
        Usuario compararEmail = usuarioBuscar.buscarUsuarioEmail(usuarioIngreso.getPaciente().getEmail());
        if (compararEmail != null && compararEmail.getPaciente().getEmail().equalsIgnoreCase(usuarioIngreso.getPaciente().getEmail())) {
            throw new UsuarioBuscadoException("El Email ya fue registrado");
        }
        return true;
    }
}
