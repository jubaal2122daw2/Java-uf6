import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Mecanics {
    public void mostrarTots(){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            Statement sentencia = conexion.createStatement();
            ResultSet resul = sentencia.executeQuery("SELECT * FROM Mecanics;");
            while(resul.next()){
                System.out.println("Dni: "+resul.getString("dniMecanic"));
                System.out.println("Nom i cognom: "+resul.getString("nomCognomM"));
                System.out.println("Edat: "+resul.getString("edatM"));
                System.out.println("Telèfon: "+resul.getString("telM"));
                System.out.println("Adreça: "+resul.getString("adrecaM"));
                System.out.println("Ciutat: "+resul.getString("ciutatM"));
                System.out.println("Pais: "+resul.getString("paisM"));
                System.out.println("Email: "+resul.getString("emailM"));
                System.out.println("Permis de Conducció: "+resul.getString("permisConduccioM"));
                System.out.println("Punts: "+resul.getString("puntsM"));
                System.out.println("Salari: "+resul.getString("salari"));
                System.out.println("Seguretat Social: "+resul.getString("seguretatSocial"));
                System.out.println("Titulació: "+resul.getString("titulacio"));
                System.out.println("Anys en l'empresa: "+resul.getString("anysEmp"));
                System.out.println("-----------------------------------------------\n");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void mostrarCotxes(String dniMecanic){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "SELECT * FROM Mecanics WHERE dniMecanic = ?";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, dniMecanic);
            ResultSet resul = preparedStmt.executeQuery();
            while(resul.next()){
                System.out.println("Dni: "+resul.getString("dniMecanic"));
                System.out.println("Nom i cognom: "+resul.getString("nomCognomM"));
                System.out.println("Edat: "+resul.getString("edatM"));
                System.out.println("Telèfon: "+resul.getString("telM"));
                System.out.println("Adreça: "+resul.getString("adrecaM"));
                System.out.println("Ciutat: "+resul.getString("ciutatM"));
                System.out.println("Pais: "+resul.getString("paisM"));
                System.out.println("Email: "+resul.getString("emailM"));
                System.out.println("Permis de Conducció: "+resul.getString("permisConduccioM"));
                System.out.println("Punts: "+resul.getString("puntsM"));
                System.out.println("Salari: "+resul.getString("salari"));
                System.out.println("Seguretat Social: "+resul.getString("seguretatSocial"));
                System.out.println("Titulació: "+resul.getString("titulacio"));
                System.out.println("Anys en l'empresa: "+resul.getString("anysEmp"));
                System.out.println("-----------------------------------------------\n");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }

    /*ME HE QUEDADO AQUI*/
    public void inserirCotxe(String matricula, int numBastidor,String marca, String model, String color, int places, int numPortes, double maleter, String tCombustible){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "INSERT INTO COTXES (matricula, numBastidor, marca, model, color, places, numPortes, maleter, tCombustible) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, matricula);
            preparedStmt.setInt(2, numBastidor);
            preparedStmt.setString(3, marca);
            preparedStmt.setString(4, model);
            preparedStmt.setString(5, color);
            preparedStmt.setInt(6, places);
            preparedStmt.setInt(7, numPortes);
            preparedStmt.setDouble(8, maleter);
            preparedStmt.setString(9, tCombustible);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void modificarCotxe(Map<String,String> map, String matriculaCotxe){
        try{
            String query = "UPDATE Cotxes SET ";
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
                if(claves.get(i)=="numBastidor" || claves.get(i)=="places" || claves.get(i)=="numPortes"){
                    int valorParseado = Integer.parseInt(valores.get(j));
                    preparedStmt.setInt(i+1, valorParseado);
                }else if(claves.get(i)=="tCombustible"){
                    double valorParseadoaDouble =  Double.parseDouble(valores.get(j));
                    preparedStmt.setDouble(i+1, valorParseadoaDouble);
                }else{
                    preparedStmt.setString(i+1, valores.get(j));
                }
            }
            String lastElement = claves.get(claves.size() - 1);
            preparedStmt.setString(claves.indexOf(lastElement)+2, matriculaCotxe);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }
    public void eliminarCotxe(String matricula){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "DELETE FROM Cotxes WHERE matricula = ?;";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, matricula);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
}
