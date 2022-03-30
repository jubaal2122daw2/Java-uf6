import java.security.KeyStore;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Clients {

    public void mostrarTots(){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            Statement sentencia = conexion.createStatement();
            ResultSet resul = sentencia.executeQuery("SELECT * FROM Clients;");
            while(resul.next()){
                System.out.println("Nom i cognom: "+resul.getString("nomCognom"));
                System.out.println("DNI: "+resul.getString("dni"));
                System.out.println("Edat: "+resul.getString("edat"));
                System.out.println("Telèfon: "+resul.getString("telefon"));
                System.out.println("Adreça: "+resul.getString("adreca"));
                System.out.println("Ciutat: "+resul.getString("ciutat"));
                System.out.println("Pais: "+resul.getString("pais"));
                System.out.println("Email: "+resul.getString("email"));
                System.out.println("Permís conducció: "+resul.getString("permisConduccio"));
                System.out.println("Punts actuals: "+resul.getString("punts"));
                System.out.println("-----------------------------------------------\n");
            }//fin del while
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void mostrarClient(String dni){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "SELECT * FROM Clients WHERE dni = ?";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, dni);
            ResultSet resul = preparedStmt.executeQuery();
            while(resul.next()){
                System.out.println("Nom i cognom: "+resul.getString("nomCognom"));
                System.out.println("DNI: "+resul.getString("dni"));
                System.out.println("Edat: "+resul.getString("edat"));
                System.out.println("Telèfon: "+resul.getString("telefon"));
                System.out.println("Adreça: "+resul.getString("adreca"));
                System.out.println("Ciutat: "+resul.getString("ciutat"));
                System.out.println("Pais: "+resul.getString("pais"));
                System.out.println("Email: "+resul.getString("email"));
                System.out.println("Permís conducció: "+resul.getString("permisConduccio"));
                System.out.println("Punts actuals: "+resul.getString("punts"));
                System.out.println("-----------------------------------------------\n");
            }


        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void inserirClient(String dni, String nomCognom,int edat, String telefon, String adreca, String ciutat, String pais, String email, String permisConduccio, int punts){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "INSERT INTO CLIENTS (dni, nomCognom, edat, telefon, adreca, ciutat, pais, email, permisConduccio, punts) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, dni);
            preparedStmt.setString(2, nomCognom);
            preparedStmt.setInt(3, edat);
            preparedStmt.setString(4, telefon);
            preparedStmt.setString(5, adreca);
            preparedStmt.setString(6, ciutat);
            preparedStmt.setString(7, pais);
            preparedStmt.setString(8, email);
            preparedStmt.setString(9, permisConduccio);
            preparedStmt.setInt(10, punts);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void modificarCliente(Map<String,String> map, String dniCliente){
        try{
            String query = "UPDATE Clients SET ";
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
                }else if (c == last){
                    query = query + c + " = ? WHERE dni = ?;";
                }else{
                    query = query + c + " = ? WHERE dni = ?;";
                }
            }

            System.out.println(query);
            //String query = "UPDATE Clients SET telefon = ?, nomCognom = ? WHERE dni = ?;";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            for(int i=0, j=0 ;i < claves.size() && j < valores.size(); i++,j++){
                if(claves.get(i)=="edat" || claves.get(i)=="punts"){
                    int valorParseado = Integer.parseInt(valores.get(j));
                    preparedStmt.setInt(i+1, valorParseado);
                }else{
                    preparedStmt.setString(i+1, valores.get(j));
                }
                //System.out.println("Clave: "+claves.get(i)+" Index: "+(i+1)+" Valor: "+valores.get(j));
            }
            String lastElement = claves.get(claves.size() - 1);
            //System.out.println("Ultimo index del array claves -->"+claves.indexOf(lastElement));
            preparedStmt.setString(claves.indexOf(lastElement)+2, dniCliente);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void eliminarClient(String dni){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "DELETE FROM Clients WHERE dni = ?;";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, dni);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
}
