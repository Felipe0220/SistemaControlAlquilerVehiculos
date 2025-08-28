/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

import java.time.LocalDate;

/**
 *
 * @author Luisf
 */
public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int año;
    private Tipo tipovehiculo;
    private Estado estado;

    public String getPlaca() {
        return placa;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public int getAño() {
        return año;
    }
    public Tipo getTipovehiculo() {
        return tipovehiculo;
    }
    public Estado getEstado() {
        return estado;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setTipovehiculo(Tipo tipovehiculo) {
        this.tipovehiculo = tipovehiculo;
    }
    public void setEstadovehiculo(Estado estadovehiculo) {
        this.estado = estadovehiculo;
    }
    public Vehiculo(String placa, String marca, String modelo, int año, Tipo tipo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.tipovehiculo = tipovehiculo;
        this.estado = Estado.Disponible;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", a\u00f1o=" + año + ", tipovehiculo=" + tipovehiculo + ", estado=" + estado + '}';
    }
    
}
