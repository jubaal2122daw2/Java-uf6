import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Mecanics {
    private String dniMecanic;
    private String nomCognomM;
    private int edatM;
    private String telM;
    private String adrecaM;
    private String ciutatM;
    private String paisM;
    private String emailM;
    private String permisConduccioM;
    private int puntsM;
    private double salari;
    private int seguretatSocial;
    private String titulacio;
    private int anysEmp;

    /*CONSTRUCTORES*/

    public Mecanics() {
    }

    public Mecanics(String dniMecanic, String nomCognomM, int edatM, String telM, String adrecaM, String ciutatM, String paisM, String emailM, String permisConduccioM, int puntsM, double salari, int seguretatSocial, String titulacio, int anysEmp) {
        this.dniMecanic = dniMecanic;
        this.nomCognomM = nomCognomM;
        this.edatM = edatM;
        this.telM = telM;
        this.adrecaM = adrecaM;
        this.ciutatM = ciutatM;
        this.paisM = paisM;
        this.emailM = emailM;
        this.permisConduccioM = permisConduccioM;
        this.puntsM = puntsM;
        this.salari = salari;
        this.seguretatSocial = seguretatSocial;
        this.titulacio = titulacio;
        this.anysEmp = anysEmp;
    }

    public Mecanics(String dniMecanic) {
        this.dniMecanic = dniMecanic;
    }

    /*GETTER*/

    public String getDniMecanic() {
        return dniMecanic;
    }

    public String getNomCognomM() {
        return nomCognomM;
    }

    public int getEdatM() {
        return edatM;
    }

    public String getTelM() {
        return telM;
    }

    public String getAdrecaM() {
        return adrecaM;
    }

    public String getCiutatM() {
        return ciutatM;
    }

    public String getPaisM() {
        return paisM;
    }

    public String getEmailM() {
        return emailM;
    }

    public String getPermisConduccioM() {
        return permisConduccioM;
    }

    public int getPuntsM() {
        return puntsM;
    }

    public double getSalari() {
        return salari;
    }

    public int getSeguretatSocial() {
        return seguretatSocial;
    }

    public String getTitulacio() {
        return titulacio;
    }

    public int getAnysEmp() {
        return anysEmp;
    }

    public static void mostrarTots(Connexio connexio){
        try{
            Statement sentencia = connexio.getConexion().createStatement();
            ResultSet resul = sentencia.executeQuery("SELECT * FROM Mecanics;");
            while(resul.next()){
                System.out.println("Dni: "+resul.getString("dniMecanic"));
                System.out.println("Nom i cognom: "+resul.getString("nomCognomM"));
                System.out.println("Edat: "+resul.getString("edatM"));
                System.out.println("Tel??fon: "+resul.getString("telM"));
                System.out.println("Adre??a: "+resul.getString("adrecaM"));
                System.out.println("Ciutat: "+resul.getString("ciutatM"));
                System.out.println("Pais: "+resul.getString("paisM"));
                System.out.println("Email: "+resul.getString("emailM"));
                System.out.println("Permis de Conducci??: "+resul.getString("permisConduccioM"));
                System.out.println("Punts: "+resul.getString("puntsM"));
                System.out.println("Salari: "+resul.getString("salari"));
                System.out.println("Seguretat Social: "+resul.getString("seguretatSocial"));
                System.out.println("Titulaci??: "+resul.getString("titulacio"));
                System.out.println("Anys en l'empresa: "+resul.getString("anysEmp"));
                System.out.println("-----------------------------------------------\n");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
    public void mostrarMecanics(Connexio connexio,String dniMecanic){
        try{
            String query = "SELECT * FROM Mecanics WHERE dniMecanic = ?";
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
            preparedStmt.setString(1, dniMecanic);
            ResultSet resul = preparedStmt.executeQuery();
            boolean val = resul.next();
            if(!val){
                System.err.println("No existeix aquest dni");
            }
            while(val){
                System.out.println("Dni: "+resul.getString("dniMecanic"));
                System.out.println("Nom i cognom: "+resul.getString("nomCognomM"));
                System.out.println("Edat: "+resul.getString("edatM"));
                System.out.println("Tel??fon: "+resul.getString("telM"));
                System.out.println("Adre??a: "+resul.getString("adrecaM"));
                System.out.println("Ciutat: "+resul.getString("ciutatM"));
                System.out.println("Pais: "+resul.getString("paisM"));
                System.out.println("Email: "+resul.getString("emailM"));
                System.out.println("Permis de Conducci??: "+resul.getString("permisConduccioM"));
                System.out.println("Punts: "+resul.getString("puntsM"));
                System.out.println("Salari: "+resul.getString("salari"));
                System.out.println("Seguretat Social: "+resul.getString("seguretatSocial"));
                System.out.println("Titulaci??: "+resul.getString("titulacio"));
                System.out.println("Anys en l'empresa: "+resul.getString("anysEmp"));
                System.out.println("-----------------------------------------------\n");
                break;
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }

    public void inserirMecanic(Connexio connexio,String dniMecanic, String nomCognomM, int edatM, String telM, String adrecaM, String ciutatM, String paisM, String emailM, String permisConduccioM, int puntsM, double salari, int seguretatSocial, String titulacio, int anysEmp){
        try{
            String query = "INSERT INTO MECANICS (dniMecanic, nomCognomM, edatM, telM, adrecaM, ciutatM, paisM, emailM, permisConduccioM, puntsM, salari, seguretatSocial, titulacio, anysEmp) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
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
    public void modificarMecanic(Connexio connexio,Map<String,String> map, String dniMecanic){
        try{
            String query = "UPDATE Mecanics SET ";
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
                    query = query + c + " = ? WHERE dniMecanic = ?;";
                }
            }
            System.out.println(query);
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
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
            int val =  preparedStmt.executeUpdate();
            if(val == 0){
                System.err.println("No existeix el dni escollit");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }
    public void eliminarMecanic(Connexio connexio,String dniMecanic){
        try{
            String query = "DELETE FROM Mecanics WHERE dniMecanic = ?;";
            PreparedStatement preparedStmt = connexio.getConexion().prepareStatement(query);
            preparedStmt.setString(1, dniMecanic);
            int val =  preparedStmt.executeUpdate();
            if(val == 0){
                System.err.println("No existeix el dni escollit");
            }
        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }

    }
}
