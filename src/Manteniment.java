import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Map;

public class Manteniment {

    private String dniMecanic;
    private String  matricula;
    private String dataInici;
    private String dataFi;

    /*CONSTRUCTORES*/
    public Manteniment() {
    }

    public Manteniment(String dniMecanic) {
        this.dniMecanic = dniMecanic;
    }


    public Manteniment(String dniMecanic, String matricula, String dataInici, String dataFi) {
        this.dniMecanic = dniMecanic;
        this.matricula = matricula;
        this.dataInici = dataInici;
        this.dataFi = dataFi;
    }

    public Manteniment(String dniMecanic, String matricula) {
        this.dniMecanic = dniMecanic;
        this.matricula = matricula;
    }

    /*GETTER*/

    public String getDniMecanic() {
        return dniMecanic;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getDataInici() {
        return dataInici;
    }

    public String getDataFi() {
        return dataFi;
    }

    public static void mostrarTots(Connexio connexio){
        try{
            Statement sentencia = connexio.getConexion().createStatement();
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
    public void mostrarManteniment(Connexio connexio,String valor){
        try{
            String query = "SELECT * FROM Manteniment WHERE dniMecanic = ?";
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
            preparedStmt.setString(1, valor);
            ResultSet resul = preparedStmt.executeQuery();
            boolean val = resul.next();
            if(!val){
                System.err.println("No existeix aquest dni");
            }
            while(val){
                System.out.println("Dni Mecànic: "+resul.getString("dniMecanic"));
                System.out.println("Matrícula: "+resul.getString("matricula"));
                System.out.println("Data Inici Manteniment: "+resul.getString("dataInici"));
                System.out.println("Data Fi Manteniment: "+resul.getString("dataFi"));
                System.out.println("-----------------------------------------------\n");
                break;
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }

    public void inserirManteniment(Connexio connexio,String dni, String matricula, String dataInici, String dataFi){
        try{
            String query = "INSERT INTO MANTENIMENT (dniMecanic, matricula, dataInici, dataFi) values (?, ?, STR_TO_DATE(?,'%d-%m-%Y'), STR_TO_DATE(?,'%d-%m-%Y'));";
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
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
    public void modificarManteniment(Connexio connexio,Map<String,String> map, String matricula, String dni){
        try{
            String query = "UPDATE Manteniment SET ";
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
                    query = query + c + " = ? WHERE matricula = ? and dniMecanic = ?;";
                }
            }
            System.out.println(query);
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
            for(int i=0, j=0 ;i < claves.size() && j < valores.size(); i++,j++){
                if(claves.get(i)=="dataInici" || claves.get(i)=="dataFi"){
                    //String valorAFecha = "STR_TO_DATE('"+valores.get(j)+"','%d-%m-%Y')";
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = formatter.parse(valores.get(j));
                    //System.out.println("FECHA FORMATADA"+ date);
                    preparedStmt.setDate(i+1, new java.sql.Date(date.getTime()));
                }else{
                    preparedStmt.setString(i+1, valores.get(j));
                }
            }
            String lastElement = claves.get(claves.size() - 1);
            preparedStmt.setString(claves.indexOf(lastElement)+2, matricula);
            preparedStmt.setString(claves.indexOf(lastElement)+3, dni);
            int val =  preparedStmt.executeUpdate();
            if(val == 0){
                System.err.println("No existeix la matricula o el dni escollida en el manteniment");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }
    public void eliminarManteniment(Connexio connexio,String matricula, String dni){
        try{
            String query = "DELETE FROM Manteniment WHERE matricula = ? and dniMecanic = ?;";
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
            preparedStmt.setString(1, matricula);
            preparedStmt.setString(2, dni);
            int val =  preparedStmt.executeUpdate();
            if(val == 0){
                System.err.println("No existeix la matricula o el dni escollida en el manteniment");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }

}
