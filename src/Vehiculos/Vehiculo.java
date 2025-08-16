/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

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
    private Estado estadovehiculo;

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
    public Estado getEstadovehiculo() {
        return estadovehiculo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setTipovehiculo(Tipo tipovehiculo) {
        this.tipovehiculo = tipovehiculo;
    }
    public void setEstadovehiculo(Estado estadovehiculo) {
        this.estadovehiculo = estadovehiculo;
    }
    public Vehiculo(String placa, String marca, String modelo, int año, Tipo tipovehiculo, Estado estadovehiculo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.tipovehiculo = tipovehiculo;
        this.estadovehiculo = Estado.Disponible;
    }
}
