package br.com.fiap.resource;

import br.com.fiap.bo.MedidaCuidadoBO;
import br.com.fiap.to.MedidaCuidadoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/medidas-cuidado")
public class MedidaCuidadoResource {
    private MedidaCuidadoBO medidaCuidadoBO = new MedidaCuidadoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<MedidaCuidadoTO> resultado = medidaCuidadoBO.findAll();
        Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty()) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        MedidaCuidadoTO resultado = medidaCuidadoBO.findById(id);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid MedidaCuidadoTO medidaCuidado) {
        MedidaCuidadoTO resultado = medidaCuidadoBO.save(medidaCuidado);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Response.ResponseBuilder response = medidaCuidadoBO.delete(id) ? Response.status(204) : Response.status(404);
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid MedidaCuidadoTO medidaCuidado, @PathParam("id") Long id) {
        medidaCuidado.setIdMedida(id);
        MedidaCuidadoTO resultado = medidaCuidadoBO.update(medidaCuidado);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(400);
        response.entity(resultado);
        return response.build();
    }
}