/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personas;

import Utils.UtilDate;
import java.time.LocalDate;

/**
 *
 * @author UTN
 */
public abstract class Persona {
     protected String id;
    protected String name;
    protected LocalDate birthDate;
    protected String phone;
    protected String email;

    public String getEmail() {
        return email;
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public int getAge(){
        return UtilDate.calculateAge(birthDate);
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
