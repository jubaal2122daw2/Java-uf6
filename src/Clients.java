import java.sql.*;

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
    public void modificarCliente(String tel, String dni, String nomCognom){
        try{
            String miDriver="com.mysql.cj.jdbc.Driver";
            String miUrl = "jdbc:mysql://localhost/carsRental";
            Class.forName(miDriver);
            Connection conexion = DriverManager.getConnection(miUrl, "root", "admin");
            String query = "UPDATE Clients SET telefon = ?, nomCognom = ? WHERE dni = ?;";
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, tel);
            preparedStmt.setString(2, nomCognom);
            preparedStmt.setString(3, dni);
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
