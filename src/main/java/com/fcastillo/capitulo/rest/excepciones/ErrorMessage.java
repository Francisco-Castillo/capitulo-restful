package com.fcastillo.capitulo.rest.excepciones;

import javax.ws.rs.core.Response;

/**
 *
 * @author fcastillo
 */
public class ErrorMessage {

    public Response.Status status;
    public int codigo;
    public String descripcion;

    public ErrorMessage(Response.Status status, int codigo, String descripcion) {
        this.status = status;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

}
