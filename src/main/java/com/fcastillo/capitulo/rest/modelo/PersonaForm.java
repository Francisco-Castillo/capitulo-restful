package com.fcastillo.capitulo.rest.modelo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.ws.rs.QueryParam;

/**
 *
 * @author fcastillo
 */
@ApiModel(value = "PersonaFormModel", description = "descripcion")
public class PersonaForm {

    @ApiModelProperty(position = 1)
    private int id;
    @ApiModelProperty(position = 2, required = true)
    private String apellido;
    @ApiModelProperty(position = 3, required = true)
    private String nombre;
    @ApiModelProperty(position = 4, required = true)
    private String ndocumento;
    @ApiModelProperty(position = 5,required = true)
    private Date fnacimiento;
    @ApiModelProperty(position = 6,dataType = "integer",allowableValues = "1,2")
    private int sexo;
    @ApiModelProperty(position = 6)
    private String email;
    @ApiModelProperty(position = 7)
    private String telefono;
    @ApiModelProperty(position = 8, dataType = "string")
    private String imagen;

    public PersonaForm() {
    }

    public PersonaForm(int id, String apellido, String nombre, String ndocumento, Date fnacimiento, int sexo, String email, String telefono, String imagen) {
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

    public Date getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(Date fnacimiento) {
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
