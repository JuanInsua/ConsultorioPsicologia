package Logica.Principal;


import Logica.CRUD.CrudUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaAdmin extends JDialog {
    private JPanel vistaAdmin;
    private JButton SALIRButton;
    private JButton USUARIOSButton;
    private JButton TURNOSButton;
    private JButton SESIONESButton;
    public VistaAdmin(Frame parent) {
        super(parent);
        setTitle("Administracion");
        setContentPane(vistaAdmin);
        setMinimumSize(new Dimension(920, 820));
        setModal(true);
        setLocationRelativeTo(parent);
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        USUARIOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrudUsuario crudUsuario=new CrudUsuario(null);
            }
        });
        setVisible(true);
    }
}
