/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Empleados;

import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class Alquiler {
    private int idAlquiler;
    private String vehiculo;
    private String cliente;
    private String fechaInicio;
    private String fechaFin;
    
    private static ArrayList<Alquiler> listaAlquileres = new ArrayList<>();
    
    public int getIdAlquiler() {
        return idAlquiler;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public String getCliente() {
        return cliente;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public Alquiler(int idAlquiler, String vehiculo, String cliente, String fechaInicio, String fechaFin) {
        this.idAlquiler = idAlquiler;
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    
     public static void agregarAlquiler(Alquiler a) {
        listaAlquileres.add(a);
    }

    public static Alquiler buscarAlquiler(int id) {
        for (Alquiler a : listaAlquileres) {
            if (a.getIdAlquiler() == id) {
                return a;
            }
        }
        return null;
    }

    public static void eliminarAlquiler(int id) {
        Alquiler a = buscarAlquiler(id);
        if (a != null) {
            listaAlquileres.remove(a);
        }
    }

    public static ArrayList<Alquiler> getListaAlquileres() {
        return listaAlquileres;
    }

    @Override
    public String toString() {
        return "Alquiler{" + "idAlquiler=" + idAlquiler + ", vehiculo=" + vehiculo + ", cliente=" + cliente + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }
    
}
