/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Empleados;

import Utils.UtilDate;
import java.util.ArrayList;


/**
 *
 * @author kevin
 */
public class Listaempleados {

    private ArrayList<Empleado> empleados;

   
    public Listaempleados() {
        empleados = new ArrayList<>();
    }

    public boolean agregarEmpleado(Empleado e) {
        // validacion de id dobles
        if (buscarEmpleado(e.getId()) != null) {
            return false;
        }

        if (!UtilDate.isLegalAge(e.getBirthDate())) {
            return false;
        }

   
       
        empleados.add(e);
        return true;
    }

    public boolean actualizarEmpleado(String id, String telefono, String email, String puesto) {
        Empleado e = buscarEmpleado(id);
        if (e == null) {
            return false;
        }

        if (telefono != null && !telefono.isEmpty()) {
            e.setPhone(telefono);
        }

        if (email != null && !email.isEmpty()) {
            e.setEmail(email);
        }

        if (puesto != null && !puesto.isEmpty()) {
            e.setPuesto(puesto);
        }

        return true;
    }

    public boolean eliminarEmpleado(String id) {
        Empleado e = buscarEmpleado(id);
        if (e != null) {
            empleados.remove(e);
            return true;
        }
        return false;
    }

    public Empleado buscarEmpleado(String id) {
        for (Empleado e : empleados) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

}
