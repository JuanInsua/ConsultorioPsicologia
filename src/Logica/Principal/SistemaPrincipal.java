package Logica.Principal;

import Logica.Principal.IngresoSistema;
import Logica.Principal.RegistroForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The SistemaPrincipal class represents the main system dialog in the application.
 * It extends the JDialog class and provides functionality for user interaction.
 */
public class SistemaPrincipal extends JDialog {
    private JButton INGRESARButton;
    private JButton REGISTRARButton;
    private JButton SALIRButton;
    private JPanel sistemaPrincipal;

    /**
     * Constructs a new SistemaPrincipal object with the specified parent JFrame.
     * @param parent The parent JFrame for this dialog.
     */
    public SistemaPrincipal(JFrame parent) {
        super(parent);
        setTitle("Sistema Principal");
        setContentPane(sistemaPrincipal);
        setMinimumSize(new Dimension(780, 920));
        setModal(true);
        setLocationRelativeTo(parent);

        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deployRegisterMenu();
            }
        });

        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deployLoginMenu();
            }
        });

        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
    /**
     * Deploys the login menu by creating a new IngresoSistema object.
     */
    private void deployLoginMenu() {
        IngresoSistema ingresoSistema = new IngresoSistema(null);
    }
    /**
     * Deploys the register menu by creating a new RegistroForm object.
     */
    private void deployRegisterMenu() {
        RegistroForm registroForm = new RegistroForm(null);
    }
}
