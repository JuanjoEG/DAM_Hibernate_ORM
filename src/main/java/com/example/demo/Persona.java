package com.example.demo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Juan José Estévez González
 */

@Entity
@Table(name = "persona")
public class Persona implements Serializable {
    
    // ATRIBUTOS
    
    @Id @GeneratedValue
    @Column(name="id_persona")    
    private int idPersona;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellido")
    private String apellido;
    
    @Column(name="email")
    private String email;
    
    @Column(name="telefono")
    private String telefono;
    
    // CONSTRUCTORES
    
    public Persona(){}    

    public Persona(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
    
    // GETTERS Y SETTERS
    
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    // TO STRING
    
    @Override
    public String toString() {
        
        String tit = ("\nTABLA PERSONA:");
        String idP = ("\n Persona ID:  " + idPersona);
        String noP = ("\n Nombre    :  " + nombre);
        String apP = ("\n Apellido  :  " + apellido);
        String emP = ("\n Email     :  " + email);
        String teP = ("\n Telefono  :  " + telefono);       
        
        return tit + idP + noP + apP + emP + teP;
    }   
}