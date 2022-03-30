import java.util.*;

public class main {
    public static void main(String[] args) {
        Clients client = new Clients();
        Cotxes cotxe = new Cotxes();
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while(!salir){
            System.out.println("CARS RENTAL");
            System.out.println("1.Gestionar Clients");
            System.out.println("2.Gestionar Cotxes");
            System.out.println("6.Salir");
            System.out.print("Escribe una de las opciones --> ");
            opcion=sn.nextInt();
            switch (opcion){
                case 1:
                    menuCliente(client, sn);
                    break;
            }
        }
    }
    public static void menuCliente(Clients client, Scanner sn) {
        sn.nextLine(); //refresca scanner
        boolean salir = false;
        int opcionMenu;
        while(!salir){
            System.out.println("Benvingut a la secció de Clients, què vol fer?");
            System.out.println("1.MostrarTodos");
            System.out.println("2.Mostrar por dni");
            System.out.println("3.Inserir");
            System.out.println("4.Actualizar");
            System.out.println("5.Eliminar por dni");
            System.out.println("6.Salir");
            System.out.print("Escribe una de las opciones --> ");
            opcionMenu=sn.nextInt();
            switch (opcionMenu){
                case 1:
                    System.out.println("Mostrando todos los datos...");
                    client.mostrarTots();
                    break;
                case 2:
                    String dni;
                    System.out.print("Introdueix el dni del client que vols buscar: ");
                    dni=sn.next();
                    System.out.println("Mostrar client pel dni...");
                    client.mostrarClient(dni);

                    break;
                case 3:
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
}

