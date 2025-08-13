/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clientes;

import Personas.Persona;
import java.time.LocalDate;

/**
 *
 * @author Sebastian
 */
public class Cliente extends Persona {
    private String license;

    public Cliente(String license, String id, String name, LocalDate birthDate, String phone) {
        super(id, name, birthDate, phone);
        this.license = license;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
    
    public boolean Adult(){
        return getAge() >= 18;
    }
    
    public boolean EmailValid(){
        return email != null && email.contains("@") && email.contains(".");
    }
    
    public boolean PhoneValid(){
        return phone != null && phone.matches("\\d{8}");
    }
    
    public boolean HasLicense(){
        return license != null && !license.isEmpty();
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", birthDate: " + birthDate + ", age: "+getAge() + ", phone: " + phone + ", License: " + license;
    }   
    
}
