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

    /*EJEMPLO DE COMO SERIA UNA CONEXION SIN CLASES

      String miDriver="com.mysql.cj.jdbc.Driver";
      String miUrl = "jdbc:mysql://localhost/carsRental";
      Class.forName(miDriver);
      Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
      Statement sentencia = conexion.createStatement();
      ResultSet resul = sentencia.executeQuery("SELECT * FROM Mecanics;");*/
}
