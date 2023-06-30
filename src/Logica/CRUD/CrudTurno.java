package Logica.CRUD;

import Exeption.CampoVacioExeption;
import Interfaz.I_LimpiarCampo;
import Interfaz.I_ListarEnTabla;
import Interfaz.I_Seleccionar;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**

 Esta clase representa un diálogo CRUD (Create, Read, Update, Delete) para gestionar objetos Turno.

 Extiende JDialog e implementa I_ValidacionCampo.
 */
public class CrudTurno extends JDialog implements I_ValidacionCampo, I_LimpiarCampo, I_ListarEnTabla, I_Seleccionar {
    private JTextField textField6;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton VOLVERButton;
    private JTable table1;
    private JButton modificarButton;
    private JButton limpiarButton;
    private JPanel crudTurno;
    private JComboBox comboBox1;
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
        setSize(new Dimension(1420, 820));
        setModal(true);
        setLocationRelativeTo(parent);
        table1.setModel(listarEnTabla());

        modificarButton.addActionListener(e ->{ turnoSQL.modificar(setTurnoModificar());table1.setModel(listarEnTabla());});
        limpiarButton.addActionListener(e -> limpiarCampos());
        VOLVERButton.addActionListener(e -> dispose());
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    seleccionar(e);
                }catch (RuntimeException re){
                    re.getCause();
                }
            }
        });
        setVisible(true);
    }

    @Override
    public void seleccionar(MouseEvent e) throws RuntimeException {
        int selection=table1.rowAtPoint(e.getPoint());
        if (selection!=0) {
            textField3.setText(String.valueOf(table1.getValueAt(selection, 0)));
            textField4.setText(String.valueOf(table1.getValueAt(selection, 1)));
            textField5.setText(String.valueOf(table1.getValueAt(selection, 2)));
            textField6.setText(String.valueOf(table1.getValueAt(selection, 3)));
            if(String.valueOf(table1.getValueAt(selection,4)).toString().equalsIgnoreCase("activado")){
                comboBox1.setSelectedIndex(0);
            }else{
                comboBox1.setSelectedIndex(1);
            }
        }
    }

    /**

     Obtiene un TableModel que contiene la lista de objetos Turno para mostrar en una tabla.
     @return El TableModel con la lista de objetos Turno.
     */
    @Override
    public TableModel listarEnTabla() {
            ArrayList<Turno> turnos = turnoSQL.listar();
            DefaultTableModel model = new DefaultTableModel(0, 0){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String[] columnName = { "Fecha", "Horario", "Motivo", "Dni", "Estado" };
            model.setColumnIdentifiers(columnName);
            model.addRow(columnName);
            Object[] objects = new Object[5];
            for (int i = 0; i < turnos.size(); i++) {
                if (!turnos.get(i).getEstado().name().equalsIgnoreCase("atendido")){
                    objects[0] = turnos.get(i).getFechaConsulta();
                    objects[1] = turnos.get(i).getHorarioConsulta();
                    objects[2] = turnos.get(i).getMotivoConsulta();
                    objects[3] = turnos.get(i).getDniUsuario();
                    objects[4] = turnos.get(i).getEstado().name();
                    model.addRow(objects);
                }
            }
            return model;
    }

    /**

     Limpia los campos de texto en el diálogo.
     */
    @Override
    public void limpiarCampos() {
        try {
            if (validacionCampo()){
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
            }
        }catch (CampoVacioExeption ce){
            JOptionPane.showMessageDialog(this, ce.getMessage(), "Intente otra vez",
                    JOptionPane.ERROR_MESSAGE);
        }
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
                if (comboBox1.getSelectedIndex()==0) {
                    turnoBuscado.setEstado(Estado.ACTIVADO);
                } else if (comboBox1.getSelectedIndex()==1) {
                    turnoBuscado.setEstado(Estado.CANCELADO);
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
        if (!textField3.getText().isEmpty()
                && !textField4.getText().isEmpty() && !textField5.getText().isEmpty() && !textField6.getText().isEmpty()) {
            return true;
        } else {
            throw new CampoVacioExeption();
        }
    }
}
