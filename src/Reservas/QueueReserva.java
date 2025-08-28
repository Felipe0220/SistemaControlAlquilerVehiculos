/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservas;

import Listas.Lista;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Luisf
 */
public class QueueReserva implements Lista<ReservaCliente> {
    private Queue<ReservaCliente> reservasEnEspera = new LinkedList<>();

    
    @Override
    public boolean Agregar(ReservaCliente v) {
        return reservasEnEspera.add(v); // nuevo Kevin añade al final de la cola
    }
    
    @Override
    public boolean modificarReserva(ReservaCliente v) {
        for (ReservaCliente r : reservasEnEspera) {
            if (r.getIdReserva().equals(v.getIdReserva())) {
                reservasEnEspera.remove(r);
                reservasEnEspera.add(v);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean cancelarReserva(ReservaCliente v) {
        for (ReservaCliente r : reservasEnEspera) {
            if (r.getIdReserva().equals(v.getIdReserva())) {
                if (LocalDate.now().isBefore(r.getFechaInicio())) {
                    reservasEnEspera.remove(r);
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    @Override
    public ReservaCliente buscarReservaPorCliente(Object idReserva) {
        String id = String.valueOf(idReserva);
        for (ReservaCliente v : reservasEnEspera) {
            if (v.getIdReserva().equals(id)) {
                return v;
            }
        }
        return null;
    }
    @Override
    public boolean confirmarReserva(ReservaCliente v) {
        for (ReservaCliente r : reservasEnEspera) {
            if (r.getIdReserva().equals(v.getIdReserva())) {
                reservasEnEspera.remove(r);
                return true;
            }
        }
        return false;
    }
    
    
    
    @Override
    public boolean Eliminar(ReservaCliente v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReservaCliente Buscar(Object placa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Actualizar(ReservaCliente v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}






