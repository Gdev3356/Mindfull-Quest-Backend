package br.com.fiap.resource;

import br.com.fiap.bo.RecursoAudioBO;
import br.com.fiap.to.RecursoAudioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/recursos-audio")
public class RecursoAudioResource {
    private RecursoAudioBO recursoAudioBO = new RecursoAudioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<RecursoAudioTO> resultado = recursoAudioBO.findAll();
        Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty()) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        RecursoAudioTO resultado = recursoAudioBO.findById(id);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid RecursoAudioTO recursoAudio) {
        RecursoAudioTO resultado = recursoAudioBO.save(recursoAudio);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Response.ResponseBuilder response = recursoAudioBO.delete(id) ? Response.status(204) : Response.status(404);
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid RecursoAudioTO recursoAudio, @PathParam("id") Long id) {
        recursoAudio.setIdAudio(id);
        RecursoAudioTO resultado = recursoAudioBO.update(recursoAudio);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(400);
        response.entity(resultado);
        return response.build();
    }
}