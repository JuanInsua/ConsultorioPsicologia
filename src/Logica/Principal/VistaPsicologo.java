package Logica.Principal;

import Exeption.CampoVacioExeption;
import Exeption.DiaExeption;
import Exeption.DniExeption;
import Interfaz.I_ListarEnTabla;
import Interfaz.I_ValidacionCampo;
import Modelo.Consultorio;
import Modelo.Sesion;
import Modelo.Turno;
import Persistencia.SesionSQL;
import Persistencia.TurnoSQL;
import Persistencia.UsuarioSQL;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa la vista del psicólogo en el sistema.
 * Esta clase extiende de JDialog e implementa la interfaz I_ValidacionCampo.
 */
public class VistaPsicologo extends JDialog implements I_ValidacionCampo, I_ListarEnTabla {
    private JButton SALIRButton;
    private JPanel vistaPsicologo;
    private JButton atenderTurnoButton;
    private JButton informesButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTable table1;
    private JTextField textField3;
    private JTextField textField4;
    private JTable table2;
    private JTextArea textArea1;
    private TurnoSQL turnoSQL=new TurnoSQL();
    private SesionSQL sesionSQL=new SesionSQL();;
    private Consultorio consultorio = new Consultorio();
    private UsuarioSQL usuarioSQL = new UsuarioSQL();

    /**
     * Constructor de la clase VistaPsicologo.
     *
     * @param parent El marco padre de la ventana.
     */
    public VistaPsicologo(Frame parent) {
        // Configuración de la ventana
        super(parent);
        setTitle("Psicologo");
        setContentPane(vistaPsicologo);
        setSize(new Dimension(1280, 1020));
        setModal(true);
        setLocationRelativeTo(parent);
        table1.setModel(listarEnTabla());

        // Acción del botón SALIR
        SALIRButton.addActionListener(e -> dispose());

        // Acción del botón informes
        informesButton.addActionListener(e -> table2.setModel(generarListaSesiones()));

        // Acción del botón atenderTurno
        atenderTurnoButton.addActionListener(e -> {atenderTurno();table1.setModel(listarEnTabla());});

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    table1.removeEditor();
                    try {
                        seleccionar(e);
                    }catch (RuntimeException re){
                        re.getMessage();
                    }
            }
        });
        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                table2.removeEditor();
                try {
                    selecionarSesion(e);
                }catch (RuntimeException re){
                    re.getMessage();
                }
            }
        });
    }
    public void seleccionar(MouseEvent e) throws RuntimeException{
        int selection=table1.rowAtPoint(e.getPoint());
        if (selection!=0){
            textField1.setText(String.valueOf(table1.getValueAt(selection,0)));
            textField2.setText(String.valueOf(table1.getValueAt(selection,1)));
        }else throw new RuntimeException();
    }
    public void selecionarSesion(MouseEvent e) throws RuntimeException{
        int selection=table2.rowAtPoint(e.getPoint());
        table2.isRowSelected(selection);
        if (selection!=0) {
            textArea1.setText(String.valueOf(table2.getValueAt(selection, 3)));
        }else throw new RuntimeException();
    }

    /**
     * Método privado para listar los turnos en una tabla.
     *
     * @return El modelo de tabla con los turnos listados.
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
            String[] columnName =new String[]{"Fecha", "Horario", "Motivo", "Dni", "Estado"};
            model.setColumnIdentifiers(columnName);
            model.addRow(columnName);
            Object[] objects = new Object[5];
            for (int i = 0; i < turnos.size(); i++) {
                if (turnos.get(i).getEstado().name().equalsIgnoreCase("activado")) {
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
     * Método privado para generar la lista de sesiones en una tabla.
     *
     * @return El modelo de tabla con las sesiones listadas.
     */
    private TableModel generarListaSesiones() {
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        try {
            if (!textField3.getText().isEmpty()){
                if (validarDni()){
                    HashSet sesionesDni = consultorio.listarSesionesPorDNI(textField3.getText());
                    String[] columnName = {"Dni", "Horario", "Motivo", "Resumen", "Fecha"};
                    model.setColumnIdentifiers(columnName);
                    model.addRow(columnName);
                    Object[] objects = new Object[5];
                    Iterator it= sesionesDni.iterator();
                    while (it.hasNext()){
                        Sesion sesion=(Sesion) it.next();
                        objects[0] = sesion.getTurno().getDniUsuario();
                        objects[1] = sesion.getTurno().getHorarioConsulta();
                        objects[2] = sesion.getTurno().getMotivoConsulta();
                        objects[3] = sesion.getResumenSesion();
                        objects[4] = sesion.getTurno().getFechaConsulta();
                        model.addRow(objects);
                    }
                }
            }else {
                JOptionPane.showMessageDialog(this, "Error campo vacio de busqueda", "Intentar otra vez", JOptionPane.ERROR_MESSAGE);
            }

        } catch (DniExeption de) {
            JOptionPane.showMessageDialog(this, de.getMessage(), "Intentar otra vez", JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }

    /**
     * Método privado para validar el DNI ingresado.
     *
     * @return true si el DNI es válido, false en caso contrario.
     * @throws DniExeption Si el DNI no corresponde a un usuario.
     */
    private boolean validarDni() throws DniExeption {
        if (usuarioSQL.buscarUsuarioDni(textField3.getText()) != null) {
            return true;
        } else {
            textField3.setText("");
            throw new DniExeption("Dni no corresponde a un Usuario");
        }
    }

    /**
     * Método privado para limpiar los campos de texto.
     */
    private void limpiarCampos() {
        textField1.setText("");
        textField2.setText("");
        textField4.setText("");
        textField3.setText("");
    }

    /**
     * Método privado para atender un turno.
     */
    private void atenderTurno() {
        try {
            if (validacionCampo()) {
                Turno turnoBuscado = turnoSQL.buscarTurno(textField1.getText().toLowerCase(), textField2.getText().toLowerCase());
                if (turnoBuscado != null && turnoBuscado.getEstado().name().equalsIgnoreCase("activado")) {
                    Sesion sesionNueva = new Sesion(turnoBuscado, textField4.getText().toLowerCase());
                    sesionSQL.registrar(sesionNueva);
                    turnoSQL.modificarEstado(turnoBuscado, "atendido".toUpperCase());
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Turno no encontrado", "Intente otra vez", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (CampoVacioExeption ce) {
            JOptionPane.showMessageDialog(this, ce.getMessage(), "Intente otra vez", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método de validación de campos.
     *
     * @return true si los campos son válidos, false en caso contrario.
     * @throws CampoVacioExeption Si algún campo está vacío.
     */
    @Override
    public boolean validacionCampo() throws CampoVacioExeption {
        if (!textField1.getText().isEmpty() && !textField2.getText().isEmpty() && !textField4.getText().isEmpty()) {
            return true;
        } else {
            throw new CampoVacioExeption();
        }
    }
}

