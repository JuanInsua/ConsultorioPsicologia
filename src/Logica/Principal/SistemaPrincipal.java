package Logica.Principal;

import JSON.ControladoraJson;
import Logica.Principal.IngresoSistema;
import Logica.Principal.RegistroForm;
import Modelo.Consultorio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SistemaPrincipal extends JDialog {
    private JButton INGRESARButton;
    private JButton REGISTRARButton;
    private JButton SALIRButton;
    private JPanel sistemaPrincipal;
    private Consultorio consultorio;


    /**
     * Construye un nuevo objeto SistemaPrincipal con el JFrame padre especificado.
     * @param parent El JFrame padre para este diálogo.
     */
    public SistemaPrincipal(JFrame parent) {
        super(parent);
        setTitle("Sistema Principal");
        setContentPane(sistemaPrincipal);
        setSize(new Dimension(780, 920));
        setModal(true);
        setLocationRelativeTo(parent);
        consultorio=new Consultorio();

        REGISTRARButton.addActionListener(e -> desplegarMenuRegistro());

        INGRESARButton.addActionListener(e -> desplegarMenuIngreso());

        SALIRButton.addActionListener(e -> dispose());
        setVisible(true);
        ControladoraJson.toJson(consultorio);
    }

    /**
     * Despliega el menú de ingreso creando un nuevo objeto IngresoSistema.
     */
    private void desplegarMenuIngreso() {
        IngresoSistema ingresoSistema = new IngresoSistema(null);
    }

    /**
     * Despliega el menú de registro creando un nuevo objeto RegistroForm.
     */
    private void desplegarMenuRegistro() {
        RegistroForm registroForm = new RegistroForm(null);
    }
}

