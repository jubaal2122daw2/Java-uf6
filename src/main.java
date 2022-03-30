import java.util.*;

public class main {
    public static void main(String[] args) {
        Clients client = new Clients();
        Cotxes cotxe = new Cotxes();
        Mecanics mecanic = new Mecanics();
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while(!salir){
            System.out.println("CARS RENTAL");
            System.out.println("1.Gestionar Clients");
            System.out.println("2.Gestionar Cotxes");
            System.out.println("3.Gestionar Mecànics");
            System.out.println("6.Salir");
            System.out.print("Escriu una de les opcions --> ");
            opcion=sn.nextInt();
            switch (opcion){
                case 1:
                    menuClient(client, sn);
                    break;
                case 2:
                    menuCotxes(cotxe,sn);
                    break;
                case 3:
                    menuMecanics(mecanic, sn);
                    break;
                case 6:
                    salir=true;
                    break;
            }
        }
    }
    public static void menuClient(Clients client, Scanner sn) {
        sn.nextLine(); //refresca scanner
        boolean salir = false;
        int opcionMenu;
        while(!salir){
            System.out.println("Benvingut a la secció de Clients, què vol fer?");
            System.out.println("1.Mostrar tots els clients");
            System.out.println("2.Mostrar per dni");
            System.out.println("3.Inserir");
            System.out.println("4.Actualitzar");
            System.out.println("5.Esborrar per dni");
            System.out.println("6.Tornar al menú principal");
            System.out.print("Escriu una de les opcions --> ");

            opcionMenu=sn.nextInt();
            switch (opcionMenu){
                case 1:
                    System.out.print("-------------------MOSTRAR DADES-------------------\n");
                    System.out.println("Mostrant totes les dades...");
                    client.mostrarTots();
                    break;
                case 2:
                    System.out.print("-------------------MOSTRAR PER DNI-------------------\n");
                    String dni;
                    System.out.print("Introdueix el dni del client que vols buscar: ");
                    dni=sn.next();
                    System.out.println("Mostrar client pel dni...");
                    client.mostrarClient(dni);
                    break;
                case 3:
                    System.out.print("-------------------INSERIR-------------------\n");
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
                    client.inserirClient(dniInsertar,nom, edat, tel, adreca, ciutat, pais, email, permis, punts);
                    break;
                case 4:
                    System.out.print("-------------------ACTUALITZAR-------------------\n");
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
                    client.modificarCliente(modificarColumnas,dniModificar);
                    System.out.println("Actualizar...");
                    break;
                case 5:
                    System.out.print("-------------------ESBORRAR-------------------\n");
                    String dniEsborrar;
                    System.out.print("Introdueix el dni del client que vols esborrar: ");
                    dniEsborrar=sn.next();
                    System.out.println("Esborrant client...");
                    client.eliminarClient(dniEsborrar);
                    break;
                case 6:
                    salir=true;
                    break;
                default:
                    System.out.println("Únicament nombres del 1 al 6");
            }

        }

    }

    public static void menuCotxes(Cotxes cotxe, Scanner sn){
        sn.nextLine(); //refresca scanner
        boolean salir = false;
        int opcionMenu;
        while(!salir){
            System.out.println("Benvingut a la secció de Cotxes, què vol fer?");
            System.out.println("1.Mostrar tots els cotxes");
            System.out.println("2.Mostrar per matricula");
            System.out.println("3.Inserir");
            System.out.println("4.Actualitzar");
            System.out.println("5.Esborrar per matricula");
            System.out.println("6.Tornar al menú principal");
            System.out.print("Escriu una de les opcions --> ");

            opcionMenu=sn.nextInt();
            switch (opcionMenu){
                case 1:
                    System.out.print("-------------------MOSTRAR DADES-------------------\n");
                    cotxe.mostrarTots();
                    break;
                case 2:
                    System.out.print("-------------------MOSTRAR DADES PER MATRICULA-------------------\n");
                    String matricula;
                    System.out.print("Introdueix la matricula del cotxe que vols buscar: ");
                    matricula=sn.next();
                    System.out.println("Mostrar cotxe per la matricula...");
                    cotxe.mostrarCotxes(matricula);
                    break;
                case 3:
                    System.out.print("-------------------INSERIR-------------------\n");
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
                    System.out.println("Inserint el client.....");
                    cotxe.inserirCotxe(matriculaInsertar,numBastidor, marca, model, color, places, numPortes, maleter, tCombustible);
                    break;
                case 4:
                    System.out.print("-------------------ACTUALITZAR-------------------\n");
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
                    cotxe.modificarCotxe(modificarColumnas,matriculaModificar);
                    System.out.println("Actualitzant...");
                    break;
                case 5:
                    System.out.print("-------------------ESBORRAR-------------------\n");
                    String matriculaEsborrar;
                    System.out.print("Introdueix la matricula del cotxe que vols esborrar: ");
                    matriculaEsborrar=sn.next();
                    System.out.println("Esborrant cotxe...");
                    cotxe.eliminarCotxe(matriculaEsborrar);
                    break;
                case 6:
                    salir=true;
                    break;
                default:
                    System.out.println("Únicament nombres del 1 al 6");
            }

        }
    }

    public static void menuMecanics(Mecanics mecanic, Scanner sn) {
        sn.nextLine(); //refresca scanner
        boolean salir = false;
        int opcionMenu;
        while(!salir){
            System.out.println("Benvingut a la secció de Mecànics, què vol fer?");
            System.out.println("1.Mostrar tots els mecànics");
            System.out.println("2.Mostrar per dni");
            System.out.println("3.Inserir");
            System.out.println("4.Actualitzar");
            System.out.println("5.Esborrar per dni");
            System.out.println("6.Tornar al menú principal");
            System.out.print("Escriu una de les opcions --> ");

            opcionMenu=sn.nextInt();
            switch (opcionMenu){
                case 1:
                    System.out.print("-------------------MOSTRAR DADES-------------------\n");
                    System.out.println("Mostrant totes les dades...");
                    mecanic.mostrarTots();
                    break;
                case 2:
                    System.out.print("-------------------MOSTRAR PER DNI-------------------\n");
                    String dni;
                    System.out.print("Introdueix el dni del mecanic que vols buscar: ");
                    dni=sn.next();
                    System.out.println("Mostrar mecanic pel dni...");
                    mecanic.mostrarMecanics(dni);
                    break;
                case 3:
                    System.out.print("-------------------INSERIR-------------------\n");
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
                    mecanic.inserirMecanic(dniInsertar,nomCognomM, edatM, telM, adrecaM, ciutatM, paisM, emailM, permisConduccioM, puntsM, salari, seguretatSocial, titulacio, anysEmp);
                    break;
                case 4:
                    System.out.print("-------------------ACTUALITZAR-------------------\n");
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
                    mecanic.modificarMecanic(modificarColumnas,dniModificar);
                    System.out.println("Actualizar...");
                    break;
                case 5:
                    System.out.print("-------------------ESBORRAR-------------------\n");
                    String dniEsborrar;
                    System.out.print("Introdueix el dni del mecànic que vols esborrar: ");
                    dniEsborrar=sn.next();
                    System.out.println("Esborrant mecànic...");
                    mecanic.eliminarMecanic(dniEsborrar);
                    break;
                case 6:
                    salir=true;
                    break;
                default:
                    System.out.println("Únicament nombres del 1 al 6");
            }

        }

    }
}

