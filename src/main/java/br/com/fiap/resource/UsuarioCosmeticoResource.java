package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioCosmeticoBO;
import br.com.fiap.to.UsuarioCosmeticoTO;
import br.com.fiap.exception.BusinessRuleException;
import br.com.fiap.exception.DAOException;
import br.com.fiap.exception.IdNotFoundException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/usuario-cosmeticos")
public class UsuarioCosmeticoResource {
    private final UsuarioCosmeticoBO usuarioCosmeticoBO = new UsuarioCosmeticoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            List<UsuarioCosmeticoTO> resultado = usuarioCosmeticoBO.findAll();
            return Response.ok(resultado).build();
        } catch (DAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        try {
            UsuarioCosmeticoTO resultado = usuarioCosmeticoBO.findById(id);
            return Response.ok(resultado).build();
        } catch (IdNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage())).build();
        } catch (BusinessRuleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage())).build();
        } catch (DAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid UsuarioCosmeticoTO usuarioCosmetico) {
        try {
            UsuarioCosmeticoTO resultado = usuarioCosmeticoBO.save(usuarioCosmetico);
            return Response.status(Response.Status.CREATED).entity(resultado).build();
        } catch (BusinessRuleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage())).build();
        } catch (DAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        try {
            usuarioCosmeticoBO.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IdNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage())).build();
        } catch (BusinessRuleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage())).build();
        } catch (DAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid UsuarioCosmeticoTO usuarioCosmetico, @PathParam("id") Long id) {
        try {
            usuarioCosmetico.setIdAquisicao(id);
            UsuarioCosmeticoTO resultado = usuarioCosmeticoBO.update(usuarioCosmetico);
            return Response.ok(resultado).build();
        } catch (IdNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage())).build();
        } catch (BusinessRuleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage())).build();
        } catch (DAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    public static class ErrorResponse {
        private String message;
        public ErrorResponse(String message) { this.message = message; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}