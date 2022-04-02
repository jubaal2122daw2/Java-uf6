import java.awt.event.MouseWheelEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Manteniment manteniment = new Manteniment();
        Connexio connexio = new Connexio("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost/carsRental");

        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while(!salir){
            System.out.println("--------CARS RENTAL--------");
            System.out.println("1.Gestionar Clients");
            System.out.println("2.Gestionar Cotxes");
            System.out.println("3.Gestionar Mecànics");
            System.out.println("4.Gestionar Lloguers");
            System.out.println("5.Gestionar Manteniment");
            System.out.println("6.Veure el registre de lloguers");
            System.out.println("0.Salir");
            System.out.print("Escriu una de les opcions --> ");
            opcion=sn.nextInt();
            switch (opcion){
                case 1:
                    menuClient(connexio, sn);
                    break;
                case 2:
                    menuCotxes(connexio,sn);
                    break;
                case 3:
                    menuMecanics(connexio,sn);
                    break;
                case 4:
                    menuLloguers(connexio, sn);
                    break;
                case 5:
                    menuManteniment(connexio, sn);
                    break;
                case 6:
                    infoRegistre(connexio);
                    break;
                case 0:
                    salir=true;
                    break;
            }
        }
    }
    public static void menuClient(Connexio connexio, Scanner sn) {
        sn.nextLine(); //refresca scanner
        boolean salir = false;
        int opcionMenu;
        while(!salir){
            System.out.println("----Benvingut a la secció de Clients, què vol fer?----");
            System.out.println("1.Mostrar tots els clients");
            System.out.println("2.Mostrar per dni");
            System.out.println("3.Inserir");
            System.out.println("4.Actualitzar");
            System.out.println("5.Esborrar per dni");
            System.out.println("0.Tornar al menú principal");
            System.out.print("Escriu una de les opcions --> ");

            opcionMenu=sn.nextInt();
            switch (opcionMenu){
                case 1:
                    System.out.print("\n-------------------MOSTRAR DADES-------------------\n");
                    Clients.mostrarTots(connexio);
                    break;
                case 2:
                    System.out.print("\n-------------------MOSTRAR PER DNI-------------------\n");
                    String dniC;
                    System.out.print("Introdueix el dni del client que vols buscar: ");
                    dniC=sn.next();
                    Clients c = new Clients(dniC);
                    System.out.println("Mostrar client pel dni...");
                    c.mostrarClient(connexio, c.getDni());
                    break;
                case 3:
                    System.out.print("\n-------------------INSERIR-------------------\n");
                    String dniInsertar,nom,tel,adreca,ciutat,pais,email,permis;
                    int edat, punts;
                    System.out.println("Introdueix les dades del client a crear ");
                    sn.nextLine(); //refresca el scanner
                    System.out.print("DNI: "); dniInsertar=sn.nextLine();
                    System.out.print("Nom y Cognom: "); nom=sn.nextLine();
                    System.out.print("Edat: "); edat=sn.nextInt();
                    sn.nextLine();
                    System.out.print("Telèfon: "); tel=sn.nextLine();
                    System.out.print("Adreça: "); adreca=sn.nextLine();
                    System.out.print("Ciutat: "); ciutat=sn.nextLine();
                    System.out.print("Pais: "); pais=sn.nextLine();
                    System.out.print("Email: "); email=sn.nextLine();
                    System.out.print("Permis: "); permis=sn.nextLine();
                    System.out.print("Punts: "); punts=sn.nextInt();
                    sn.nextLine();
                    System.out.println("Inserint el client.....");
                    Clients c1 = new Clients(nom, dniInsertar, edat, tel, adreca, ciutat, pais, email, permis, punts);
                    c1.inserirClient(connexio,c1.getDni(),c1.getNomCognom(), c1.getEdat(), c1.getTelefon(), c1.getAdreca(), c1.getCiutat(), c1.getPais(), c1.getEmail(), c1.getPermisConduccio(), c1.getPunts());
                    break;
                case 4:
                    System.out.print("\n-------------------ACTUALITZAR-------------------\n");
                    String [] columnas = new String[]{"nomCognom","dni","edat","telefon","adreca","ciutat","pais","email","permisConduccio","punts"};
                    Map<String,String> modificarColumnas = new TreeMap<>();
                    String dniModificar;
                    sn.nextLine(); //refresca scanner
                    for(String columna :columnas){
                        String clave;
                        String valor;
                        System.out.print("Vols modificar "+columna+"? (s/n) ");
                        clave=sn.nextLine();
                        if (clave.equalsIgnoreCase("s")){
                            System.out.print("Quin nou valor per "+columna+"? ");
                            valor=sn.nextLine();
                            modificarColumnas.put(columna, valor);
                        }
                    }
                    System.out.print("Posa el dni del client que vols modificar: ");
                    dniModificar=sn.next();
                    Clients c3 = new Clients(dniModificar);
                    c3.modificarCliente(connexio,modificarColumnas,c3.getDni());
                    System.out.println("Actualizar...");
                    break;
                case 5:
                    System.out.print("\n-------------------ESBORRAR-------------------\n");
                    String dniEsborrar;
                    System.out.print("Introdueix el dni del client que vols esborrar: ");
                    dniEsborrar=sn.next();
                    System.out.println("Esborrant client...");
                    Clients c2 = new Clients(dniEsborrar);
                    c2.eliminarClient(connexio,c2.getDni());
                    break;
                case 0:
                    salir=true;
                    break;
                default:
                    System.out.println("Únicament nombres del 1 al 6");
            }
        }
    }

    public static void menuCotxes(Connexio connexio,Scanner sn){
        sn.nextLine(); //refresca scanner
        boolean salir = false;
        int opcionMenu;
        while(!salir){
            System.out.println("----Benvingut a la secció de Cotxes, què vol fer?----");
            System.out.println("1.Mostrar tots els cotxes");
            System.out.println("2.Mostrar per matricula");
            System.out.println("3.Inserir");
            System.out.println("4.Actualitzar");
            System.out.println("5.Esborrar per matricula");
            System.out.println("0.Tornar al menú principal");
            System.out.print("Escriu una de les opcions --> ");

            opcionMenu=sn.nextInt();
            switch (opcionMenu){
                case 1:
                    System.out.print("\n-------------------MOSTRAR DADES-------------------\n");
                    Cotxes.mostrarTots(connexio);
                    break;
                case 2:
                    System.out.print("\n-------------------MOSTRAR DADES PER MATRICULA-------------------\n");
                    String matricula;
                    System.out.print("Introdueix la matricula del cotxe que vols buscar: ");
                    matricula=sn.next();
                    System.out.println("Mostrar cotxe per la matricula...");
                    Cotxes c = new Cotxes(matricula);
                    c.mostrarCotxes(connexio,c.getMatricula());
                    break;
                case 3:
                    System.out.print("\n-------------------INSERIR-------------------\n");
                    String matriculaInsertar,marca,model,color,tCombustible;
                    int numBastidor, places, numPortes;
                    double maleter;
                    System.out.println("Introdueix les dades del cotxe a crear ");
                    sn.nextLine(); //refresca el scanner
                    System.out.print("Matrícula: "); matriculaInsertar=sn.nextLine();
                    System.out.print("Nº Bastidor: "); numBastidor=sn.nextInt();
                    sn.nextLine();
                    System.out.print("Marca: "); marca=sn.nextLine();
                    System.out.print("Model: "); model=sn.nextLine();
                    System.out.print("Color: "); color=sn.nextLine();
                    System.out.print("Places: "); places=sn.nextInt();
                    sn.nextLine();
                    System.out.print("Nº Portes: "); numPortes=sn.nextInt();
                    sn.nextLine();
                    System.out.print("Maleter (l): "); maleter=sn.nextDouble();
                    sn.nextLine();
                    System.out.print("Tipus de Combustible: "); tCombustible=sn.nextLine();
                    System.out.println("Inserint el cotxe.....");
                    Cotxes c1 = new Cotxes(matriculaInsertar, numBastidor, marca, model, color, places, numPortes, maleter, tCombustible);
                    c1.inserirCotxe(connexio,c1.getMatricula(),c1.getNumBastidor(), c1.getMarca(), c1.getModel(), c1.getColor(), c1.getPlaces(), c1.getNumPortes(), c1.getMaleter(), c1.gettCombustible());
                    break;
                case 4:
                    System.out.print("\n-------------------ACTUALITZAR-------------------\n");
                    String [] columnas = new String[]{"matricula","numBastidor","marca","model","color","places","numPortes","maleter","tCombustible"};
                    Map<String,String> modificarColumnas = new TreeMap<>();
                    String matriculaModificar;
                    sn.nextLine(); //refresca scanner
                    for(String columna :columnas){
                        String clave;
                        String valor;
                        System.out.print("Vols modificar "+columna+"? (s/n) ");
                        clave=sn.nextLine();
                        if (clave.equalsIgnoreCase("s")){
                            System.out.print("Quin nou valor per "+columna+"? ");
                            valor=sn.nextLine();
                            modificarColumnas.put(columna, valor);
                        }
                    }
                    System.out.print("Posa la matricula del cotxe que vols modificar: ");
                    matriculaModificar=sn.next();
                    Cotxes c2 = new Cotxes(matriculaModificar);
                    c2.modificarCotxe(connexio,modificarColumnas,c2.getMatricula());
                    System.out.println("Actualitzant...");
                    break;
                case 5:
                    System.out.print("\n-------------------ESBORRAR-------------------\n");
                    String matriculaEsborrar;
                    System.out.print("Introdueix la matricula del cotxe que vols esborrar: ");
                    matriculaEsborrar=sn.next();
                    System.out.println("Esborrant cotxe...");
                    Cotxes c3 = new Cotxes(matriculaEsborrar);
                    c3.eliminarCotxe(connexio,c3.getMatricula());
                    break;
                case 0:
                    salir=true;
                    break;
                default:
                    System.out.println("Únicament nombres del 1 al 6");
            }

        }
    }

    public static void menuMecanics(Connexio connexio, Scanner sn) {
        sn.nextLine(); //refresca scanner
        boolean salir = false;
        int opcionMenu;
        while(!salir){
            System.out.println("----Benvingut a la secció de Mecànics, què vol fer?----");
            System.out.println("1.Mostrar tots els mecànics");
            System.out.println("2.Mostrar per dni");
            System.out.println("3.Inserir");
            System.out.println("4.Actualitzar");
            System.out.println("5.Esborrar per dni");
            System.out.println("0.Tornar al menú principal");
            System.out.print("Escriu una de les opcions --> ");

            opcionMenu=sn.nextInt();
            switch (opcionMenu){
                case 1:
                    System.out.print("\n-------------------MOSTRAR DADES-------------------\n");
                    Mecanics.mostrarTots(connexio);

                    break;
                case 2:
                    System.out.print("\n-------------------MOSTRAR PER DNI-------------------\n");
                    String dni;
                    System.out.print("Introdueix el dni del mecanic que vols buscar: ");
                    dni=sn.next();
                    System.out.println("Mostrar mecanic pel dni...");
                    Mecanics m1 = new Mecanics(dni);
                    m1.mostrarMecanics(connexio,m1.getDniMecanic());
                    break;
                case 3:
                    System.out.print("\n-------------------INSERIR-------------------\n");
                    String dniInsertar,nomCognomM,telM,adrecaM,ciutatM,paisM,emailM,permisConduccioM, titulacio;
                    int edatM, puntsM, seguretatSocial, anysEmp;
                    double salari;
                    System.out.println("Introdueix les dades del mecànic a crear ");
                    sn.nextLine(); //refresca el scanner
                    System.out.print("DNI: "); dniInsertar=sn.nextLine();
                    System.out.print("Nom y Cognom: "); nomCognomM=sn.nextLine();
                    System.out.print("Edat: "); edatM=sn.nextInt();
                    sn.nextLine();
                    System.out.print("Telèfon: "); telM=sn.nextLine();
                    System.out.print("Adreça: "); adrecaM=sn.nextLine();
                    System.out.print("Ciutat: "); ciutatM=sn.nextLine();
                    System.out.print("Pais: "); paisM=sn.nextLine();
                    System.out.print("Email: "); emailM=sn.nextLine();
                    System.out.print("Permis: "); permisConduccioM=sn.nextLine();
                    System.out.print("Punts: "); puntsM=sn.nextInt();
                    sn.nextLine();
                    System.out.print("Salari: "); salari=sn.nextDouble();
                    sn.nextLine();
                    System.out.print("Nº SeguretatSocial: "); seguretatSocial=sn.nextInt();
                    sn.nextLine();
                    System.out.print("Titulació: "); titulacio=sn.nextLine();
                    System.out.print("Anys Empresa: "); anysEmp=sn.nextInt();
                    sn.nextLine();
                    System.out.println("Inserint el mecànic.....");
                    Mecanics m2 = new Mecanics(dniInsertar,nomCognomM, edatM, telM, adrecaM, ciutatM, paisM, emailM, permisConduccioM, puntsM, salari, seguretatSocial, titulacio, anysEmp);
                    m2.inserirMecanic(connexio,m2.getDniMecanic(),m2.getNomCognomM(), m2.getEdatM(), m2.getTelM(), m2.getAdrecaM(), m2.getCiutatM(), m2.getPaisM(), m2.getEmailM(), m2.getPermisConduccioM(), m2.getPuntsM(), m2.getSalari(), m2.getSeguretatSocial(), m2.getTitulacio(), m2.getAnysEmp());
                    break;
                case 4:
                    System.out.print("\n-------------------ACTUALITZAR-------------------\n");
                    String [] columnas = new String[]{"nomCognomM","dniMecanic","edatM","telM","adrecaM","ciutatM","paisM","emailM","permisConduccioM","puntsM", "salari", "seguretatSocial","titulacio", "anysEmp"};
                    Map<String,String> modificarColumnas = new TreeMap<>();
                    String dniModificar;
                    sn.nextLine(); //refresca scanner
                    for(String columna :columnas){
                        String clave;
                        String valor;
                        System.out.print("Vols modificar "+columna+"? (s/n) ");
                        clave=sn.nextLine();
                        if (clave.equalsIgnoreCase("s")){
                            System.out.print("Quin nou valor per "+columna+"? ");
                            valor=sn.nextLine();
                            modificarColumnas.put(columna, valor);
                        }
                    }
                    System.out.print("Posa el dni del mecànic que vols modificar: ");
                    dniModificar=sn.next();
                    Mecanics m3 = new Mecanics(dniModificar);
                    m3.modificarMecanic(connexio,modificarColumnas,m3.getDniMecanic());
                    System.out.println("Actualizar...");
                    break;
                case 5:
                    System.out.print("\n-------------------ESBORRAR-------------------\n");
                    String dniEsborrar;
                    System.out.print("Introdueix el dni del mecànic que vols esborrar: ");
                    dniEsborrar=sn.next();
                    System.out.println("Esborrant mecànic...");
                    Mecanics m4 = new Mecanics(dniEsborrar);
                    m4.eliminarMecanic(connexio,m4.getDniMecanic());
                    break;
                case 0:
                    salir=true;
                    break;
                default:
                    System.out.println("Únicament nombres del 1 al 6");
            }

        }

    }

    public static void menuLloguers(Connexio connexio, Scanner sn){
        sn.nextLine(); //refresca scanner
        boolean salir = false;
        int opcionMenu;
        while(!salir){
            System.out.println("----Benvingut a la secció de Lloguers, què vol fer?----");
            System.out.println("1.Mostrar tots els lloguers");
            System.out.println("2.Cercar per matricula o dni");
            System.out.println("3.Inserir un nou lloguer");
            System.out.println("4.Actualitzar un lloguer per la matricula del cotxe");
            System.out.println("5.Esborrar per matricula");
            System.out.println("0.Tornar al menú principal");
            System.out.print("Escriu una de les opcions --> ");

            opcionMenu=sn.nextInt();
            switch (opcionMenu){
                case 1:
                    System.out.print("\n-------------------MOSTRAR DADES-------------------\n");
                    Lloguers.mostrarTots(connexio);
                    break;
                case 2:
                    System.out.print("\n-------------------MOSTRAR DADES PER MATRICULA O DNI-------------------\n");
                    String input;
                    System.out.print("Introdueix la matricula o el dni del lloguer que vols buscar: ");
                    input=sn.next();
                    System.out.println("Mostrar cotxe per la matricula...");
                    Lloguers llo1 = new Lloguers();
                    llo1.mostrarLloguers(connexio,input);
                    break;
                case 3:
                    System.out.print("\n------------------INSERIR-------------------\n");
                    String dniInsertar,matriculaInsertar,llocDevolucio,tAsseguranca, depositPle;
                    int dies;
                    double preu;
                    boolean toBoolean;
                    System.out.println("Introdueix les dades del lloguer a crear ");
                    sn.nextLine(); //refresca el scanner
                    System.out.print("Dni Client: "); dniInsertar=sn.nextLine();
                    System.out.print("Matrícula: "); matriculaInsertar=sn.nextLine();
                    System.out.print("Dies: "); dies=sn.nextInt();
                    sn.nextLine();
                    System.out.print("Preu: "); preu=sn.nextDouble();
                    sn.nextLine();
                    System.out.print("Lloc Devolució: "); llocDevolucio=sn.nextLine();
                    System.out.print("Retorn de depòsit ple (S/N): "); depositPle=sn.nextLine();
                    if(depositPle.equalsIgnoreCase("S")){
                        depositPle = "true";
                        toBoolean = Boolean.parseBoolean(depositPle);
                    }else{
                        depositPle = "false";
                        toBoolean = Boolean.parseBoolean(depositPle);
                    }
                    System.out.print("Tipus Assegurança (Amb Franquisia [S], Sense Franquisia [N]): "); tAsseguranca=sn.nextLine();
                    if (tAsseguranca.equalsIgnoreCase("S")){
                        tAsseguranca = "Amb Franquisia";
                    }else{
                        tAsseguranca = "Sense Franquisia";
                    }
                    System.out.println("Inserint el lloguer.....");
                    Lloguers llo2 = new Lloguers(dniInsertar,matriculaInsertar, dies, preu, llocDevolucio, toBoolean, tAsseguranca);
                    llo2.inserirLloguer(connexio,llo2.getDni(),llo2.getMatricula(), llo2.getDies(), llo2.getPreu(), llo2.getLlocDevolucio(), llo2.isDepositPle(), llo2.gettAsseguranca());
                    break;
                case 4:
                    System.out.print("\n-------------------ACTUALITZAR-------------------\n");
                    String [] columnas = new String[]{"dni","dies","preu","llocDevolucio","depositPle","tAsseguranca"};
                    Map<String,String> modificarColumnas = new TreeMap<>();
                    String dniModificar;
                    sn.nextLine(); //refresca scanner
                    for(String columna :columnas){
                        String clave;
                        String valor="";
                        System.out.print("Vols modificar "+columna+"? (s/n) ");
                        clave=sn.nextLine();
                        if (clave.equalsIgnoreCase("s")){
                            System.out.print("Quin nou valor per "+columna+"? ");
                            if(columna.equalsIgnoreCase("tAsseguranca")){
                                System.out.print("\nTipus Assegurança:\n\tAmb Franquisia [S], Sense Franquisia [N]: ");
                                valor=sn.nextLine();
                                if(valor.equalsIgnoreCase("S")){
                                    valor = "Amb Franquisia";
                                }else{
                                    valor = "Sense Franquisia";
                                }
                            }else if(columna.equalsIgnoreCase("depositPle")){
                                System.out.print("\n\tRetorn de depòsit ple (S/N): ");
                                valor=sn.nextLine();
                                if(valor.equalsIgnoreCase("S")){
                                    valor = "true";
                                }else{
                                    valor = "false";
                                }
                            }else{
                                valor=sn.nextLine();
                            }
                            modificarColumnas.put(columna, valor);
                        }
                    }
                    System.out.print("Posa la matricula del cotxe per modificar el seu lloguer: ");
                    dniModificar=sn.next();
                    Lloguers llo3 = new Lloguers(dniModificar);
                    llo3.modificarLloguer(connexio,modificarColumnas,llo3.getDni());
                    System.out.println("Actualitzant...");
                    break;
                case 5:
                    System.out.print("\n-------------------ESBORRAR-------------------\n");
                    String matriculaEsborrar;
                    System.out.print("Introdueix la matricula del cotxe per esborrar el seu lloguer: ");
                    matriculaEsborrar=sn.next();
                    System.out.println("Esborrant cotxe...");
                    Lloguers llo4 = new Lloguers(matriculaEsborrar);
                    llo4.eliminarLloguer(connexio, llo4.getMatricula());
                    break;
                case 0:
                    salir=true;
                    break;
                default:
                    System.out.println("Únicament nombres del 1 al 6");
            }

        }
    }

    public static void menuManteniment(Connexio connexio, Scanner sn) {
        sn.nextLine(); //refresca scanner
        boolean salir = false;
        int opcionMenu;
        while(!salir){
            System.out.println("----Benvingut a la secció de Manteniment, què vol fer?----");
            System.out.println("1.Mostrar tots els registres de manteniment");
            System.out.println("2.Mostrar per dni de Mecànic");
            System.out.println("3.Inserir registre manteniment");
            System.out.println("4.Actualitzar registre");
            System.out.println("5.Esborrar per matricula cotxe");
            System.out.println("0.Tornar al menú principal");
            System.out.print("Escriu una de les opcions --> ");

            opcionMenu=sn.nextInt();
            switch (opcionMenu){
                case 1:
                    System.out.print("\n-------------------MOSTRAR DADES-------------------\n");
                    Manteniment.mostrarTots(connexio);
                    break;
                case 2:
                    System.out.print("\n-------------------MOSTRAR PER DNI-------------------\n");
                    String dni;
                    System.out.print("Introdueix el dni del mecanic que vols buscar: ");
                    dni=sn.next();
                    Manteniment ma1 = new Manteniment(dni);
                    ma1.mostrarManteniment(connexio,ma1.getDniMecanic());
                    break;
                case 3:
                    //String dni, String matricula, String dataInici, String dataFi)
                    System.out.print("\n-------------------INSERIR-------------------\n");
                    String dniInsertar,matricula,dataInici,dataFi;
                    System.out.println("Introdueix les dades del mecànic a crear ");
                    sn.nextLine(); //refresca el scanner
                    System.out.print("DNI Mecànic: "); dniInsertar=sn.nextLine();
                    System.out.print("Matrícula Cotxe: "); matricula=sn.nextLine();
                    System.out.print("Data Inici Manteniment (dd-mm-yyyy): "); dataInici=sn.nextLine();
                    System.out.print("Data Fi Manteniment (dd-mm-yyyy): "); dataFi=sn.nextLine();
                    System.out.println("Inserint el registre.....");
                    Manteniment ma2 = new Manteniment(dniInsertar,matricula, dataInici, dataFi);
                    ma2.inserirManteniment(connexio, ma2.getDniMecanic(),ma2.getMatricula(), ma2.getDataInici(), ma2.getDataFi());
                    break;
                case 4:
                    System.out.print("\n-------------------ACTUALITZAR-------------------\n");
                    String [] columnas = new String[]{"dniMecanic","matricula","dataInici","dataFi"};
                    Map<String,String> modificarColumnas = new TreeMap<>();
                    String matriculaModificar, dniModificar;
                    sn.nextLine(); //refresca scanner
                    for(String columna :columnas){
                        String clave;
                        String valor;
                        System.out.print("Vols modificar "+columna+"? (s/n) ");
                        clave=sn.nextLine();
                        if (clave.equalsIgnoreCase("s")){
                            System.out.print("Quin nou valor per "+columna+"? ");
                            if(columna.equalsIgnoreCase("dataInici")||columna.equalsIgnoreCase("dataFi")){
                                System.out.print(" (dd/mm/yyyy): ");
                            }
                            valor=sn.nextLine();
                            modificarColumnas.put(columna, valor);
                        }
                    }
                    System.out.print("Posa la matrícula del cotxe pel registre que vols modificar: ");
                    matriculaModificar=sn.next();
                    System.out.print("Posa el dni del mecànic pel registre que vols modificar: ");
                    dniModificar=sn.next();
                    Manteniment ma3 = new Manteniment(dniModificar, matriculaModificar);
                    ma3.modificarManteniment(connexio,modificarColumnas,ma3.getMatricula(), ma3.getDniMecanic());
                    System.out.println("Actualizar...");
                    break;
                case 5:
                    System.out.print("\n-------------------ESBORRAR-------------------\n");
                    String matriculaEsborrar;
                    System.out.print("Introdueix matricula del cotxe que vols esborrar: ");
                    matriculaEsborrar=sn.next();
                    System.out.println("Esborrant registre...");
                    Manteniment ma4 = new Manteniment(matriculaEsborrar);
                    ma4.eliminarManteniment(connexio, ma4.getMatricula());
                    break;
                case 0:
                    salir=true;
                    break;
                default:
                    System.out.println("Únicament nombres del 1 al 6");
            }

        }

    }

    public static void infoRegistre(Connexio connexio){
        try{
            Statement sentencia = connexio.getConexion().createStatement();
            ResultSet resul = sentencia.executeQuery("select matricula, c.dni, nomCognom, telefon, dies, preu from clients c, lloguer l where l.dni = c.dni;");
            while(resul.next()){
                System.out.println("-----------------------------------------------\n");
                System.out.println("Matricula: "+resul.getString("matricula"));
                System.out.println("Dni Client: "+resul.getString("dni"));
                System.out.println("Nom i cognom Client: "+resul.getString("nomCognom"));
                System.out.println("Telèfon: "+resul.getString("telefon"));
                System.out.println("Dies de lloguer: "+resul.getString("dies"));
                System.out.println("Preu per dia: "+resul.getString("preu"));
                System.out.println("-----------------------------------------------\n");
            }

        }catch (Exception e){
            System.err.println("Ha habido una exception!");
            System.err.println(e.getMessage());
        }
    }
}

