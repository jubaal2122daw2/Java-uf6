import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Lloguers {
    private String dni;
    private String matricula;
    private int dies;
    private double preu;
    private String llocDevolucio;
    private boolean depositPle;
    private String tAsseguranca;

    /*CONSTRUCTORS*/
    public Lloguers() {
    }

    public Lloguers(String matricula) {
        this.matricula = matricula;
    }

    public Lloguers(String dni, String matricula, int dies, double preu, String llocDevolucio, boolean depositPle, String tAsseguranca) {
        this.dni = dni;
        this.matricula = matricula;
        this.dies = dies;
        this.preu = preu;
        this.llocDevolucio = llocDevolucio;
        this.depositPle = depositPle;
        this.tAsseguranca = tAsseguranca;
    }

    /*GETTER*/

    public String getDni() {
        return dni;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getDies() {
        return dies;
    }

    public double getPreu() {
        return preu;
    }

    public String getLlocDevolucio() {
        return llocDevolucio;
    }

    public boolean isDepositPle() {
        return depositPle;
    }

    public String gettAsseguranca() {
        return tAsseguranca;
    }

    public static void mostrarTots(Connexio connexio){
        try{
            Statement sentencia = connexio.getConexion().createStatement();
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
    public void mostrarLloguers(Connexio connexio, String valor){
        try{
            String canviBoolean = "";
            String query = "SELECT * FROM Lloguer WHERE dni = ? or matricula= ?";
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
            preparedStmt.setString(1, valor);
            preparedStmt.setString(2, valor);
            ResultSet resul = preparedStmt.executeQuery();
            boolean val = resul.next();
            if(!val){
                System.err.println("No existeix aquest dni o matricula");
            }
            while(val){
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
                break;
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }

    public void inserirLloguer(Connexio connexio, String dni, String matricula, int dies, double preu, String llocDevolucio, boolean depositPle, String tAsseguranca){
        try{
            String query = "INSERT INTO LLOGUER (dni, matricula, dies, preu, llocDevolucio, depositPle, tAsseguranca) values (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
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
    public void modificarLloguer(Connexio connexio, Map<String,String> map, String dni){
        try{
            String query = "UPDATE Lloguer SET ";
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
            //System.out.println(query);
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
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
            int val =  preparedStmt.executeUpdate();
            if(val == 0){
                System.err.println("No existeix la matricula escollida en el lloguer");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }
    public void eliminarLloguer(Connexio connexio, String matricula){
        try{
            String query = "DELETE FROM Lloguer WHERE matricula = ?;";
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
            preparedStmt.setString(1, matricula);
            int val =  preparedStmt.executeUpdate();
            if(val == 0){
                System.err.println("No existeix la matricula escollida en el lloguer");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
}
