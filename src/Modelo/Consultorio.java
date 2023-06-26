package Modelo;

import Exeption.UsuarioBuscadoException;
import Persistencia.SesionSQL;
import Persistencia.TurnoSQL;
import Persistencia.UsuarioSQL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * La clase Consultorio representa un consultorio médico que maneja un calendario de turnos.
 */
public class Consultorio  {
    ArrayList<Dia> calendario;
    TurnoSQL turnoSQL = new TurnoSQL();
    SesionSQL sesionSQL = new SesionSQL();
    UsuarioSQL usuarioSQL = new UsuarioSQL();
    HashSet sesiones = sesionSQL.listar();
    ArrayList<Turno> turnos = turnoSQL.listar();
    TreeMap<String, Usuario> usuarioTreeMap = usuarioSQL.listar();

    /**
     * Constructor de la clase Consultorio. Inicializa el calendario y lo carga con los días de la semana.
     */
    public Consultorio() {
        this.calendario = new ArrayList<>();
        setCalendario();
    }

    //-------------CALENDARIO--------------

    /**
     * Configura el calendario del consultorio con los días de la semana.
     */
    public void setCalendario() {
        Dia lunes = new Dia("lunes");
        Dia martes = new Dia("martes");
        Dia miercoles = new Dia("miercoles");
        Dia jueves = new Dia("jueves");
        Dia viernes = new Dia("viernes");
        calendario.add(0, lunes);
        calendario.add(1, martes);
        calendario.add(2, miercoles);
        calendario.add(3, jueves);
        calendario.add(4, viernes);
    }

    /**
     * Carga los turnos del consultorio en el calendario.
     */
    public void cargarCalendario() {
        ArrayList<Turno> turnos = turnoSQL.listar();
        for (Turno turno : turnos) {
            int indexDia = diaTurno(turno.getFechaConsulta());
            calendario.get(indexDia).agregarTurno(turno);
        }
    }

    /**
     * Obtiene el índice correspondiente a un día de la semana.
     *
     * @param diaInput el día de la semana ("lunes", "martes", etc.).
     * @return el índice correspondiente al día de la semana.
     */
    public int diaTurno(String diaInput) {
        int indexDia = 0;
        switch (diaInput) {
            case "lunes":
                indexDia = 0;
                break;
            case "martes":
                indexDia = 1;
                break;
            case "miercoles":
                indexDia = 2;
                break;
            case "jueves":
                indexDia = 3;
                break;
            case "viernes":
                indexDia = 4;
                break;
        }
        return indexDia;
    }

    /**
     * Obtiene el índice correspondiente a un horario de turno.
     *
     * @param horarioInput el horario del turno ("8a9", "9a10", etc.).
     * @return el índice correspondiente al horario del turno.
     */
    public int horarioTurno(String horarioInput) {
        int indexTurno = 0;
        switch (horarioInput) {
            case "8a9":
                indexTurno = 0;
                break;
            case "9a10":
                indexTurno = 1;
                break;
            case "10a11":
                indexTurno = 2;
                break;
            case "11a12":
                indexTurno = 3;
                break;
            case "12a13":
                indexTurno = 4;
                break;
        }
        return indexTurno;
    }

    /**
     * Busca los turnos de un día en el calendario.
     *
     * @param indexDia el índice del día en el calendario.
     * @return una lista de turnos para el día especificado.
     */
    public ArrayList<Turno> buscarDia(int indexDia) {
        cargarCalendario();
        return calendario.get(indexDia).turnos;
    }
    /**
     * Devuelve una lista de turnos asociados a un usuario específico.
     *
     * @param dniUsuario El DNI del usuario para el que se listarán los turnos.
     * @return Una ArrayList de objetos Turno asociados al usuario.
     */
    public ArrayList<Turno> listarTurnosUsuario(String dniUsuario) {
        ArrayList<Turno> turnos = turnoSQL.listar();
        ArrayList<Turno> turnosUsuario = new ArrayList<>();

        for (int i = 0; i < turnos.size(); i++) {
            if (turnos.get(i).getDniUsuario().equalsIgnoreCase(dniUsuario)) {
                turnosUsuario.add(turnos.get(i));
            }
        }

        return turnosUsuario;
    }

    /**
     * Devuelve una lista de todos los turnos existentes.
     *
     * @return Una ArrayList de todos los objetos Turno.
     */
    public ArrayList<Turno> listarTurnos() {
        return turnos;
    }

    /**
     * Devuelve la cantidad de turnos que han sido atendidos.
     *
     * @return La cantidad de turnos atendidos como una cadena de texto.
     */
    public String cantidadTurnosAtendidos() {
        Integer cant = 0;

        for (int i = 0; i < turnos.size(); i++) {
            if (turnos.get(i).getEstado().name().equalsIgnoreCase("atendido")) {
                cant++;
            }
        }
        return cant.toString();
    }

    /**
     * Devuelve la cantidad de turnos que han sido cancelados.
     *
     * @return La cantidad de turnos cancelados como una cadena de texto.
     */
    public String cantidadTurnosCancelados() {
        Integer cant = 0;

        for (int i = 0; i < turnos.size(); i++) {
            if (turnos.get(i).getEstado().name().equalsIgnoreCase("cancelado")) {
                cant++;
            }
        }
        return cant.toString();
    }

    /**
     * Devuelve un conjunto de sesiones asociadas a un DNI específico.
     *
     * @param dni El DNI del usuario para el que se listarán las sesiones.
     * @return Un HashSet de objetos Sesion asociados al DNI.
     */
    public HashSet listarSesionesPorDNI(String dni) {
        HashSet sesiones = sesionSQL.listar();
        HashSet sesionesDni = new HashSet();
        Iterator it = (Iterator) sesiones.iterator();

        while (it.hasNext()) {
            Sesion sesion = (Sesion) it.next();

            if (sesion.getTurno().getDniUsuario().equalsIgnoreCase(dni)) {
                sesionesDni.add(sesion);
            }
        }
        return sesionesDni;
    }

    /**
     * Devuelve una lista de todas las sesiones existentes.
     *
     * @return Un HashSet de todos los objetos Sesion.
     */
    public HashSet listarSesiones() {
        return sesiones;
    }

    /**
     * Devuelve la cantidad de sesiones existentes.
     *
     * @return La cantidad de sesiones como una cadena de texto.
     */
    public String cantidadSesiones() {
        Integer cantidad = 0;

        for (int i = 0; i < sesiones.size(); i++) {
            cantidad++;
        }

        return cantidad.toString();
    }
//-------------USUARIOS--------------
    /**
     * Devuelve un TreeMap con todos los usuarios.
     *
     * @return TreeMap con los usuarios.
     */
    public TreeMap<String, Usuario> listarUsuarios() {
        return usuarioSQL.listar();
    }
    /**
     * Busca un usuario por su email o DNI y contraseña.
     *
     * @param input    Email o DNI del usuario a buscar.
     * @param password Contraseña del usuario.
     * @return El objeto Usuario encontrado.
     * @throws UsuarioBuscadoException Si no se encuentra ningún usuario con los datos proporcionados.
     */
    public Usuario buscarUsuarioPasswordEmail(String input, String password) throws UsuarioBuscadoException {
        Usuario usuarioRta = null;
        TreeMap<String, Usuario> usuarios = usuarioTreeMap;
        int flag = 0;
        int i = 0;
        if (usuarios != null) {
            Iterator it = (Iterator) usuarios.entrySet().iterator();
            while (it.hasNext() && flag == 0) {
                Map.Entry entry = (Map.Entry) it.next();
                Usuario usuario1 = (Usuario) entry.getValue();
                if ((usuario1.getEmail().equalsIgnoreCase(input) || usuario1.getDni().equalsIgnoreCase(input)) && usuario1.getPassword().equalsIgnoreCase(password)) {
                    usuarioRta = usuario1;
                    flag = 1;
                }
            }
            if (flag == 0) {
                throw new UsuarioBuscadoException("Usuario no registrado");
            }
        }
        return usuarioRta;
    }
    /**
     * Busca y retorna la contraseña de un usuario por su palabra de recuperación y email o DNI.
     *
     * @param palabraRec Palabra de recuperación del usuario.
     * @param input      Email o DNI del usuario.
     * @return La contraseña del usuario encontrado.
     */
    public String buscarRetornarPw(String palabraRec, String input) {
        String passwordBuscada = null;
        TreeMap<String, Usuario> usuarios = usuarioTreeMap;
        int flag = 0;
        if (usuarios != null) {
            Iterator it = (Iterator) usuarios.entrySet().iterator();
            while (it.hasNext() && flag == 0) {
                Map.Entry entry = (Map.Entry) it.next();
                Usuario usuario1 = (Usuario) entry.getValue();
                if ((usuario1.getEmail().equalsIgnoreCase(input) || usuario1.getDni().equalsIgnoreCase(input)) && usuario1.getPalabraRecuperacion().equalsIgnoreCase(palabraRec)) {
                    if (usuario1.isEstado()) {
                        passwordBuscada = "PASSWORD: " + usuario1.getPassword();
                    } else {
                        passwordBuscada = "Usuario está de baja";
                    }
                    flag = 1;
                } else {
                    passwordBuscada = "Usuario no existe";
                }
            }
        }
        return passwordBuscada;
    }

/// -----------------JSON---------------------
    /**
     * Convierte los usuarios a formato JSON y los devuelve en un JSONArray.
     *
     * @return JSONArray con los usuarios en formato JSON.
     */
    public JSONArray usuariosToJson() {
        JSONArray jsonArrayUsuarios = new JSONArray();
        try {
            Iterator it = (Iterator) usuarioTreeMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Usuario usuario = (Usuario) entry.getValue();
                JSONObject jsonObjectUser = usuario.toJson();
                JSONArray jsonArraySesiones = sesionesToJson(usuario.getDni());
                jsonObjectUser.put("sesiones", jsonArraySesiones);
                jsonArrayUsuarios.put(jsonObjectUser);
            }
        } catch (JSONException je) {
            System.out.println(je.getMessage());
        }
        return jsonArrayUsuarios;
    }
    /**
     * Convierte las sesiones de un usuario con el DNI dado a formato JSON y las devuelve en un JSONArray.
     *
     * @param dni DNI del usuario.
     * @return JSONArray con las sesiones en formato JSON.
     */
    public JSONArray sesionesToJson(String dni) {
        JSONArray jsonArraySesiones = new JSONArray();
            HashSet hashSetSesiones = listarSesionesPorDNI(dni);
            Iterator it = (Iterator) hashSetSesiones.iterator();
            while (it.hasNext()) {
                Sesion sesion = (Sesion) it.next();
                JSONObject jsonObjectSesion = sesion.toJson();
                jsonArraySesiones.put(jsonObjectSesion);
            }
        return jsonArraySesiones;
    }

}
