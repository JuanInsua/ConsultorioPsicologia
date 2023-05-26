package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngresoSistema extends JDialog {
    private JPanel ingresoSistema;
    private JTextField textField1;
    private JTextField textField2;
    private JButton volverButton;
    private JButton ingresarButton;
    public IngresoSistema (JFrame parent) {
        super(parent);
        setTitle("Ingresar al Sistema");
        setContentPane(ingresoSistema);
        setMinimumSize(new Dimension(680, 620));
        setModal(true);
        setLocationRelativeTo(parent);

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setVisible(true);
    }
}
