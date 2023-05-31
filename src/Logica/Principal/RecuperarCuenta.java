package Logica.Principal;

import Persistencia.UsuarioSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecuperarCuenta extends JDialog{
    private JPanel recuperarCuenta;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton SALIRButton;
    private JButton recuperarButton;
    private JTextField textField2;

    public RecuperarCuenta (JFrame parent){
        super(parent);
        setTitle("Ingresar al Sistema");
        setContentPane(recuperarCuenta);
        setMinimumSize(new Dimension(980, 520));
        setModal(true);
        setLocationRelativeTo(parent);

        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        recuperarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarRetornoConValidacion();
            }
        });
        setVisible(true);
    }
    private void buscarRetornoConValidacion(){
        if (!textField1.getText().isEmpty() && !textField2.getText().isEmpty()){
            UsuarioSQL usuarioSQL=new UsuarioSQL();
            String passwordRecuperada=usuarioSQL.buscarRetornarPw(textField1.getText(),textField2.getText());
            JOptionPane.showMessageDialog(this,"PASSWORD: "+passwordRecuperada);
        }else {
            JOptionPane.showMessageDialog(this,
                    "Por favor completar los campos",
                    "Intentalo otra vez",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
