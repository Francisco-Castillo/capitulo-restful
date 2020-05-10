package com.fcastillo.capitulo.rest.modelo;

import java.util.Date;

/**
 *
 * @author fcastillo
 */
public class PersonaForm {

    private int id;
    private String apellido;
    private String nombre;
    private String ndocumento;
    private String fnacimiento;
    private int sexo;
    private String email;
    private String telefono;
    private String imagen;

    public PersonaForm() {
    }

    public PersonaForm(int id, String apellido, String nombre, String ndocumento, String fnacimiento, int sexo, String email, String telefono, String imagen) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.ndocumento = ndocumento;
        this.fnacimiento = fnacimiento;
        this.sexo = sexo;
        this.email = email;
        this.telefono = telefono;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNdocumento() {
        return ndocumento;
    }

    public void setNdocumento(String ndocumento) {
        this.ndocumento = ndocumento;
    }

    public String getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(String fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
