/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

import Excepciones.ExcepcionVehiculo;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author Luisf
 */
public class ValidacionVehiculo {
     public static void validarAnio(int año) throws ExcepcionVehiculo {
        int actual = LocalDate.now().getYear();
        if (año > actual || año < (actual - 20)) {
            throw new ExcepcionVehiculo();
        }
    }
    public static void validarTipo(Tipo tipo) throws ExcepcionVehiculo {
        if (tipo == Tipo.Sedan) {
            throw new ExcepcionVehiculo();
        }
    }
    public static void validarEstado(Estado estado) throws ExcepcionVehiculo {
        if (estado == Estado.Reservado) {
            throw new ExcepcionVehiculo();
        }
    }
    public static void validarPlacaUnica(String placa, HashMap<String, Vehiculo> map) throws ExcepcionVehiculo {
        if (map.containsKey(placa)) {
            throw new ExcepcionVehiculo();
        }
    }
}



