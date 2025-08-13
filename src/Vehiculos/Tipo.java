/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Vehiculos;

/**
 *
 * @author UTN
 */
public enum Tipo {
    Sedan("Sedan"),
    BMW("BMW"),
    SUV("SUV"),
    Pick_up("Pick_up");
    
    private final String tipo;

     Tipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return  tipo ;
    }
     
     
    
    
}
