/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.rest.resources;

import io.swagger.annotations.Api;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author fcastillo
 */
@Path("/clientes")
@Api(value = "clientes")
public class ClienteResource {

    @GET
    public void re() {

    }

}
