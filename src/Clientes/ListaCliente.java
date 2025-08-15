/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clientes;

import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class ListaCliente {
    private ArrayList<Cliente> clientes;

    public ListaCliente() {
        clientes=new ArrayList<>();
    }
    
    public void agregarCliente(Cliente cliente){
        clientes.add(cliente);
    }
    
    public Cliente buscarClientePorId(String id){
        for (Cliente c : clientes){
            if (c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }
    
    public boolean eliminarCliente(String id){
        Cliente cliente = buscarClientePorId(id);
        if (cliente !=null){
            clientes.remove(cliente);
            return true;
        }
        return false;
    }
    
    public ArrayList<Cliente> getClientes(){
        return clientes;
    }
}
