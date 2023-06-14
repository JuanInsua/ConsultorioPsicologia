package Logica.CRUD;

import Exeption.CampoVacioExeption;
import Interfaz.I_ValidacionCampo;
import Modelo.Consultorio;
import Modelo.Estado;
import Modelo.Turno;
import Persistencia.TurnoSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**

 Esta clase representa un diálogo CRUD (Create, Read, Update, Delete) para gestionar objetos Turno.

 Extiende JDialog e implementa I_ValidacionCampo.
 */
public class CrudTurno extends JDialog implements I_ValidacionCampo {
    private JButton buscarButton;
    private JTextField textField6;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField7;
    private JButton VOLVERButton;
    private JButton listarTurnosButton;
    private JTable table1;
    private JButton modificarButton;
    private JButton limpiarButton;
    private JPanel crudTurno;
    private TurnoSQL turnoSQL = new TurnoSQL();
    private Consultorio consultorio = new Consultorio();

    /**
     Crea una nueva instancia de CrudTurno.

     @param parent El Frame padre del diálogo.
     */
    public CrudTurno(Frame parent) {
        super(parent);
        setTitle("Administración");
        setContentPane(crudTurno);
        setMinimumSize(new Dimension(1420, 820));
        setModal(true);
        setLocationRelativeTo(parent);

        listarTurnosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(listarEnTabla());
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBuscarPorDni();
            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnoSQL.modificar(setTurnoModificar());
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        VOLVERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    /**

     Obtiene un TableModel que contiene la lista de objetos Turno para mostrar en una tabla.
     @return El TableModel con la lista de objetos Turno.
     */
    public TableModel listarEnTabla() {
        ArrayList<Turno> turnos = turnoSQL.listar();
        DefaultTableModel model = new DefaultTableModel(0, 0);
        String[] columnName = { "Fecha", "Horario", "Motivo", "Dni", "Estado" };
        model.setColumnIdentifiers(columnName);
        model.addRow(columnName);
        Object[] objects = new Object[5];
        for (int i = 0; i < turnos.size(); i++) {
            objects[0] = turnos.get(i).getFechaConsulta();
            objects[1] = turnos.get(i).getHorarioConsulta();
            objects[2] = turnos.get(i).getMotivoConsulta();
            objects[3] = turnos.get(i).getDniUsuario();
            objects[4] = turnos.get(i).getEstado().name();
            model.addRow(objects);
        }
        return model;
    }
    /**

     Limpia los campos de texto en el diálogo.
     */
    private void limpiarCampos() {
        textField1.setText("");
        textField2.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
    }
    /**

     Crea un objeto Turno con los datos de los campos de texto para modificarlo.
     @return El objeto Turno a modificar.
     */
    private Turno setTurnoModificar() {
        Turno turnoBuscado = null;
        try {
            if (validacionCampo()) {
                turnoBuscado = new Turno(textField6.getText(), textField5.getText(), textField3.getText(),
                        textField4.getText());
                if (textField7.getText().equalsIgnoreCase("activado")) {
                    turnoBuscado.setEstado(Estado.ACTIVADO);
                } else if (textField7.getText().equalsIgnoreCase("cancelado")) {
                    turnoBuscado.setEstado(Estado.CANCELADO);
                } else if (textField7.getText().equalsIgnoreCase("atendido")) {
                    turnoBuscado.setEstado(Estado.ATENDIDO);
                }
            }
        } catch (CampoVacioExeption ce) {
            JOptionPane.showMessageDialog(this, ce.getMessage(), "Intente otra vez", JOptionPane.ERROR_MESSAGE);
        }
        return turnoBuscado;
    }
    /**

     Valida los campos de texto para asegurarse de que no estén vacíos.
     @return True si todos los campos de texto no están vacíos, false en caso contrario.
     @throws CampoVacioExeption Si alguno de los campos de texto está vacío.
     */
    @Override
    public boolean validacionCampo() throws CampoVacioExeption {
        if (!textField1.getText().isEmpty() && !textField2.getText().isEmpty() && !textField3.getText().isEmpty()
                && !textField4.getText().isEmpty() && !textField5.getText().isEmpty() && !textField6.getText().isEmpty()
                && !textField7.getText().isEmpty()) {
            return true;
        } else {
            throw new CampoVacioExeption();
        }
    }
    /**

     Obtiene un objeto Turno basado en los valores de DNI ingresados y completa los campos de texto con sus datos.
     */
    public void getBuscarPorDni() {
        try {
            if (!textField1.getText().isEmpty() && !textField2.getText().isEmpty()) {
                Turno turnoBuscado = turnoSQL.buscarTurno(textField1.getText(), textField2.getText());
                textField3.setText(turnoBuscado.getFechaConsulta());
                textField4.setText(turnoBuscado.getHorarioConsulta());
                textField5.setText(turnoBuscado.getMotivoConsulta());
                textField6.setText(turnoBuscado.getDniUsuario());
                textField7.setText(turnoBuscado.getEstado().name());
            } else {
                JOptionPane.showMessageDialog(this, "Campos para búsqueda vacíos", "Intente otra vez",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (RuntimeException r) {
            JOptionPane.showMessageDialog(this, "Error en la búsqueda", "Intente otra vez",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
