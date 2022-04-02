import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connexio {

    private String miDriver;
    private String miUrl;
    private Connection conexion;

    public Connexio(String miDriver, String miUrl) {
        try{
            this.miDriver = miDriver;
            this.miUrl = miUrl;
            Class.forName(miDriver);
            this.conexion = DriverManager.getConnection(miUrl, "root", "admin");
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}
