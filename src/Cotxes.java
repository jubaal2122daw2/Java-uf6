import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Cotxes {

    private String matricula;
    private int numBastidor;
    private String marca;
    private String model;
    private String color;
    private int places;
    private int numPortes;
    private double maleter;
    private String tCombustible;

    /*CONSTRUCTORES*/

    public Cotxes() {
    }

    public Cotxes(String matricula) {
        this.matricula = matricula;
    }

    public Cotxes(String matricula, int numBastidor, String marca, String model, String color, int places, int numPortes, double maleter, String tCombustible) {
        this.matricula = matricula;
        this.numBastidor = numBastidor;
        this.marca = marca;
        this.model = model;
        this.color = color;
        this.places = places;
        this.numPortes = numPortes;
        this.maleter = maleter;
        this.tCombustible = tCombustible;
    }

    /*GETTER*/

    public String getMatricula() {
        return matricula;
    }

    public int getNumBastidor() {
        return numBastidor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getPlaces() {
        return places;
    }

    public int getNumPortes() {
        return numPortes;
    }

    public double getMaleter() {
        return maleter;
    }

    public String gettCombustible() {
        return tCombustible;
    }

    public static void mostrarTots(Connexio connexio){
        try{
            Statement sentencia = connexio.getConexion().createStatement();
            ResultSet resul = sentencia.executeQuery("SELECT * FROM Cotxes;");
            while(resul.next()){
                System.out.println("Matrícula: "+resul.getString("matricula"));
                System.out.println("Nº Bastidor: "+resul.getString("numBastidor"));
                System.out.println("Marca: "+resul.getString("marca"));
                System.out.println("Model: "+resul.getString("model"));
                System.out.println("Color: "+resul.getString("color"));
                System.out.println("Places: "+resul.getString("places"));
                System.out.println("Nº Portes: "+resul.getString("numPortes"));
                System.out.println("Maleter (l): "+resul.getString("maleter"));
                System.out.println("Tipus de Combustible: "+resul.getString("tCombustible"));
                System.out.println("-----------------------------------------------\n");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void mostrarCotxes(Connexio connexio,String matricula){
        try{
            String query = "SELECT * FROM Cotxes WHERE matricula = ?";
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
            preparedStmt.setString(1, matricula);
            ResultSet resul = preparedStmt.executeQuery();
            while(resul.next()){
                System.out.println("Matrícula: "+resul.getString("matricula"));
                System.out.println("Nº Bastidor: "+resul.getString("numBastidor"));
                System.out.println("Marca: "+resul.getString("marca"));
                System.out.println("Model: "+resul.getString("model"));
                System.out.println("Color: "+resul.getString("color"));
                System.out.println("Places: "+resul.getString("places"));
                System.out.println("Nº Portes: "+resul.getString("numPortes"));
                System.out.println("Maleter (l): "+resul.getString("maleter"));
                System.out.println("Tipus de Combustible: "+resul.getString("tCombustible"));
                System.out.println("-----------------------------------------------\n");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void inserirCotxe(Connexio connexio,String matricula, int numBastidor,String marca, String model, String color, int places, int numPortes, double maleter, String tCombustible){
        try{
            String query = "INSERT INTO COTXES (matricula, numBastidor, marca, model, color, places, numPortes, maleter, tCombustible) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
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
    public void modificarCotxe(Connexio connexio,Map<String,String> map, String matriculaCotxe){
        try{
            String query = "UPDATE Cotxes SET ";
            ArrayList<String> claves = new ArrayList<String>();
            ArrayList<String> valores = new ArrayList<String>();

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
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
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
    public void eliminarCotxe(Connexio connexio,String matricula){
        try{
            String query = "DELETE FROM Cotxes WHERE matricula = ?;";
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
            preparedStmt.setString(1, matricula);
            preparedStmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
}
