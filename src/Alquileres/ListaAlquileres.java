/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alquileres;

import Listas.Lista;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class ListaAlquileres implements Lista<Alquiler> {
    private ArrayList<Alquiler> alquileres;

    public ListaAlquileres() {
        this.alquileres = new ArrayList<>();
    }

    @Override
    public boolean Eliminar(Alquiler v) {
        return alquileres.remove(v);
    }

    @Override
    public Alquiler Buscar(Object id) {
        for (Alquiler a : alquileres) {
            if (a.getIdAlquiler() == (int) id) { // suponiendo que el "Object" es un int
                return a;
            }
        }
        return null;
    }

    @Override
    public boolean Actualizar(Alquiler v) {
        for (int i = 0; i < alquileres.size(); i++) {
            if (alquileres.get(i).getIdAlquiler() == v.getIdAlquiler()) {
                alquileres.set(i, v);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean modificarReserva(Alquiler v) {
        return Actualizar(v);//igual actualizar
    }

    @Override
    public boolean cancelarReserva(Alquiler v) {
        return Eliminar(v); //igual que eliminar
    }

    @Override
    public Alquiler buscarReservaPorCliente(Object cliente) {
        for (Alquiler a : alquileres) {
            if (a.getCliente().equals(cliente)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public boolean confirmarReserva(Alquiler v) {
     if (!alquileres.contains(v)) {
            alquileres.add(v);
            return true;
        }
        return false;
    }

    public ArrayList<Alquiler> getAlquileres() {
        return alquileres;
    }
}

