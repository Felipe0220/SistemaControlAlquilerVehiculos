/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Empleados;

import Personas.Persona;
import java.time.LocalDate;

/**
 *
 * @author kevin
 */
public class Empleado extends Persona {

    private String puesto;
    private double salario;

    public Empleado(String id, String name, LocalDate birthDate, String phone, String email, String puesto, double salario) {
        super(id, name, birthDate, phone);
        this.setEmail(email);
        this.puesto = puesto;
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return super.toString() + ", email=" + email + ", puesto=" + puesto + ", salario=" + salario;
    }

}
