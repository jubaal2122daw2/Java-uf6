package Ejercicio2;

public class mainDAO {
    public static void main(String[] args) {
        usarMetodosInCliente clienteDao = new usarMetodosInCliente();

        // imprimir los clientes
        clienteDao.obtenerClientes().forEach(System.out::println);

        // obtner un cliente
        Cliente cliente = clienteDao.obtenerCliente(0);
        cliente.setApellido("Pardo");
        //actualizar cliente
        clienteDao.actualizarCliente(cliente);

        // imprimir los clientes
        System.out.println("*****");
        clienteDao.obtenerClientes().forEach(System.out::println);
    }
}
