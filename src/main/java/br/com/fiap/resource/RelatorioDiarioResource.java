package br.com.fiap.resource;

import br.com.fiap.bo.RelatorioDiarioBO;
import br.com.fiap.to.RelatorioDiarioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/relatorios-diario")
public class RelatorioDiarioResource {
    private RelatorioDiarioBO relatorioDiarioBO = new RelatorioDiarioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<RelatorioDiarioTO> resultado = relatorioDiarioBO.findAll();
        Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty()) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        RelatorioDiarioTO resultado = relatorioDiarioBO.findById(id);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid RelatorioDiarioTO relatorioDiario) {
        RelatorioDiarioTO resultado = relatorioDiarioBO.save(relatorioDiario);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Response.ResponseBuilder response = relatorioDiarioBO.delete(id) ? Response.status(204) : Response.status(404);
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid RelatorioDiarioTO relatorioDiario, @PathParam("id") Long id) {
        relatorioDiario.setIdRelatorio(id);
        RelatorioDiarioTO resultado = relatorioDiarioBO.update(relatorioDiario);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(400);
        response.entity(resultado);
        return response.build();
    }
}