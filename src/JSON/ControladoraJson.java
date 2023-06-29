package JSON;

import Modelo.Consultorio;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * La clase ControladoraJson se encarga de convertir objetos en formato JSON.
 */
public class ControladoraJson {

    /**
     * Convierte un objeto Consultorio en formato JSON.
     *
     * @return El objeto JSONObject que representa el Consultorio en formato JSON.
     */
    public static void toJson(Consultorio consultorio) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArrayUsuarios = consultorio.usuariosToJson();
            jsonObject.put("nombreConsultorio","Consultorio Psycho");
            jsonObject.put("usuarios", jsonArrayUsuarios);
            JsonUtiles.grabar(jsonObject,"consultorio");
        } catch (JSONException je) {
            System.out.println(je.getMessage());
        }
    }
}

