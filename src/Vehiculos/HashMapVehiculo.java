/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

import Listas.Lista;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author Luisf
 */
public class HashMapVehiculo implements Lista<Vehiculo> {
    HashMap<String, Vehiculo> map;

    public HashMap<String, Vehiculo> getMap() {
        return map;
    }
    
    

    public HashMapVehiculo() {
        this.map = new HashMap<>();
    }
        @Override
    public boolean Agregar(Vehiculo v)throws IllegalArgumentException {
        if (!map.containsKey(v.getPlaca())) {
            map.put(v.getPlaca(), v);
              int añoActual = Calendar.getInstance().get(Calendar.YEAR);
        if (v.getAño() > añoActual) {
            throw new IllegalArgumentException("El año no puede ser mayor al año actual (" + añoActual + ")");
        }
        if (añoActual - v.getAño() > 20) {
            throw new IllegalArgumentException("El vehículo no puede tener más de 20 años de antigüedad");
        }

            return true;
        }
        return false; // ya existe un vehículo con esa placa
    }

    
    @Override
    public boolean Eliminar(Vehiculo v) {
        if (map.containsKey(v.getPlaca())) {
            Vehiculo vehiculo = map.get(v.getPlaca());
            if (vehiculo.getEstado()== Estado.En_Alquiler) {
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
            existente.setEstadovehiculo(v.getEstado());
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
    public Vehiculo buscarPorPlaca(String placa) {
    return map.get(placa);
}
}


