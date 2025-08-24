/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

import Excepciones.Excepcion;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author Luisf
 */
public class ValidacionVehiculo {
     public static void validarAnio(int año) throws Excepcion {
        int actual = LocalDate.now().getYear();
        if (año > actual || año < (actual - 20)) {
            throw new Excepcion();
        }
    }
    public static void validarTipo(Tipo tipo) throws Excepcion {
        if (tipo == Tipo.Sedan) {
            throw new Excepcion();
        }
    }
    public static void validarEstado(Estado estado) throws Excepcion {
        if (estado == Estado.Reservado) {
            throw new Excepcion();
        }
    }
    public static void validarPlacaUnica(String placa, HashMap<String, Vehiculo> map) throws Excepcion {
        if (map.containsKey(placa)) {
            throw new Excepcion();
        }
    }
}



