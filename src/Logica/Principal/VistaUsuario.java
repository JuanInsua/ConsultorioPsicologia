package Logica.Principal;

import Modelo.Consultorio;
import Modelo.Turno;
import Modelo.Usuario;
import Persistencia.TurnoSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 * Represents a user interface dialog for the user view.
 */
public class VistaUsuario extends JDialog {
    private JPanel vistaUsuario;
    private JLabel nombreUsuario;
    private JButton SALIRButton;
    private JPanel Turnos;
    private JButton misTurnosButton;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton agregarTurnoButton;
    private JTextField textField1;
    private JLabel emailLabel;
    private String fecha;
    private String horario;
    TurnoSQL turnoSQL = new TurnoSQL();
    Consultorio consultorio = new Consultorio();
    /**
     * Constructs a new VistaUsuario dialog.
     *
     * @param parent             the parent frame
     * @param usuario the active user's name &  the user's identification number
     */
    public VistaUsuario(Frame parent, Usuario usuario) {
        super(parent);
        setTitle("Usuario");
        setContentPane(vistaUsuario);
        setMinimumSize(new Dimension(980, 620));
        setModal(true);
        setLocationRelativeTo(parent);
        nombreUsuario.setText("Bienvenido " + usuario.getPaciente().getNombre().toUpperCase(Locale.ROOT) + "!");
        emailLabel.setText("Email: "+ usuario.getPaciente().getEmail());
        resetbuttonVisibleHorario(false);

        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFechaInput(0);
                resetbuttonHorario();
                resetbuttonDia();
                resetbuttonVisibleHorario(true);
                setHorario("");
                turnosDisponibles(0);
                button1.setBackground(Color.green);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFechaInput(1);
                resetbuttonHorario();
                resetbuttonDia();
                resetbuttonVisibleHorario(true);
                setHorario("");
                turnosDisponibles(1);
                button2.setBackground(Color.green);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFechaInput(2);
                resetbuttonHorario();
                resetbuttonDia();
                resetbuttonVisibleHorario(true);
                setHorario("");
                turnosDisponibles(2);
                button3.setBackground(Color.green);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFechaInput(3);
                resetbuttonHorario();
                resetbuttonDia();
                resetbuttonVisibleHorario(true);
                setHorario("");
                turnosDisponibles(3);
                button4.setBackground(Color.green);
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFechaInput(4);
                resetbuttonHorario();
                resetbuttonDia();
                resetbuttonVisibleHorario(true);
                setHorario("");
                turnosDisponibles(4);
                button5.setBackground(Color.green);
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHorario("8a9");
                resetForegroundHorario();
                button6.setForeground(Color.green);
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHorario("9a10");
                resetForegroundHorario();
                button7.setForeground(Color.green);
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHorario("10a11");
                resetForegroundHorario();
                button8.setForeground(Color.green);
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHorario("11a12");
                resetForegroundHorario();
                button9.setForeground(Color.green);
            }
        });

        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHorario("12a13");
                resetForegroundHorario();
                button10.setForeground(Color.green);
            }
        });

        agregarTurnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarTurno(usuario.getPaciente().getDni());
            }
        });

        misTurnosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(setModelTabla(usuario.getPaciente().getDni()));
            }
        });
        setVisible(true);
    }
    /**
     * Sets the selected date.
     *
     * @param fecha the selected date
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    private void turnosDisponibles(int indexDia) {
        ArrayList<Turno> turnos = consultorio.buscarDia(indexDia);
        resetForegroundHorario();
        if (turnos.isEmpty()) {
            resetbuttonHorario();
        } else {
            int i = 0;
            while ( i < turnos.size()) {
                setbuttonHorario(turnos.get(i));
                i++;
            }
        }
    }
    /**
     * Resets the foreground color of the time slots.
     */
    public void resetForegroundHorario(){
        button6.setForeground(Color.black);
        button7.setForeground(Color.black);
        button8.setForeground(Color.black);
        button9.setForeground(Color.black);
        button10.setForeground(Color.black);
    }
    /**
     * Resets the background color and enables all date buttons.
     */
    public void resetbuttonHorario(){
        button6.setBackground(new Color(187,182,183));
        button6.setEnabled(true);
        button7.setBackground(new Color(187,182,183));
        button7.setEnabled(true);
        button8.setBackground(new Color(187,182,183));
        button8.setEnabled(true);
        button9.setBackground(new Color(187,182,183));
        button9.setEnabled(true);
        button10.setBackground(new Color(187,182,183));
        button10.setEnabled(true);
    }
    /**
     * Resets the background color of the day buttons.
     */
    public void resetbuttonDia(){
        button1.setBackground(new Color(187,182,183));
        button2.setBackground(new Color(187,182,183));
        button3.setBackground(new Color(187,182,183));
        button4.setBackground(new Color(187,182,183));
        button5.setBackground(new Color(187,182,183));
    }
    public void resetbuttonVisibleHorario(boolean rta){
        button6.setVisible(rta);
        button7.setVisible(rta);
        button8.setVisible(rta);
        button9.setVisible(rta);
        button10.setVisible(rta);
    }
    /**
     * Sets the background color and disables the time slot button for the given Turno.
     *
     * @param turno the Turno object
     */
    public void setbuttonHorario(Turno turno){
        if (turno!=null) {
            int indexHorario = consultorio.horarioTurno(turno.getHorarioConsulta());
            switch (indexHorario) {
                case 0:
                    button6.setBackground(Color.red);
                    button6.setEnabled(false);
                    break;
                case 1:
                    button7.setBackground(Color.red);
                    button7.setEnabled(false);
                    break;
                case 2:
                    button8.setBackground(Color.red);
                    button8.setEnabled(false);
                    break;
                case 3:
                    button9.setBackground(Color.red);
                    button9.setEnabled(false);
                    break;
                case 4:
                    button10.setBackground(Color.red);
                    button10.setEnabled(false);
                    break;
            }
        }
    }
    /**
     * Sets the selected date based on the index of the day button clicked.
     *
     * @param indexDia the index of the day button
     * @return the selected date
     */
    private String setFechaInput(int indexDia) {
        if (indexDia == 0) {
            setFecha("lunes");
        }
        if (indexDia == 1) {
            setFecha("martes");
        }
        if (indexDia == 2) {
            setFecha("miercoles");
        }
        if (indexDia == 3) {
            setFecha("jueves");
        }
        if (indexDia == 4) {
            setFecha("viernes");
        }
        return fecha;
    }
    /**
     * Sets the selected time slot.
     *
     * @param horario the selected time slot
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }
    /**
     * Generates a new Turno with the provided information and registers it.
     *
     * @param dniUsuario the user's identification number
     */
    private void generarTurno(String dniUsuario) {
        if (!textField1.getText().isEmpty() && !fecha.isEmpty() && !horario.isEmpty()) {
            Turno turnoAgregar = new Turno(dniUsuario, textField1.getText(), fecha, horario, true);
            turnoSQL.registrarTurno(turnoAgregar);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Por favor completar todos los campos para agregar su turno",
                    "Intente otra vez",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Creates and returns a TableModel for displaying the user's turnos.
     *
     * @param dniUsuario the user's identification number
     * @return the TableModel for the turnos
     */
    public TableModel setModelTabla(String dniUsuario){
        List<Turno> turnosUsuario=consultorio.listarTurnosUsuario(dniUsuario);
        DefaultTableModel model=new DefaultTableModel(0,0);
        String[] columnName={"Fecha","Horario","Motivo","Dni","Estado"};
        model.setColumnIdentifiers(columnName);
        model.addRow(columnName);
        Object[] objects=new Object[8];
        for (int i=0;i<turnosUsuario.size();i++){
            objects[0]=turnosUsuario.get(i).getFechaConsulta();
            objects[1]=turnosUsuario.get(i).getHorarioConsulta();
            objects[2]="Privado";
            objects[3]=turnosUsuario.get(i).getDniUsuario();
            if (turnosUsuario.get(i).isEstado()){
                objects[4]="activo";
            }else {
                objects[4]="cancelado";
            }
            model.addRow(objects);
        }
        return model;
    }
}
