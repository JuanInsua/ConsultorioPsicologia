package Logica.CRUD;

import Exeption.CampoVacioExeption;
import Interfaz.I_ValidacionCampo;
import Modelo.Usuario;
import Persistencia.UsuarioSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
/**

 Esta clase representa una ventana de administración de usuarios.

 Se extiende de JDialog, convirtiéndola en una ventana de diálogo.
 */
public class CrudUsuario extends JDialog implements I_ValidacionCampo {
    // Componentes de la interfaz de usuario
    private JTable table1;
    private JPanel crudUsuario;
    private JButton listarUsuariosButton;
    private JButton VOLVERButton;
    private JButton modificarButton;
    private JButton limpiarButton;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton buscarButton;
    private JTextField textField8;
    private JTextField textField9;

    private UsuarioSQL usuarioSQL = new UsuarioSQL();

    /**
     * Constructor de la clase CrudUsuario.
     *
     * @param parent El marco padre para la ventana de diálogo.
     */
    public CrudUsuario(Frame parent) {
        super(parent);
        setTitle("Administración");
        setContentPane(crudUsuario);
        setMinimumSize(new Dimension(1420, 820));
        setModal(true);
        setLocationRelativeTo(parent);

        VOLVERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        listarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(listarEnTabla());
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCamposUsuario();
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
                usuarioSQL.modificar(setUsuarioModificar());
            }
        });
        setVisible(true);
    }

    /**
     * Limpia los campos de usuario en la interfaz de usuario.
     */
    private void limpiarCamposUsuario() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
    }

    /**
     * Devuelve un TableModel con los datos de todos los usuarios registrados en la base de datos, para ser utilizado en una tabla.
     *
     * @return Un TableModel con los datos de los usuarios.
     */
    public TableModel listarEnTabla() {
        ArrayList<Usuario> usuarios = usuarioSQL.listar();
        DefaultTableModel model = new DefaultTableModel(0, 0);
        String[] columnName = {"Nombre", "Dni", "Email", "Contraseña", "Palabra de recuperación", "Estado"};
        model.setColumnIdentifiers(columnName);
        model.addRow(columnName);
        Object[] objects = new Object[6];
        for (int i = 0; i < usuarios.size(); i++) {
            objects[0] = usuarios.get(i).getPaciente().getNombre();
            objects[1] = usuarios.get(i).getPaciente().getDni();
            objects[2] = usuarios.get(i).getPaciente().getEmail();
            objects[3] = usuarios.get(i).getPassword();
            objects[4] = usuarios.get(i).getPalabraRecuperacion();
            objects[5] = usuarios.get(i).isEstado();
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
                if (textField6.getText().equalsIgnoreCase("true")) {
                    estado = true;
                } else {
                    estado = false;
                }
                usuarioBuscado = new Usuario(
                        textField1.getText(),
                        textField3.getText(),
                        textField7.getText(),
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
                && !textField4.getText().isEmpty() && !textField5.getText().isEmpty() && !textField6.getText().isEmpty() && !textField7.getText().isEmpty()) {
            return true;
        } else throw new CampoVacioExeption();
    }

    /**
     * Busca un usuario por número de identificación y muestra los datos en los campos de la interfaz de usuario.
     */
    public void getBuscarPorDni() {
        try {
            if (!textField7.getText().isEmpty()) {
                Usuario usuarioBuscado = usuarioSQL.buscarUsuarioDni(textField7.getText());
                textField1.setText(usuarioBuscado.getPaciente().getNombre());
                textField3.setText(usuarioBuscado.getPaciente().getEmail());
                textField4.setText(usuarioBuscado.getPassword());
                textField5.setText(usuarioBuscado.getPalabraRecuperacion());
                String estado;
                if (usuarioBuscado.isEstado()) {
                    estado = "true";
                } else {
                    estado = "false";
                }
                textField6.setText(estado);
            } else {
                JOptionPane.showMessageDialog(this, "Campos para búsqueda vacíos", "Intente otra vez", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RuntimeException r) {
            JOptionPane.showMessageDialog(this, "Error en la búsqueda", "Intente otra vez", JOptionPane.ERROR_MESSAGE);
        }
    }
}


