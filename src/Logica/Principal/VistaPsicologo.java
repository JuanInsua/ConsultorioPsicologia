package Logica.Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPsicologo extends JDialog{
    private JLabel nombreUsuario;
    private JButton SALIRButton;
    private JPanel vistaPsicologo;
    private JButton atenderTurnoButton;
    private JButton historialPacienteButton;

    public VistaPsicologo(Frame parent) {
            super(parent);
            setTitle("Psicologo");
            setContentPane(vistaPsicologo);
            setMinimumSize(new Dimension(780, 920));
            setModal(true);
            setLocationRelativeTo(parent);

            SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
            });
        setVisible(true);
    }
}
