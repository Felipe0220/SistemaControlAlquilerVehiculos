/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservas;

import Clientes.Cliente;
import Excepciones.Excepcion;
import Vehiculos.Estado;
import static Vehiculos.Estado.Disponible;
import Vehiculos.Tipo;
import java.time.LocalDate;

/**
 *
 * @author Sebastian
 */
public class ReservaCliente {
    private String idReserva;
    private Cliente cliente;
    private Estado estado;
    private Tipo tipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double costoTotal;

    public ReservaCliente(String idReserva, Cliente cliente, Tipo tipo, LocalDate fechaInicio, LocalDate fechaFin, double costoTotal) throws Excepcion {
        this.idReserva = idReserva;
        
        
        this.cliente = cliente;
        
        
        this.estado = Disponible;
        
        
        this.tipo = tipo;
        
         if (fechaInicio.isBefore(LocalDate.now())) {
        throw new Excepcion();
    }
        this.fechaInicio = fechaInicio;
        
        if (!fechaFin.isAfter(fechaInicio)) {
        throw new Excepcion();
    }
        this.fechaFin = fechaFin;
        
        
        this.costoTotal = costoTotal;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Estado getEstado() {
        return estado;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
    
    public void calcularCosto(double tarifaDiaria){
        int dias = fechaFin.getDayOfYear() - fechaInicio.getDayOfYear();
        if (dias <= 0){
            dias = 1;
        }
        costoTotal=dias*tarifaDiaria;
    }
}
