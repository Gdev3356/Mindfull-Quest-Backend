package br.com.fiap.resource;

import br.com.fiap.bo.CosmeticoBO;
import br.com.fiap.to.CosmeticoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cosmeticos")
public class CosmeticoResource {
    private CosmeticoBO cosmeticoBO = new CosmeticoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<CosmeticoTO> resultado = cosmeticoBO.findAll();
        Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty()) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        CosmeticoTO resultado = cosmeticoBO.findById(id);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid CosmeticoTO cosmetico) {
        CosmeticoTO resultado = cosmeticoBO.save(cosmetico);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Response.ResponseBuilder response = cosmeticoBO.delete(id) ? Response.status(204) : Response.status(404);
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid CosmeticoTO cosmetico, @PathParam("id") Long id) {
        cosmetico.setIdCosmetico(id);
        CosmeticoTO resultado = cosmeticoBO.update(cosmetico);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(400);
        response.entity(resultado);
        return response.build();
    }
}