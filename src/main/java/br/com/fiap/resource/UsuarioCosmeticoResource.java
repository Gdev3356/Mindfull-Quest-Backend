package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioCosmeticoBO;
import br.com.fiap.to.UsuarioCosmeticoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuario-cosmeticos")
public class UsuarioCosmeticoResource {
    private UsuarioCosmeticoBO usuarioCosmeticoBO = new UsuarioCosmeticoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<UsuarioCosmeticoTO> resultado = usuarioCosmeticoBO.findAll();
        Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty()) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        UsuarioCosmeticoTO resultado = usuarioCosmeticoBO.findById(id);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid UsuarioCosmeticoTO usuarioCosmetico) {
        UsuarioCosmeticoTO resultado = usuarioCosmeticoBO.save(usuarioCosmetico);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Response.ResponseBuilder response = usuarioCosmeticoBO.delete(id) ? Response.status(204) : Response.status(404);
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid UsuarioCosmeticoTO usuarioCosmetico, @PathParam("id") Long id) {
        usuarioCosmetico.setIdAquisicao(id);
        UsuarioCosmeticoTO resultado = usuarioCosmeticoBO.update(usuarioCosmetico);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(400);
        response.entity(resultado);
        return response.build();
    }
}