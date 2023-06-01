package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a una base de datos.
 */
public class ConexionBBDD {

    private Connection conn;
    private String DB_URL = "jdbc:mysql://uekoylgwrdq2igzq:UBSHnTrnCgHOZIt7abQW@bty7sho5dbjw5zqxm5yn-mysql.services.clever-cloud.com:3306/bty7sho5dbjw5zqxm5yn";
    private String USERNAME = "uekoylgwrdq2igzq";
    private String PASSWORD = "UBSHnTrnCgHOZIt7abQW";

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return La conexión establecida a la base de datos.
     * @throws SQLException si ocurre un error al establecer la conexión.
     */
    public Connection getConexion() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            return conn;
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
    }
}
