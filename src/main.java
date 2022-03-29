import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Clients client = new Clients();
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while(!salir){
            System.out.println("Datos de la Base de datos carsRental");
            System.out.println("1.MostrarTodos");
            System.out.println("2.Mostrar por dni");
            System.out.println("3.Inserir");
            System.out.println("4.Actualizar");
            System.out.println("5.Eliminar por dni");
            System.out.println("6.Salir");
            System.out.print("Escribe una de las opciones --> ");
            opcion=sn.nextInt();

            switch (opcion){
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
                    String dniInsertar,nom, tel, adreca,ciutat,pais,email,permis;
                    int edat,punts;
                    System.out.println("Introdueix les dades del usuario a mostrar ");
                    System.out.print("DNI: "); dniInsertar=sn.next();
                    System.out.print("Nom: "); nom=sn.next();
                    System.out.print("Edat: "); edat=sn.nextInt();
                    System.out.print("Telèfon: "); tel=sn.next();
                    System.out.print("Adreça: "); adreca=sn.next();
                    System.out.print("Ciutat: "); ciutat=sn.next();
                    System.out.print("Pais: "); pais=sn.next();
                    System.out.print("Email: "); email=sn.next();
                    System.out.print("Permis: "); permis=sn.next();
                    System.out.print("Punts: "); punts=sn.nextInt();
                    System.out.println("Inserint el client.....");
                    client.inserirClient(dniInsertar,nom, edat, tel, adreca, ciutat, pais, email, permis, punts);
                    break;
                case 4:
                    String [] columnas = new String[]{"nomCognom","dni","edat","telefon","adreca","ciutat","pais","email","permisConduccio","punts"};
                    ArrayList<String> tmpArray = new ArrayList<String>();
                    Map<String,String> modificarColumnas = new HashMap<String,String>();
                    String dniModificar;
                    for(String columna :columnas){
                        String clave;
                        String valor;
                        System.out.print("Vols modificar "+columna+"? (s/n) ");
                        clave=sn.next();
                        if (clave.equalsIgnoreCase("s")){
                            //tmpArray.add(columna);
                            System.out.print("Quin nou valor per "+columna+"? ");
                            valor=sn.next();
                            modificarColumnas.put(columna, valor);
                        }
                        System.out.println(modificarColumnas);
                    }
                    //System.out.print("Posa el dni del client que vols modificar: ");
                    //dniModificar=sn.next();
                    //client.modificarCliente(modificarColumnas,dniModificar);
                    //System.out.println("Actualizar...");
                    //client.modificarCliente("55555","22222","Pepito");
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