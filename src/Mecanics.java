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
    public void mostrarMecanics(String dniMecanic){
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

    public void inserirMecanic(String dniMecanic, String nomCognomM, int edatM, String telM, String adrecaM, String ciutatM, String paisM, String emailM, String permisConduccioM, int puntsM, double salari, int seguretatSocial, String titulacio, int anysEmp){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "INSERT INTO MECANICS (dniMecanic, nomCognomM, edatM, telM, adrecaM, ciutatM, paisM, emailM, permisConduccioM, puntsM, salari, seguretatSocial, titulacio, anysEmp) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, dniMecanic);
            preparedStmt.setString(2, nomCognomM);
            preparedStmt.setInt(3, edatM);
            preparedStmt.setString(4, telM);
            preparedStmt.setString(5, adrecaM);
            preparedStmt.setString(6, ciutatM);
            preparedStmt.setString(7, paisM);
            preparedStmt.setString(8, emailM);
            preparedStmt.setString(9, permisConduccioM);
            preparedStmt.setInt(10, puntsM);
            preparedStmt.setDouble(11, salari);
            preparedStmt.setInt(12, seguretatSocial);
            preparedStmt.setString(13, titulacio);
            preparedStmt.setInt(14, anysEmp);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void modificarMecanic(Map<String,String> map, String dniMecanic){
        try{
            String query = "UPDATE Mecanics SET ";
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
                    query = query + c + " = ? WHERE dniMecanic = ?;";
                }
            }
            System.out.println(query);
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            for(int i=0, j=0 ;i < claves.size() && j < valores.size(); i++,j++){
                if(claves.get(i)=="edatM" || claves.get(i)=="puntsM" || claves.get(i)=="seguretatSocial" || claves.get(i)=="anysEmp"){
                    int valorParseado = Integer.parseInt(valores.get(j));
                    preparedStmt.setInt(i+1, valorParseado);
                }else if(claves.get(i)=="salari"){
                    double valorParseadoaDouble =  Double.parseDouble(valores.get(j));
                    preparedStmt.setDouble(i+1, valorParseadoaDouble);
                }else{
                    preparedStmt.setString(i+1, valores.get(j));
                }
            }
            String lastElement = claves.get(claves.size() - 1);
            preparedStmt.setString(claves.indexOf(lastElement)+2, dniMecanic);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }
    public void eliminarMecanic(String dniMecanic){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "DELETE FROM Cotxes WHERE dniMecanic = ?;";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, dniMecanic);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
}
