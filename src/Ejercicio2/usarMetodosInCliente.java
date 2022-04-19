package Ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class usarMetodosInCliente implements interfaceDaoCliente{

    List<Cliente> clientes;

    public usarMetodosInCliente() {
        clientes = new ArrayList<>();
        Cliente cliente1 = new Cliente(0,"Javier", "Molina");
        Cliente cliente2 = new Cliente(1,"Lillian","Álvarez");
        clientes.add(cliente1);
        clientes.add(cliente2);
    }
    @Override
    public List<Cliente> obtenerClientes() {
        return clientes;
    }

    @Override
    public Cliente obtenerCliente(int id) {
        return clientes.get(id);
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        clientes.get(cliente.getId()).setNombre(cliente.getNombre());
        clientes.get(cliente.getId()).setApellido(cliente.getApellido());
        System.out.println("Cliente con id: "+cliente.getId()+" actualizado satisfactoriamente");
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente.getId());
        System.out.println("Cliente con id: "+cliente.getId()+" elimnado satisfactoriamente");
    }
}
