package Logica.CRUD;

import Exeption.CampoVacioExeption;
import Interfaz.I_LimpiarCampo;
import Interfaz.I_ListarEnTabla;
import Interfaz.I_Seleccionar;
import Interfaz.I_ValidacionCampo;
import Modelo.Consultorio;
import Modelo.Usuario;
import Persistencia.UsuarioSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**

 Esta clase representa una ventana de administración de usuarios.

 Se extiende de JDialog, convirtiéndola en una ventana de diálogo.
 */
public class CrudUsuario extends JDialog implements I_ValidacionCampo, I_ListarEnTabla, I_LimpiarCampo, I_Seleccionar {
    // Componentes de la interfaz de usuario
    private JTable table1;
    private JPanel crudUsuario;
    private JButton VOLVERButton;
    private JButton modificarButton;
    private JButton limpiarButton;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;
    private JLabel labelDni;


    private UsuarioSQL usuarioSQL = new UsuarioSQL();
    private Consultorio consultorio=new Consultorio();

    /**
     * Constructor de la clase CrudUsuario.
     *
     * @param parent El marco padre para la ventana de diálogo.
     */
    public CrudUsuario(Frame parent) {
        super(parent);
        setTitle("Administración");
        setContentPane(crudUsuario);
        setSize(new Dimension(1420, 820));
        setModal(true);
        setLocationRelativeTo(parent);
        table1.setModel(listarEnTabla());

        VOLVERButton.addActionListener(e -> dispose());
        limpiarButton.addActionListener(e -> limpiarCampos());
        modificarButton.addActionListener(e -> {
            usuarioSQL.modificar(setUsuarioModificar());table1.setModel(listarEnTabla());}
        );
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
        if (selection!=0){
            textField1.setText(String.valueOf(table1.getValueAt(selection,0)));
            textField3.setText(String.valueOf(table1.getValueAt(selection,2)));
            textField4.setText(String.valueOf(table1.getValueAt(selection,3)));
            textField5.setText(String.valueOf(table1.getValueAt(selection,4)));
            labelDni.setText(table1.getValueAt(selection,1).toString());
            if(String.valueOf(table1.getValueAt(selection,5)).toString().equalsIgnoreCase("true")){
                comboBox1.setSelectedIndex(0);
            }else if (String.valueOf(table1.getValueAt(selection,5)).toString().equalsIgnoreCase("false")){
                comboBox1.setSelectedIndex(1);
            }
        }else throw new  RuntimeException();
    }
    /**
     * Limpia los campos de usuario en la interfaz de usuario.
     */
    @Override
    public void limpiarCampos() {
        try {
            if (validacionCampo()){
                textField1.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                labelDni.setText("DNI Usuario:");
            }
        }catch (CampoVacioExeption ce){
            JOptionPane.showMessageDialog(this,ce.getMessage(),"Intente otra vez",JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Devuelve un TableModel con los datos de todos los usuarios registrados en la base de datos, para ser utilizado en una tabla.
     *
     * @return Un TableModel con los datos de los usuarios.
     */
    @Override
    public TableModel listarEnTabla() {
            TreeMap<String,Usuario> usuarios = consultorio.listarUsuarios();
            DefaultTableModel model = new DefaultTableModel(0, 0){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String[] columnName = {"Nombre", "Dni", "Email", "Contraseña", "Palabra de recuperación", "Estado"};
            model.setColumnIdentifiers(columnName);
            model.addRow(columnName);
            Object[] objects = new Object[6];
            Iterator it=(Iterator) usuarios.entrySet().iterator();
            while (it.hasNext())
             {
                 Map.Entry entry=(Map.Entry) it.next();
                 Usuario usuario=(Usuario)entry.getValue();
                objects[0] = usuario.getNombre();
                objects[1] = usuario.getDni();
                objects[2] = usuario.getEmail();
                objects[3] = usuario.getPassword();
                objects[4] = usuario.getPalabraRecuperacion();
                objects[5] = usuario.isEstado();
                model.addRow(objects);
            }
            return model;
    }

    /**
     * Crea un objeto Usuario a partir de los datos en los campos de la interfaz de usuario.
     *
     * @return Un objeto Usuario con los datos ingresados en la interfaz.
     */
    private Usuario setUsuarioModificar() {
        Usuario usuarioBuscado = null;
        try {
            if (validacionCampo()) {
                Boolean estado;
                if (comboBox1.getSelectedIndex()==0) {
                    estado = true;
                } else{
                    estado = false;
                }
                usuarioBuscado = new Usuario(
                        textField1.getText(),
                        textField3.getText(),
                        labelDni.getText(),
                        textField4.getText(),
                        textField5.getText(),
                        estado);
            }
        } catch (CampoVacioExeption ce) {
            JOptionPane.showMessageDialog(this, ce.getMessage(), "Intente otra vez", JOptionPane.ERROR_MESSAGE);
        }
        return usuarioBuscado;
    }

    /**
     * Realiza la validación de los campos necesarios para modificar un usuario.
     *
     * @return true si todos los campos son válidos, false en caso contrario.
     */
    @Override
    public boolean validacionCampo() throws CampoVacioExeption {

        if (!textField1.getText().isEmpty() && !textField3.getText().isEmpty()
                && !textField4.getText().isEmpty() && !textField5.getText().isEmpty() && !labelDni.getText().isEmpty()) {
            return true;
        } else throw new CampoVacioExeption();
    }
}


