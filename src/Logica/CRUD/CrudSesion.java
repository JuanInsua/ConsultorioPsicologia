package Logica.CRUD;

import Interfaz.I_ListarEnTabla;
import Interfaz.I_Seleccionar;
import Modelo.Consultorio;
import Modelo.Sesion;
import Modelo.Usuario;
import Persistencia.SesionSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Clase que implementa la funcionalidad de CRUD para las sesiones.
 * Extiende la clase JDialog e implementa la interfaz I_ListarEnTabla.
 */
public class CrudSesion extends JDialog implements I_ListarEnTabla, I_Seleccionar {
    private JTable table1;
    private JButton VOLVERButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel crudSesion;
    private JTextArea textArea1;
    private SesionSQL sesionSQL = new SesionSQL();
    private Consultorio consultorio = new Consultorio();

    /**
     * Constructor de la clase CrudSesion.
     *
     * @param parent El frame padre.
     */
    public CrudSesion(Frame parent) {
        super(parent);
        setTitle("Gestión de sesiones");
        setContentPane(crudSesion);
        setSize(new Dimension(1420, 820));
        setModal(true);
        setLocationRelativeTo(parent);
        textField1.setText(consultorio.cantidadSesiones());
        textField2.setText(consultorio.cantidadTurnosActivos());
        textField3.setText(consultorio.cantidadTurnosCancelados());
        table1.setModel(listarEnTabla());

        VOLVERButton.addActionListener(e -> dispose());
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    seleccionar(e);
                }catch (RuntimeException re){
                    re.getMessage();
                }
            }
        });
        setVisible(true);
    }
    @Override
    public void seleccionar(MouseEvent e) throws RuntimeException {
        int selection=table1.rowAtPoint(e.getPoint());
        if (selection!=0) {
            textArea1.setText(String.valueOf(table1.getValueAt(selection, 4)));
        }
        setVisible(true);
    }

    /**
     * Implementación del método listarEnTabla de la interfaz I_ListarEnTabla.
     * Lista las sesiones en una tabla.
     *
     * @return El modelo de tabla con los datos de las sesiones.
     */
    @Override
    public TableModel listarEnTabla() {
        HashSet<Sesion> sesiones = consultorio.listarSesiones();
        DefaultTableModel model = new DefaultTableModel(0, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] columnName = {"Fecha", "Horario", "DNI", "Motivo", "Resumen"};
        model.setColumnIdentifiers(columnName);
        model.addRow(columnName);
        Object[] objects = new Object[5];
        Iterator<Sesion> it = sesiones.iterator();
        while (it.hasNext()) {
            Sesion sesion = it.next();
            objects[0] = sesion.getTurno().getFechaConsulta();
            objects[1] = sesion.getTurno().getHorarioConsulta();
            objects[2] = sesion.getTurno().getDniUsuario();
            objects[3] = sesion.getTurno().getMotivoConsulta();
            objects[4] = sesion.getResumenSesion();
            model.addRow(objects);
        }
        return model;
    }
}

