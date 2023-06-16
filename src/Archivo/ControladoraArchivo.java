package Archivo;

import java.io.*;
import java.util.Date;

/**
 * La clase ControladoraArchivo proporciona métodos para grabar y leer archivos.
 */
public class ControladoraArchivo {
    /**
     * Graba un elemento y un mensaje en un archivo llamado "errores.txt".
     * Si el archivo no existe, se crea. El mensaje se adjunta al archivo.
     *
     * @param elemento El elemento a grabar.
     * @param message El mensaje a grabar.
     */
    public static void grabar(Date elemento, String message) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            String data = "| "+message + ": " + elemento +"| ";
            File file = new File("errores.txt");
            // Si el archivo no existe, se crea.
            if (!file.exists()) {
                file.createNewFile();
            }
            // flag true, indica adjuntar información al archivo.
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     * Lee el contenido del archivo "errores.txt" y lo imprime por pantalla.
     * Si ocurre algún error durante la lectura, se muestra un mensaje de error.
     */
    public static void leer() {
        try {
            // Abrimos el archivo con la ruta especificada.
            FileInputStream fstream = new FileInputStream(new File("errores.txt"));
            // Creamos el objeto de entrada
            DataInputStream entrada = new DataInputStream(fstream);
            // Creamos el Buffer de Lectura
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            // Leer el archivo línea por línea
            while ((strLinea = buffer.readLine()) != null) {
                // Imprimimos la línea por pantalla
                System.out.println(strLinea);
            }
            // Cerramos el archivo
            entrada.close();
        } catch (Exception e) { // Catch de excepciones
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }
}

