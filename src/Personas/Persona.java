/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personas;

import Utils.UtilDate;
import java.time.LocalDate;
import java.util.regex.Pattern;

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
    protected static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";


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
        if(validarEmail(email))
        this.email = email;
    }

     public void setPhone(String phone) {
        if (validatePhone(phone))
            this.phone = phone;
    }
    
    private static boolean validatePhone(String phone){
        return phone.matches("^[0-9]{4}-[0-9]{4}$");
    }
    private boolean validarEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
    public Persona(String id, String name, LocalDate birthDate, String phone) {
        this.id = id;
        this.name = name;
        if (UtilDate.isNotFutureDate(birthDate))
            this.birthDate = birthDate;
        if(validatePhone(phone))
            this.phone = phone;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", age="+getAge() + ", phone=" + phone;
    }
    
    
}
