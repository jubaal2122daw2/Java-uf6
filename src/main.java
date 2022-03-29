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
                    System.out.println("Mostrando por dni...");
                    client.mostrarClient("123456");
                    break;
                case 3:
                    System.out.println("Inseriendo el cliente");
                    client.inserirClient("22222",
                            "HOLA",
                            22,
                            "123345",
                            "holahola",
                            "holagola",
                            "españa",
                            "@@@@",
                            "A2",
                            10);
                    break;
                case 4:
                    System.out.println("Actualizar...");
                    client.modificarCliente("55555","22222","Pepito");
                    break;
                case 5:
                    System.out.println("Eliminar por dni...");
                    client.eliminarClient("22222");
                    break;
                case 6:
                    salir=true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 6");
            }
        }
    }
}
