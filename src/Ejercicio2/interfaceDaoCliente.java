package Ejercicio2;

import java.util.List;

public interface interfaceDaoCliente {
    public List<Cliente> obtenerClientes();
    public Cliente obtenerCliente(int id);
    public void actualizarCliente(Cliente cliente);
    public void eliminarCliente(Cliente cliente);
}
