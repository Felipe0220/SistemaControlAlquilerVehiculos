/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alquileres;

import Clientes.Cliente;
import Vehiculos.Estado;
import Vehiculos.Vehiculo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 *
 * @author kevin
 */
public class Contrato {
    private int idContrato;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double monto;
    private Estado estadoContrato;

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Estado getEstadoContrato() {
        return estadoContrato;
    }

    public void setEstadoContrato(Estado estadoContrato) {
        this.estadoContrato = estadoContrato;
    }



        // Acciones sobre el contrato
    public void finalizarContrato() {
        if (estadoContrato == Estado.En_Alquiler) { // comparación con enum
            estadoContrato = Estado.Disponible; // cambiar estado del contrato
            vehiculo.setEstadovehiculo(Estado.Disponible); // libera el vehículo
        }
    }

    public void cancelarContrato() {
        if (estadoContrato != Estado.Disponible) { // si no está finalizado
            estadoContrato = Estado.Disponible; // marcar como disponible
            vehiculo.setEstadovehiculo(Estado.Disponible); // liberar vehículo
        }
    }

    public Contrato(int idContrato, Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin, double tarifaDiaria, Estado estadoContrato) {
        this.idContrato = idContrato;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        this.monto = dias * tarifaDiaria;
        this.estadoContrato = estadoContrato;
        
        this.estadoContrato = Estado.En_Alquiler; 
        this.vehiculo.setEstadovehiculo(Estado.En_Alquiler);
        
        
    }

    @Override
    public String toString() {
        return "Contrato{" + "idContrato=" + idContrato + ", cliente=" + cliente.getId() + ", vehiculo=" + vehiculo.getPlaca() + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", monto=" + monto + ", estadoContrato=" + estadoContrato + '}';
    }
    
}