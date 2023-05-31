package Logica.Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class VistaUsuario extends JDialog {
    private JPanel vistaUsuario;
    private JLabel nombreUsuario;
    private JButton SALIRButton;

    public VistaUsuario(Frame parent,String nombreUsuarioActivo) {
        super(parent);
        setTitle("Usuario");
        setContentPane(vistaUsuario);
        setMinimumSize(new Dimension(780, 920));
        setModal(true);
        setLocationRelativeTo(parent);
        nombreUsuario.setText("Bienvenido "+ nombreUsuarioActivo.toUpperCase(Locale.ROOT)+"!");

        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
