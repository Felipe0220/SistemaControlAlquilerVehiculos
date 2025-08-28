/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Vehiculos;

/**
 *
 * @author UTN
 */
public enum Estado {
    Disponible("Disponible"),
    En_Alquiler("En alquiler"),
    Reservado("Reservado"),
    En_Mantenimiento("En_Mantenimiento"),
    Confirmada("Confirmada");
    
    private final String estado;

     Estado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return estado ;
    }
   
}
