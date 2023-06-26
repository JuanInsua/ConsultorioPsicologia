package Logica.Principal;

import Exeption.CampoVacioExeption;
import Exeption.UsuarioBuscadoException;
import Interfaz.I_ValidacionCampo;
import Modelo.Consultorio;
import Modelo.Usuario;
import Persistencia.UsuarioSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase IngresoSistema representa una ventana de diálogo para el inicio de sesión de usuario. Extiende JDialog y
 * proporciona campos para ingresar el nombre de usuario y la contraseña, así como botones para iniciar sesión,
 * regresar a la ventana anterior y recuperar una contraseña olvidada.
 */
public class IngresoSistema extends JDialog implements I_ValidacionCampo {
    private JPanel ingresoSistema;
    private JTextField textField1;
    private JTextField textField2;
    private JButton volverButton;
    private JButton ingresarButton;
    private JPasswordField passwordField1;
    private JButton olvideMiContraseniaButton;
    private JLabel verpw;
    private JButton verButton;
    private Consultorio consultorio=new Consultorio();

    /**
     * Construye una nueva ventana de diálogo IngresoSistema.
     *
     * @param parent el JFrame padre
     */
    public IngresoSistema(JFrame parent) {
        super(parent);
        setTitle("Ingreso Usuario");
        setContentPane(ingresoSistema);
        setMinimumSize(new Dimension(680, 620));
        setModal(true);
        setLocationRelativeTo(parent);

        volverButton.addActionListener(e -> dispose());
        ingresarButton.addActionListener(e -> logearUsuario());
        olvideMiContraseniaButton.addActionListener(e -> {
            RecuperarCuenta recuperarCuenta = new RecuperarCuenta(parent);
        });
        verButton.addActionListener(e -> verpw.setText(passwordField1.getText()));
        setVisible(true);
    }

    /**
     * Inicia sesión del usuario llamando al método logearUsuario si los campos de entrada son válidos.
     */
    private void logearUsuario() {
        try {
            if (validacionCampo()) {
                validarIngresoUsuario(textField1.getText(), passwordField1.getText());
            }
        } catch (CampoVacioExeption ce) {
            JOptionPane.showMessageDialog(this,
                    ce.getMessage(),
                    "Intentar otra vez",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Valida los campos de entrada para el inicio de sesión del usuario.
     *
     * @return true si los campos son válidos, false en caso contrario
     */
    @Override
    public boolean validacionCampo() throws CampoVacioExeption {
        if (!textField1.getText().isEmpty() && !passwordField1.getText().isEmpty()) {
            return true;
        } else throw new CampoVacioExeption();
    }

    /**
     * Valida el inicio de sesión del usuario verificando el correo electrónico y la contraseña.
     * Si el inicio de sesión es exitoso, abre una nueva ventana según el rol del usuario.
     *
     * @param email    el correo electrónico del usuario
     * @param password la contraseña del usuario
     */
    public void validarIngresoUsuario(String email, String password) {

        if (email.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
            dispose();
            VistaAdmin vistaAdmin = new VistaAdmin(null);
            vistaAdmin.setVisible(true);
        } else if (email.equalsIgnoreCase("psico") && password.equalsIgnoreCase("psico")) {
            dispose();
            VistaPsicologo vistaPsicologo = new VistaPsicologo(null);
            vistaPsicologo.setVisible(true);
        } else {
            try {
                UsuarioSQL usuarioSQLIngreso = new UsuarioSQL();
                Usuario usuarioBusqueda = consultorio.buscarUsuarioPasswordEmail(email, password);
                if (usuarioBusqueda.isEstado()) {
                    dispose();
                    VistaUsuario vistaUsuario = new VistaUsuario(null, usuarioBusqueda);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "El usuario está de baja",
                            "Intentar otra vez",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (UsuarioBuscadoException ue) {
                JOptionPane.showMessageDialog(this,
                        ue.getMessage(),
                        "Intentar otra vez",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
