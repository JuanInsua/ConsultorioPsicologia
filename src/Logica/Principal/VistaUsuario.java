package Logica.Principal;

import Modelo.Consultorio;
import Modelo.Turno;
import Persistencia.TurnoSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

/**
 * The VistaUsuario class represents a user view dialog in the application.
 * It extends JDialog and provides functionality for managing user appointments.
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
    private String fecha;
    private String horario;
    TurnoSQL turnoSQL = new TurnoSQL();
    Consultorio consultorio = new Consultorio();

    /**
     * Constructs a new VistaUsuario dialog.
     *
     * @param parent            the parent frame
     * @param nombreUsuarioActivo the active user's name
     * @param dniUsuario        the user's ID
     */
    public VistaUsuario(Frame parent, String nombreUsuarioActivo, String dniUsuario) {
        super(parent);
        setTitle("Usuario");
        setContentPane(vistaUsuario);
        setMinimumSize(new Dimension(980, 920));
        setModal(true);
        setLocationRelativeTo(parent);
        nombreUsuario.setText("Bienvenido " + nombreUsuarioActivo.toUpperCase(Locale.ROOT) + "!");

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
                turnosDisponibles(0);
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFechaInput(0);
                turnosDisponibles(0);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFechaInput(1);
                turnosDisponibles(1);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFechaInput(2);
                turnosDisponibles(2);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFechaInput(3);
                turnosDisponibles(3);
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFechaInput(4);
                turnosDisponibles(4);
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                horario="8a9";
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                horario="9a10";
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                horario="10a11";
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                horario="11a12";
            }
        });
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                horario="12a13";
            }
        });

        // Add action listeners to the remaining buttons

        agregarTurnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarTurno(dniUsuario);
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

    /**
     * Displays the available appointments for a given day.
     *
     * @param indexDia the index of the selected day
     */
    private void turnosDisponibles(int indexDia) {
        ArrayList<Turno> turnos = consultorio.buscarDia(indexDia);
        if (turnos.isEmpty()) {
            button6.setVisible(true);
            button7.setVisible(true);
            button8.setVisible(true);
            button9.setVisible(true);
            button10.setVisible(true);
        } else {
            int i = 0;
            while (!turnos.isEmpty() && i < turnos.size()) {
                if (turnos.get(i) != null) {
                    int indexHorario = consultorio.horarioTurno(turnos.get(i).getHorarioConsulta());
                    switch (indexHorario) {
                        case 0:
                            button6.setVisible(false);
                            break;
                        case 1:
                            button7.setVisible(false);
                            break;
                        case 2:
                            button8.setVisible(false);
                            break;
                        case 3:
                            button9.setVisible(false);
                            break;
                        case 4:
                            button10.setVisible(false);
                            break;
                    }
                }
                i++;
            }
        }
    }

    /**
     * Sets the selected date based on the given index.
     *
     * @param indexDia the index of the selected day
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
     * Generates a new appointment for the user.
     *
     * @param dniUsuario the user's ID
     */
    private void generarTurno(String dniUsuario) {
        if (!textField1.getText().isEmpty() && !fecha.isEmpty() && !horario.isEmpty()) {
            Turno turnoAgregar = new Turno(dniUsuario, textField1.getText(), fecha, horario, true);
            turnoSQL.registrarTurno(turnoAgregar);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
