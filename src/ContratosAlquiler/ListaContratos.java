/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ContratosAlquiler;

import Listas.Lista;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class ListaContratos implements Lista<Contrato> {
    private ArrayList<Contrato> contratos;

    public ListaContratos() {
        contratos = new ArrayList<>();
    }

    @Override
    public boolean Eliminar(Contrato c) {
        return contratos.remove(c);
    }

    @Override
    public Contrato Buscar(Object id) {
        for (Contrato c : contratos) {
            if (c.getIdContrato() == (int) id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean Actualizar(Contrato v) {
        for (int i = 0; i < contratos.size(); i++) {
            if (contratos.get(i).getIdContrato() == v.getIdContrato()) {
                contratos.set(i, v);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean modificarReserva(Contrato v) {
        return Actualizar(v);
    }

    @Override
    public boolean cancelarReserva(Contrato v) {
        v.cancelarContrato();
        return true;
    }

    @Override
    public Contrato buscarReservaPorCliente(Object clienteId) {
        for (Contrato c : contratos) {
            if (c.getCliente().getId().equals(clienteId)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean confirmarReserva(Contrato v) {
        if (!contratos.contains(v)) {
            contratos.add(v);
            return true;
        }
        return false;
    }

    public ArrayList<Contrato> getContratos() {
        return contratos;
    }
}
