package com.stefanini.resources;

import com.stefanini.entity.Usuario;
import com.stefanini.service.UsuarioService;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

@Path("/usuarios")
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(Usuario usuario) {
        usuarioService.create(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        usuarioService.delete(id);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id,Usuario usuario){
        usuarioService.update(usuario,id);
        return Response.status(Response.Status.OK).entity(usuario).build();
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id) throws Exception{
        return Response.status(Response.Status.OK).entity(usuarioService.findById(id)).build();
    }

    @GET
    public Response getAllUsers(){
        return Response.status(Response.Status.OK).entity(usuarioService.findAll()).build();
    }

    @GET
    @Path("/aniversarios")
    public Response getBirthdays(@QueryParam("mes") int mes){
        return Response.status(Response.Status.OK).entity(usuarioService.listBirthDays(mes)).build();
    }

    @GET
    @Path("/nomes")
    public Response getByName(@QueryParam("nome") String nome){
        return Response.status(Response.Status.OK).entity(usuarioService.listByName(nome)).build();
    }

    @GET
    @Path("/dominios")
    public Response getProviders(){
        return Response.status(Response.Status.OK).entity(usuarioService.findAllEmailproviders()).build();
    }

}
