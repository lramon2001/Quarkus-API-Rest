package com.stefanini.resources;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;
import com.stefanini.entity.Login;
import com.stefanini.service.LoginService;

import javax.ws.rs.POST;
import javax.inject.Inject;
import javax.swing.JOptionPane;

@Path("/login")
public class LoginResource {

    @Inject
    LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response performLogin(Login login) throws Exception{
        return Response.status(Response.Status.CREATED).entity(loginService.authenticate(login)).build() ;
    }

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Login";
    }

    
}
