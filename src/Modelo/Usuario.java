package Modelo;

import Interfaz.I_ToJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
/**

 La clase Usuario representa un usuario en el sistema.
 */
public class Usuario extends Paciente implements I_ToJson {
    private String password;
    private String palabraRecuperacion;
    private boolean estado;

    /**

     Construye un nuevo objeto Usuario con valores predeterminados.
     */
    public Usuario() {
    }
    /**

     Construye un nuevo objeto Usuario con los parámetros especificados.
     @param nombreUsuario el nombre del Paciente
     @param email el correo electrónico del Paciente
     @param dni el DNI del Paciente
     @param password la contraseña del Usuario
     @param palabraRecuperacion la palabra de recuperación del Usuario
     */
    public Usuario(String nombreUsuario, String email, String dni, String password, String palabraRecuperacion, boolean estado) {
        super(nombreUsuario,email,dni);
        this.password = password;
        this.palabraRecuperacion = palabraRecuperacion;
        this.estado = estado;
    }

    /**
     Devuelve la contraseña de este Usuario.
     @return la contraseña
     */
    public String getPassword() {
        return password;
    }
    /**

     Establece la contraseña de este Usuario.
     @param password la contraseña
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**

     Devuelve la palabra de recuperación de este Usuario.
     @return la palabra de recuperación
     */
    public String getPalabraRecuperacion() {
        return palabraRecuperacion;
    }
    /**

     Establece la palabra de recuperación de este Usuario.
     @param palabraRecuperacion la palabra de recuperación
     */
    public void setPalabraRecuperacion(String palabraRecuperacion) {
        this.palabraRecuperacion = palabraRecuperacion;
    }
    /**

     Devuelve el estado de este Usuario.
     @return el estado
     */
    public boolean isEstado() {
        return estado;
    }
    /**

     Establece el estado de este Usuario.
     @param estado el estado
     */
    private void setEstado(boolean estado) {
        this.estado = estado;
    }
    /**

     Indica si algún otro objeto es "igual a" este.
     @param o el objeto de referencia con el que se compara
     @return {@code true} si este objeto es igual al argumento obj; {@code false} en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return estado == usuario.estado  && Objects.equals(password, usuario.password) && Objects.equals(palabraRecuperacion, usuario.palabraRecuperacion);
    }
    /**

     Devuelve un valor de código hash para el objeto.
     @return un valor de código hash para este objeto
     */
    @Override
    public int hashCode() {
        return 1;
    }
    /**

     Devuelve una representación en cadena del objeto.
     @return una representación en cadena del objeto
     */
    @Override
    public String toString() {
        return super.toString() + "Usuario{" +
                "paciente=" +
                ", password='" + password + "" +
                ", palabraRecuperacion='" + palabraRecuperacion + "" +
                ", estado=" + estado +
                '}';
    }

    @Override
    public JSONObject toJson() {

        JSONObject jsonObjectUsuario=new JSONObject();
        try {
            jsonObjectUsuario.put("palabraRecuperacion",this.palabraRecuperacion);
            jsonObjectUsuario.put("estado",this.estado);
            jsonObjectUsuario.put("password",this.password);
            jsonObjectUsuario.put("email",this.getEmail());
            jsonObjectUsuario.put("dniUsuario",this.getDni());
            jsonObjectUsuario.put("nombreUsuario",this.getNombre());
        }catch (JSONException je){
            System.out.println(je.getMessage());
        }
        return jsonObjectUsuario;
    }
}
