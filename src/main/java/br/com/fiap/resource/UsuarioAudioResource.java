package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioAudioBO;
import br.com.fiap.to.UsuarioAudioTO;
import br.com.fiap.exception.BusinessRuleException;
import br.com.fiap.exception.DAOException;
import br.com.fiap.exception.IdNotFoundException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/usuario-audios")
public class UsuarioAudioResource {
    private final UsuarioAudioBO usuarioAudioBO = new UsuarioAudioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            List<UsuarioAudioTO> resultado = usuarioAudioBO.findAll();
            return Response.ok(resultado).build();
        } catch (DAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @GET
    @Path("/{idUsuario}/{idAudio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIds(@PathParam("idUsuario") Long idUsuario, @PathParam("idAudio") Long idAudio) {
        try {
            UsuarioAudioTO resultado = usuarioAudioBO.findByIds(idUsuario, idAudio);
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
    public Response save(@Valid UsuarioAudioTO usuarioAudio) {
        try {
            UsuarioAudioTO resultado = usuarioAudioBO.save(usuarioAudio);
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
    @Path("/{idUsuario}/{idAudio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idUsuario") Long idUsuario, @PathParam("idAudio") Long idAudio) {
        try {
            usuarioAudioBO.delete(idUsuario, idAudio);
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
    @Path("/{idUsuario}/{idAudio}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid UsuarioAudioTO usuarioAudio,
                           @PathParam("idUsuario") Long idUsuario,
                           @PathParam("idAudio") Long idAudio) {
        try {
            usuarioAudio.setIdUsuario(idUsuario);
            usuarioAudio.setIdAudio(idAudio);
            UsuarioAudioTO resultado = usuarioAudioBO.update(usuarioAudio);
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