package Logica.CRUD;

import Interfaz.I_ListarEnTabla;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Clase que implementa la funcionalidad de CRUD para las sesiones.
 * Extiende la clase JDialog e implementa la interfaz I_ListarEnTabla.
 */
public class CrudSesion extends JDialog implements I_ListarEnTabla {
    private JTable table1;
    private JButton listarSesionesButton;
    private JButton VOLVERButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel crudSesion;
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
        setMinimumSize(new Dimension(1420, 820));
        setModal(true);
        setLocationRelativeTo(parent);
        textField1.setText(consultorio.cantidadSesiones());
        textField2.setText(consultorio.cantidadTurnosAtendidos());
        textField3.setText(consultorio.cantidadTurnosCancelados());

        VOLVERButton.addActionListener(e -> dispose());
        listarSesionesButton.addActionListener(e -> table1.setModel(listarEnTabla()));

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
        DefaultTableModel model = new DefaultTableModel(0, 0);
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

