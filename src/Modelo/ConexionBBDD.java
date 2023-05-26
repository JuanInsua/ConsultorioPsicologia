package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBBDD {
    Connection conn;
    String DB_URL="jdbc:mysql://uekoylgwrdq2igzq:UBSHnTrnCgHOZIt7abQW@bty7sho5dbjw5zqxm5yn-mysql.services.clever-cloud.com:3306/bty7sho5dbjw5zqxm5yn";
    String USERNAME="uekoylgwrdq2igzq";
    String PASSWORD="UBSHnTrnCgHOZIt7abQW";
    public Connection getConexion(){
        try {
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            return conn;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
