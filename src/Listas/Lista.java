/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Listas;

/**
 *
 * @author Luisf
 */
public interface Lista <V> {
    public boolean Agregar (V v);
    public boolean Eliminar(V v);
    public V Buscar(Object placa);
    public boolean Actualizar(V v);
    boolean modificarReserva(V v);   
    boolean cancelarReserva(V v);    
    V buscarReservaPorCliente(Object idReserva);
    boolean confirmarReserva(V v);
}

