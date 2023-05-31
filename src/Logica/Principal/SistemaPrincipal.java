package Logica.Principal;

import Logica.Principal.IngresoSistema;
import Logica.Principal.RegistroForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaPrincipal extends JDialog {
    private JButton INGRESARButton;
    private JButton REGISTRARButton;
    private JButton SALIRButton;
    private JPanel sistemaPrincipal;
    public SistemaPrincipal (JFrame parent){
        super(parent);
        setTitle("Sistema Principal");
        setContentPane(sistemaPrincipal);
        setMinimumSize(new Dimension(780,920));
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

        setVisible(true);
    }
    private void deployLoginMenu() {
        IngresoSistema ingresoSistema=new IngresoSistema(null);
    }
    private void deployRegisterMenu() {
        RegistroForm registroForm=new RegistroForm(null);
    }
}
