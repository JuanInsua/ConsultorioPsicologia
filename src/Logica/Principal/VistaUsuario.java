package Logica.Principal;

import Exeption.CampoVacioExeption;
import Exeption.CaracteresMotivoInvalidosException;
import Interfaz.I_LimpiarCampo;
import Interfaz.I_ListarEnTabla;
import Interfaz.I_ValidacionCampo;
import Modelo.Consultorio;
import Modelo.Estado;
import Modelo.Turno;
import Modelo.Usuario;
import Persistencia.TurnoSQL;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;


/**

 Esta clase representa la vista del usuario en la aplicación.

 Extiende de JDialog e implementa la interfaz I_ValidacionCampo.
 */
public class VistaUsuario extends JDialog implements I_ValidacionCampo, I_LimpiarCampo {
    private JPanel vistaUsuario;
    private JLabel nombreUsuario;
    private JButton SALIRButton;
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
    JPanel Turnos;
    private JButton agregarTurnoButton;
    private JTextField textField1;
    private JLabel emailLabel;
    private JButton modificarPerfilButton;
    private String fecha="";
    private String horario="";
    private TurnoSQL turnoSQL;
    private Consultorio consultorio;
    private Color colorBackGroundButtonDia;
    private Color colorForeGroundButton;
    private Color colorBackgroundNoDispobibleHorario;
    private Color backGroundDefault;

    /**

     Constructor de la clase VistaUsuario.

     @param parent El frame padre de la ventana.

     @param usuario El objeto Usuario que representa al usuario actual.
     */
    public VistaUsuario(Frame parent, Usuario usuario) {
        super(parent);
        setTitle("Usuario");
        setContentPane(vistaUsuario);
        setSize(new Dimension(1280, 720));
        setModal(true);
        setLocationRelativeTo(parent);
        nombreUsuario.setText("Bienvenido " + usuario.getNombre().toUpperCase(Locale.ROOT) + "!");
        emailLabel.setText("Email: " + usuario.getEmail());
        table1.setBorder(new LineBorder(Color.black));
        resetbuttonVisibleHorario(false);
        turnoSQL = new TurnoSQL();
        consultorio = new Consultorio();
        colorBackGroundButtonDia = new Color(154, 34, 209);
        colorForeGroundButton = new Color(207, 34, 209);
        colorBackgroundNoDispobibleHorario = new Color(208, 33, 61);
        backGroundDefault = new Color(187, 182, 183);

        SALIRButton.addActionListener(e -> dispose());

        button1.addActionListener(e -> {
            fecha="Lunes";
            resetbuttonHorario();
            resetbuttonDia();
            resetbuttonVisibleHorario(true);
            setHorario("");
            turnosDisponibles(0);
            setBackgroundDia(button1);
        });

        button2.addActionListener(e -> {
            fecha="Martes";
            resetbuttonHorario();
            resetbuttonDia();
            resetbuttonVisibleHorario(true);
            setHorario("");
            turnosDisponibles(1);
            setBackgroundDia(button2);
        });

        button3.addActionListener(e -> {
            fecha="Miercoles";
            resetbuttonHorario();
            resetbuttonDia();
            resetbuttonVisibleHorario(true);
            setHorario("");
            turnosDisponibles(2);
            setBackgroundDia(button3);
        });

        button4.addActionListener(e -> {
            fecha="Jueves";
            resetbuttonHorario();
            resetbuttonDia();
            resetbuttonVisibleHorario(true);
            setHorario("");
            turnosDisponibles(3);
            setBackgroundDia(button4);
        });

        button5.addActionListener(e -> {
            fecha="Viernes";
            resetbuttonHorario();
            resetbuttonDia();
            resetbuttonVisibleHorario(true);
            setHorario("");
            turnosDisponibles(4);
            setBackgroundDia(button5);
        });

        button6.addActionListener(e -> {
            setHorario("8a9");
            resetForegroundHorario();
            setForegroundHorario(button6);
        });

        button7.addActionListener(e -> {
            setHorario("9a10");
            resetForegroundHorario();
            setForegroundHorario(button7);
        });

        button8.addActionListener(e -> {
            setHorario("10a11");
            resetForegroundHorario();
            setForegroundHorario(button8);
        });

        button9.addActionListener(e -> {
            setHorario("11a12");
            resetForegroundHorario();
            setForegroundHorario(button9);
        });

        button10.addActionListener(e -> {
            setHorario("12a13");
            resetForegroundHorario();
            setForegroundHorario(button10);
        });

        agregarTurnoButton.addActionListener(e -> generarTurno(usuario.getDni()));

        misTurnosButton.addActionListener(e -> table1.setModel(setModelTabla(usuario.getDni())));

        modificarPerfilButton.addActionListener(e -> {
            PasswordModificar passwordModificar = new PasswordModificar(null, usuario);
            passwordModificar.setVisible(true);
        });

        setVisible(true);
    }
    /**

     Obtiene los turnos disponibles para el día seleccionado.
     @param indexDia El índice del día seleccionado.
     */
    private void turnosDisponibles(int indexDia) {
        ArrayList<Turno> turnos = consultorio.buscarDia(indexDia);
        resetForegroundHorario();
        if (turnos.isEmpty()) {
            resetbuttonHorario();
        } else {
            int i = 0;
            while (i < turnos.size()) {
                setbuttonHorario(turnos.get(i));
                i++;
            }
        }
    }
    /**
     Establece el color de primer plano del botón de horario seleccionado.
     @param button El botón de horario seleccionado.
     */
    public void setForegroundHorario(JButton button) {
        button.setForeground(colorForeGroundButton);
    }
    /**
     Establece el color de fondo del botón de día seleccionado.
     @param button El botón de día seleccionado.
     */
    public void setBackgroundDia(JButton button) {
        button.setBackground(colorBackGroundButtonDia);
    }
    /**

     Restablece el color de primer plano de todos los botones de horario.
     */
    public void resetForegroundHorario() {
        button6.setForeground(Color.black);
        button7.setForeground(Color.black);
        button8.setForeground(Color.black);
        button9.setForeground(Color.black);
        button10.setForeground(Color.black);
    }
    /**

     Restablece el estado y color de fondo de todos los botones de horario.
     */
    public void resetbuttonHorario() {
        button6.setBackground(backGroundDefault);
        button6.setEnabled(true);
        button7.setBackground(backGroundDefault);
        button7.setEnabled(true);
        button8.setBackground(backGroundDefault);
        button8.setEnabled(true);
        button9.setBackground(backGroundDefault);
        button9.setEnabled(true);
        button10.setBackground(backGroundDefault);
        button10.setEnabled(true);
    }
    /**

     Restablece el color de fondo de todos los botones de día.
     */
    public void resetbuttonDia() {
        button1.setBackground(backGroundDefault);
        button2.setBackground(backGroundDefault);
        button3.setBackground(backGroundDefault);
        button4.setBackground(backGroundDefault);
        button5.setBackground(backGroundDefault);
    }
    /**

     Establece la visibilidad de los botones de horario.
     @param rta El valor booleano para establecer la visibilidad.
     */
    public void resetbuttonVisibleHorario(boolean rta) {
        button6.setVisible(rta);
        button7.setVisible(rta);
        button8.setVisible(rta);
        button9.setVisible(rta);
        button10.setVisible(rta);
    }
    /**

     Establece el estado y color de fondo del botón de horario según el turno.
     @param turno El turno a establecer.
     */
    public void setbuttonHorario(Turno turno) {
        if (turno != null && turno.getEstado().name().equalsIgnoreCase("activado")) {
            int indexHorario = consultorio.horarioTurno(turno.getHorarioConsulta());
            switch (indexHorario) {
                case 0:
                    button6.setBackground(colorBackgroundNoDispobibleHorario);
                    button6.setEnabled(false);
                    break;
                case 1:
                    button7.setBackground(colorBackgroundNoDispobibleHorario);
                    button7.setEnabled(false);
                    break;
                case 2:
                    button8.setBackground(colorBackgroundNoDispobibleHorario);
                    button8.setEnabled(false);
                    break;
                case 3:
                    button9.setBackground(colorBackgroundNoDispobibleHorario);
                    button9.setEnabled(false);
                    break;
                case 4:
                    button10.setBackground(colorBackgroundNoDispobibleHorario);
                    button10.setEnabled(false);
                    break;
            }
        }
    }
    /**
     Establece el horario actual seleccionado.
     @param horario El horario actual seleccionado.
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     Limpia los campos de usuario.
     */
    @Override
    public void limpiarCampos() {
        textField1.setText("");
        fecha = "";
        horario = "";
    }

    /**
     Genera un nuevo turno para el usuario.
     @param dniUsuario El DNI del usuario.
     */
    private void generarTurno(String dniUsuario) {
        try {
            if (validacionCampo()) {
                validacionCaracteresMotivo();
                Turno turnoAgregar = new Turno(dniUsuario, textField1.getText(), fecha, horario);
                turnoAgregar.setEstado(Estado.ACTIVADO);
                turnoSQL.registrar(turnoAgregar);
                limpiarCampos();
                resetbuttonDia();
                resetbuttonHorario();
                resetForegroundHorario();
                resetbuttonVisibleHorario(false);
            }
        } catch (CampoVacioExeption ce) {
            JOptionPane.showMessageDialog(this,
                    ce.getMessage(),
                    "Intente otra vez",
                    JOptionPane.ERROR_MESSAGE);
        } catch (CaracteresMotivoInvalidosException cmi) {
            JOptionPane.showMessageDialog(this,
                    cmi.getMessage(),
                    "Intente otra vez",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    /**

     Establece el modelo de tabla para los turnos del usuario.
     @param dniUsuario El DNI del usuario.
     @return El modelo de tabla con los turnos del usuario.
     */
    private DefaultTableModel setModelTabla(String dniUsuario) {
        ArrayList<Turno> turnos = consultorio.listarTurnosUsuario(dniUsuario);
        String[] titulos = {"Fecha", "Horario"};
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        String[] fila1 = {"Fecha","Horario"};
        model.addRow(fila1);
        for (Turno t : turnos) {
            if (t.getEstado().name().equalsIgnoreCase("activado")) {
                String[] fila2 = {
                        t.getFechaConsulta(), t.getHorarioConsulta()
                };
                model.addRow(fila2);
            }
        }
        return model;
    }

    /**

     Valida que los campos de usuario estén completos.
     @return true si los campos están completos, false de lo contrario.
     @throws CampoVacioExeption Si algún campo está vacío.
     */
    @Override
    public boolean validacionCampo() throws CampoVacioExeption {
        if (textField1.getText().isEmpty() || fecha.isEmpty() || horario.isEmpty()) {
            throw new CampoVacioExeption();
        }
        return true;
    }
    /**

     Valida que los caracteres del motivo sean válidos.
     @throws CaracteresMotivoInvalidosException Si el motivo contiene caracteres inválidos.
     */
    public void validacionCaracteresMotivo() throws CaracteresMotivoInvalidosException {
        String motivo = textField1.getText();
        String[] caracteresInvalidos = {"<", ">", "&", "|"};
        for (String c : caracteresInvalidos) {
            if (motivo.contains(c)) {
            throw new CaracteresMotivoInvalidosException("El motivo contiene caracteres inválidos");
            }
        }
    }
}
