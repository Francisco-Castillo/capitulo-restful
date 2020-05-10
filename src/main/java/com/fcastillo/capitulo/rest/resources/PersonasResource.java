/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.rest.resources;

import com.fcastillo.capitulo.rest.ejb.PersonasFacadeLocal;
import com.fcastillo.capitulo.rest.entity.Personas;
import com.fcastillo.capitulo.rest.excepciones.ErrorMessage;
import com.fcastillo.capitulo.rest.excepciones.NotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author fcastillo
 */
@Path("personas")
@RequestScoped
public class PersonasResource {

    @EJB
    PersonasFacadeLocal personaEJB;

    @GET
    @Path("/getlista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByParams(@Context UriInfo uriInfo) {

        // Creamos los objetos 
        JsonObjectBuilder respuesta = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();

        // Capturamos parametros de tipo query
        String sStart = uriInfo.getQueryParameters().getFirst("start");
        String sSize = uriInfo.getQueryParameters().getFirst("size");
        String search = uriInfo.getQueryParameters().getFirst("search");
        String sortBy = uriInfo.getQueryParameters().getFirst("sortBy");
        String sortOrder = uriInfo.getQueryParameters().getFirst("order");
        Map<String, Object> map = new HashMap<>();
        map.put("search", search);

        // Establecemos valor por defecto en caso de que las variables sean null
        int start = sStart == null ? 0 : Integer.parseInt(sStart);
        int size = sSize == null ? 10 : Integer.parseInt(sSize);

        // Realizamos consulta
        List<Personas> lstPersonas = personaEJB.findByParams(start, size, sortBy, sortOrder, map);

        if (lstPersonas.isEmpty()) {

        }
        // Recorremos el listado de personas y construimos el arreglo JSON
        lstPersonas.stream().forEach(x -> {
            jab.add(Json.createObjectBuilder()
                    .add("id", x.getId())
                    .add("apellido", x.getApellido())
                    .add("nombre", x.getNombre())
                    .add("documento", x.getNdocumento())
                    .add("fnacimiento", x.getFnacimiento().toString())
                    .add("sexo", x.getSexo())
                    .add("email", x.getEmail())
                    .add("telefono", x.getTelefono()));
        });

        // Construimos la respuesta
        respuesta.add("status", Json.createObjectBuilder()
                .add("statusCode", 200))
                .add("totalRows", personaEJB.count())
                .add("personas", jab);

        return Response.status(Response.Status.OK).entity(respuesta.build()).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") int id) {
        // Buscamos a la persona
        Personas persona = personaEJB.find(id);
        
        // Si la persona buscada no existe retornamos una excepcion
        if (persona == null) {
            ErrorMessage em = new ErrorMessage(Response.Status.NOT_FOUND, Response.Status.NOT_FOUND.getStatusCode(), "No se encontró a la persona con el id " + id);
            throw new NotFoundException(em);
        }
        
        // Eliminamos
        personaEJB.remove(persona);
        
        // Construimos la respuesta
        JsonObjectBuilder respuesta = Json.createObjectBuilder()
                .add("status", Json.createObjectBuilder()
                        .add("statusCode", Response.Status.OK.getStatusCode())
                        .add("mensaje", "Se eliminó correctamente a "+persona.getApellidoNombre()));
        return Response.status(Response.Status.OK).entity(respuesta.build()).build();
    }

}
