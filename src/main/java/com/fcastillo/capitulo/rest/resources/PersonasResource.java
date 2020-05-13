/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.rest.resources;

import com.fcastillo.capitulo.rest.ejb.PersonasFacadeLocal;
import com.fcastillo.capitulo.rest.entity.Personas;
import com.fcastillo.capitulo.rest.excepciones.ConflictException;
import com.fcastillo.capitulo.rest.excepciones.ErrorMessage;
import com.fcastillo.capitulo.rest.excepciones.NotFoundException;
import com.fcastillo.capitulo.rest.modelo.PersonaForm;
import com.fcastillo.capitulo.rest.utilidades.Utilidades;
import com.fcastillo.capitulo.rest.utilidades.Validacion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
@Path("/personas")
@Api(value = "personas")
@RequestScoped
public class PersonasResource {

    @EJB
    PersonasFacadeLocal personaEJB;

    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Insertar una nueva persona")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 409, message = "Conflict")
    })
    public Response crear(@ApiParam(name = "form", value = "Formulario persona", required = true) PersonaForm personaForm) {

        Personas persona = personaEJB.findByDocumento(personaForm.getNdocumento());
        if (persona != null) {
            ErrorMessage em = new ErrorMessage(
                    Response.Status.CONFLICT, Response.Status.CONFLICT.getStatusCode(),
                    "Ya se encuentra registrada una persona con el dni " + personaForm.getNdocumento()
            );
            throw new ConflictException(em);
        }

        // seteamos los valores
        persona = new Personas();
        persona.setApellido(personaForm.getApellido());
        persona.setNombre(personaForm.getNombre());
        persona.setNdocumento(personaForm.getNdocumento());
        persona.setFnacimiento(personaForm.getFnacimiento());
        persona.setSexo(personaForm.getSexo());
        persona.setEmail(personaForm.getEmail());
        persona.setTelefono(personaForm.getTelefono());
        persona.setImagen(personaForm.getImagen());

        personaEJB.create(persona);
        JsonObjectBuilder respuesta = Json.createObjectBuilder().add("mensaje",
                Json.createObjectBuilder().add("titulo", "¡Buen trabajo!")
                        .add("descripcion", "Se registró exitosamente a "
                                + personaForm.getApellido().concat(", ").concat(personaForm.getNombre())));
        return Response.status(Response.Status.CREATED).entity(respuesta.build()).build();
    }

    @GET
    @Path("/getlista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna un listado de personas")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "start", value = "Desplazamiento a partir del primer registro", required = false, dataType = "integer", paramType = "query", defaultValue = "0"),
        @ApiImplicitParam(name = "size", value = "Cant. de registros a mostrar", required = false, dataType = "integer", paramType = "query", defaultValue = "10"),
        @ApiImplicitParam(name = "search", value = "Filtrar por apellido", required = false, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "sortBy", value = "Atributo por el cual se desea ordenar", required = false, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "sortOrder", value = "Tipo de ordenamiento", required = false, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessage.class),
        @ApiResponse(code = 404, message = "Not Found", response = ErrorMessage.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorMessage.class)
    })
    public Response findByParams(@Context UriInfo uriInfo) {

        // Creamos los objetos 
        JsonObjectBuilder respuesta = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();

        // Capturamos parametros de tipo query
        String sStart = uriInfo.getQueryParameters().getFirst("start");
        String sSize = uriInfo.getQueryParameters().getFirst("size");
        String search = uriInfo.getQueryParameters().getFirst("search");
        String sortBy = uriInfo.getQueryParameters().getFirst("sortBy");
        String sortOrder = uriInfo.getQueryParameters().getFirst("sortOrder");
        Map<String, Object> map = new HashMap<>();
        map.put("search", search);

        // Establecemos valor por defecto en caso de que las variables sean null
        int start = sStart == null ? 0 : Integer.parseInt(sStart);
        int size = sSize == null ? 10 : Integer.parseInt(sSize);

        // Realizamos consulta
        List<Personas> lstPersonas = personaEJB.findByParams(start, size, sortBy, sortOrder, map);

        if (lstPersonas.isEmpty()) {
            ErrorMessage em = new ErrorMessage(Response.Status.NOT_FOUND, Response.Status.NOT_FOUND.getStatusCode(), "No se encontraron registros");
            throw new NotFoundException(em);
        }

        // Recorremos el listado de personas y construimos el arreglo JSON
        lstPersonas.stream().forEach(x -> {
            jab.add(Json.createObjectBuilder()
                    .add("id", x.getId())
                    .add("apellido", x.getApellido())
                    .add("nombre", x.getNombre())
                    .add("documento", x.getNdocumento())
                    .add("fnacimiento", Utilidades.ISO8601(x.getFnacimiento()))
                    .add("sexo", x.getSexo())
                    .add("email", x.getEmail())
                    .add("telefono", x.getTelefono())
                    .add("imagen", Validacion.defaultValue(x.getImagen(), "S/D")));
        });

        // Construimos la respuesta
        respuesta.add("status", Json.createObjectBuilder()
                .add("statusCode", Response.Status.OK.getStatusCode()))
                .add("totalRows", personaEJB.count())
                .add("personas", jab);

        return Response.status(Response.Status.OK).entity(respuesta.build()).build();
    }

    @DELETE
    @Path("/eliminar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Elimina una persona")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    public Response eliminar(@ApiParam(value = "id de persona", required = true) @PathParam("id") int id) {
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
                        .add("mensaje", "Se eliminó correctamente a " + persona.getApellidoNombre()));
        return Response.status(Response.Status.OK).entity(respuesta.build()).build();
    }

    @PUT
    @Path("/cambiar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Actualiza la información de una persona")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 409, message = "Conflict")
    })
    public Response actualizar(@ApiParam(value = "Formulario persona", name = "form", required = true) PersonaForm personaForm) {
        Personas persona = personaEJB.findByDocumento(personaForm.getNdocumento());

        if (persona == null) {
            ErrorMessage em = new ErrorMessage(Response.Status.NOT_FOUND,
                    Response.Status.NOT_FOUND.getStatusCode(), "No se encontró la persona");
            throw new NotFoundException(em);
        }
        persona.setApellido(Validacion.defaultValue(personaForm.getApellido(), persona.getApellido()));
        persona.setNombre(Validacion.defaultValue(personaForm.getNombre(), persona.getNombre()));
        persona.setNdocumento(Validacion.defaultValue(personaForm.getNdocumento(), persona.getNdocumento()));
        persona.setFnacimiento(Validacion.defaultValue(personaForm.getFnacimiento(), persona.getFnacimiento()));
        persona.setSexo(Validacion.defaultValue(personaForm.getSexo(), persona.getSexo()));
        persona.setEmail(Validacion.defaultValue(personaForm.getEmail(), persona.getEmail()));
        persona.setTelefono(Validacion.defaultValue(personaForm.getTelefono(), persona.getTelefono()));
        persona.setImagen(Validacion.defaultValue(personaForm.getImagen(), persona.getImagen()));

        personaEJB.edit(persona);

        JsonObjectBuilder job = Json.createObjectBuilder().add("mensaje",
                Json.createObjectBuilder()
                        .add("titulo", "¡Buen trabajo!")
                        .add("descripcion", "Los datos de la persona con id "
                                + persona.getId()
                                + " se actualizaron correctamente"));
        return Response.status(Response.Status.OK).entity(job.build()).build();
    }
}
