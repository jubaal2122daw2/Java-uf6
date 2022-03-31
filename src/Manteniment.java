import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Manteniment {
    public void mostrarTots(){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            Statement sentencia = conexion.createStatement();
            ResultSet resul = sentencia.executeQuery("SELECT * FROM Manteniment;");
            while(resul.next()){
                System.out.println("Dni Mecànic: "+resul.getString("dniMecanic"));
                System.out.println("Matrícula: "+resul.getString("matricula"));
                System.out.println("Data Inici Manteniment: "+resul.getString("dataInici"));
                System.out.println("Data Fi Manteniment: "+resul.getString("dataFi"));
                System.out.println("-----------------------------------------------\n");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void mostrarManteniment(String valor){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "SELECT * FROM Manteniment WHERE dniMecanic = ?";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, valor);
            ResultSet resul = preparedStmt.executeQuery();
            while(resul.next()){
                System.out.println("Dni Mecànic: "+resul.getString("dniMecanic"));
                System.out.println("Matrícula: "+resul.getString("matricula"));
                System.out.println("Data Inici Manteniment: "+resul.getString("dataInici"));
                System.out.println("Data Fi Manteniment: "+resul.getString("dataFi"));
                System.out.println("-----------------------------------------------\n");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }

    public void inserirManteniment(String dni, String matricula, String dataInici, String dataFi){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "INSERT INTO MANTENIMENT (dniMecanic, matricula, dataInici, dataFi) values (?, ?, STR_TO_DATE(?,'%d-%m-%Y'), STR_TO_DATE(?,'%d-%m-%Y'));";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, dni);
            preparedStmt.setString(2, matricula);
            preparedStmt.setString(3, dataInici);
            preparedStmt.setString(4, dataFi);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void modificarManteniment(Map<String,String> map, String dni){
        try{
            String query = "UPDATE Manteniment SET ";
            ArrayList<String> claves = new ArrayList<String>();
            ArrayList<String> valores = new ArrayList<String>();
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");

            for (String key : map.keySet()) {
                claves.add(key);
                valores.add(map.get(key));
            }
            for(String c : claves){
                String last = claves.get(claves.size() - 1);
                if (claves.size() > 1 && c != last ){
                    query = query + c + " = ?,";
                }else{
                    query = query + c + " = ? WHERE matricula = ?;";
                }
            }
            System.out.println(query);
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            for(int i=0, j=0 ;i < claves.size() && j < valores.size(); i++,j++){
                preparedStmt.setString(i+1, valores.get(j));
            }
            String lastElement = claves.get(claves.size() - 1);
            preparedStmt.setString(claves.indexOf(lastElement)+2, dni);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }
    public void eliminarManteniment(String matricula){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "DELETE FROM Manteniment WHERE matricula = ?;";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, matricula);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }

}
