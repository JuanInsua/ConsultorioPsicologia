package Logica.Principal;

import Exeption.CampoVacioExeption;
import Interfaz.I_ValidacionCampo;
import Modelo.Consultorio;
import Persistencia.UsuarioSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**

 La clase RecuperarCuenta representa una ventana de diálogo para recuperar una cuenta en el sistema.

 Esta clase extiende la clase JDialog.
 */
public class RecuperarCuenta extends JDialog implements I_ValidacionCampo {
    private JPanel recuperarCuenta;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton SALIRButton;
    private JButton recuperarButton;
    private JTextField textField2;
    private Consultorio consultorio=new Consultorio();

    /**
     * Crea una nueva instancia de RecuperarCuenta.
     *
     * @param parent El JFrame padre de la ventana de diálogo.
     */
    public RecuperarCuenta(JFrame parent) {
        super(parent);
        setTitle("Iniciar sesión en el sistema");
        setContentPane(recuperarCuenta);
        setSize(new Dimension(980, 520));
        setModal(true);
        setLocationRelativeTo(parent);

        SALIRButton.addActionListener(e -> dispose());
        recuperarButton.addActionListener(e -> buscarPWRetornoConValidacion());
        setVisible(true);
    }

    /**
     * Método privado utilizado para buscar y devolver la contraseña con validación.
     * Si los campos de texto no están vacíos, se realiza una búsqueda en la base de datos y se muestra la contraseña recuperada.
     * Si los campos están vacíos, se muestra un mensaje de error.
     */
    private void buscarPWRetornoConValidacion() {
        try{
            if (validacionCampo()) {
                UsuarioSQL usuarioSQL = new UsuarioSQL();
                String passwordRecuperada = consultorio.buscarRetornarPw(textField1.getText(), textField2.getText());
                JOptionPane.showMessageDialog(this, passwordRecuperada);
            }
        }catch (CampoVacioExeption ce) {
            JOptionPane.showMessageDialog(this,
                    ce.getMessage(),
                    "Intentar otra vez",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public boolean validacionCampo() throws CampoVacioExeption {
        if (!textField1.getText().isEmpty() && !textField2.getText().isEmpty()) {
            return true;
        }else throw new CampoVacioExeption();
    }
}







