/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

import Listas.Lista;
import java.util.HashMap;

/**
 *
 * @author Luisf
 */
public class HashMapVehiculo implements Lista<Vehiculo> {
    HashMap<String, Vehiculo> map;

    public HashMapVehiculo() {
        this.map = new HashMap<>();
    }
    @Override
    public boolean Eliminar(Vehiculo v) {
        if (map.containsKey(v.getPlaca())) {
            Vehiculo vehiculo = map.get(v.getPlaca());
            if (vehiculo.getEstadovehiculo()== Estado.En_Alquiler) {
                return false; // no se puede eliminar
            }
            map.remove(v.getPlaca());
            return true;
        }
        return false;
    }
    @Override
    public Vehiculo Buscar(Object placa) {
        String strPlaca = String.valueOf(placa);
        return map.get(strPlaca);
    }
    @Override
    public boolean Actualizar(Vehiculo v) {
        if (map.containsKey(v.getPlaca())) {
            Vehiculo existente = map.get(v.getPlaca());
            existente.setModelo(v.getModelo());
            existente.setTipovehiculo(v.getTipovehiculo());
            existente.setEstadovehiculo(v.getEstadovehiculo());
            return true;
        }
        return false;
    }
    
    
    @Override
    public boolean modificarReserva(Vehiculo v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public boolean cancelarReserva(Vehiculo v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public Vehiculo buscarReservaPorCliente(Object idReserva) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public boolean confirmarReserva(Vehiculo v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


