import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Lloguers {
    public void mostrarTots(){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            Statement sentencia = conexion.createStatement();
            ResultSet resul = sentencia.executeQuery("SELECT * FROM Lloguer;");
            while(resul.next()){
                String canviBoolean = "";
                System.out.println("Dni: "+resul.getString("dni"));
                System.out.println("Matrícula: "+resul.getString("matricula"));
                System.out.println("Dies: "+resul.getString("dies"));
                System.out.println("Preu per dia: "+resul.getString("preu"));
                System.out.println("Lloc de devolució: "+resul.getString("llocDevolucio"));
                if(resul.getString("depositPle").equalsIgnoreCase("1")){
                    canviBoolean = "Sí";
                }else{
                    canviBoolean = "No";
                }
                System.out.println("Retorn de depòsit ple: "+canviBoolean);
                System.out.println("Tipus Assegurança: "+resul.getString("tAsseguranca"));
                System.out.println("-----------------------------------------------\n");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void mostrarLloguers(String valor){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            String canviBoolean = "";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "SELECT * FROM Lloguer WHERE dni = ? or matricula= ?";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, valor);
            preparedStmt.setString(2, valor);
            ResultSet resul = preparedStmt.executeQuery();
            while(resul.next()){
                System.out.println("Dni: "+resul.getString("dni"));
                System.out.println("Matrícula: "+resul.getString("matricula"));
                System.out.println("Dies: "+resul.getString("dies"));
                System.out.println("Preu per dia: "+resul.getString("preu"));
                System.out.println("Lloc de devolució: "+resul.getString("llocDevolucio"));
                if(resul.getString("depositPle").equalsIgnoreCase("1")){
                    canviBoolean = "Sí";
                }else{
                    canviBoolean = "No";
                }
                System.out.println("Retorn de depòsit ple: "+canviBoolean);
                System.out.println("Tipus Assegurança: "+resul.getString("tAsseguranca"));
                System.out.println("-----------------------------------------------\n");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }

    public void inserirLloguer(String dni, String matricula, int dies, double preu, String llocDevolucio, boolean depositPle, String tAsseguranca){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "INSERT INTO LLOGUER (dni, matricula, dies, preu, llocDevolucio, depositPle, tAsseguranca) values (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, dni);
            preparedStmt.setString(2, matricula);
            preparedStmt.setInt(3, dies);
            preparedStmt.setDouble(4, preu);
            preparedStmt.setString(5, llocDevolucio);
            preparedStmt.setBoolean(6, depositPle);
            preparedStmt.setString(7, tAsseguranca);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void modificarLloguer(Map<String,String> map, String dni){
        try{
            String query = "UPDATE Lloguer SET ";
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
                if(claves.get(i)=="dies"){
                    int valorParseado = Integer.parseInt(valores.get(j));
                    preparedStmt.setInt(i+1, valorParseado);
                }else if(claves.get(i)=="preu"){
                    double valorParseadoaDouble =  Double.parseDouble(valores.get(j));
                    preparedStmt.setDouble(i+1, valorParseadoaDouble);
                }else if(claves.get(i)=="depositPle"){
                    boolean valorParseadoaBoolean =  Boolean.parseBoolean(valores.get(j));
                    preparedStmt.setBoolean(i+1, valorParseadoaBoolean);
                }else{
                    preparedStmt.setString(i+1, valores.get(j));
                }
            }
            String lastElement = claves.get(claves.size() - 1);
            preparedStmt.setString(claves.indexOf(lastElement)+2, dni);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }
    public void eliminarLloguer(String matricula){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "DELETE FROM Lloguer WHERE matricula = ?;";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, matricula);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
}
