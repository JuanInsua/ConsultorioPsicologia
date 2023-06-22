import Archivo.ControladoraArchivo;
import JSON.ControladoraJson;
import JSON.JsonUtiles;
import Logica.Principal.SistemaPrincipal;

public class Main {
    public static void main(String[] args) {
        //SistemaPrincipal sistemaPrincipal=new SistemaPrincipal(null);
        System.out.println("JSON Usuarios y sus informes");
        ControladoraJson.toJson();
        System.out.println(JsonUtiles.leer("consultorio"));
        System.out.println("Log Errores: ");
        ControladoraArchivo.leer();
    }
}