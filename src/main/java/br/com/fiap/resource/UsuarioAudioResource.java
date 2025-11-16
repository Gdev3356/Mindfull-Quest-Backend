package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioAudioBO;
import br.com.fiap.to.UsuarioAudioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuario-audios")
public class UsuarioAudioResource {
    private UsuarioAudioBO usuarioAudioBO = new UsuarioAudioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<UsuarioAudioTO> resultado = usuarioAudioBO.findAll();
        Response.ResponseBuilder response = (resultado != null && !resultado.isEmpty()) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{idUsuario}/{idAudio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIds(@PathParam("idUsuario") Long idUsuario, @PathParam("idAudio") Long idAudio) {
        UsuarioAudioTO resultado = usuarioAudioBO.findByIds(idUsuario, idAudio);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid UsuarioAudioTO usuarioAudio) {
        UsuarioAudioTO resultado = usuarioAudioBO.save(usuarioAudio);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{idUsuario}/{idAudio}")
    public Response delete(@PathParam("idUsuario") Long idUsuario, @PathParam("idAudio") Long idAudio) {
        Response.ResponseBuilder response = usuarioAudioBO.delete(idUsuario, idAudio) ? Response.status(204) : Response.status(404);
        return response.build();
    }

    @PUT
    @Path("/{idUsuario}/{idAudio}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid UsuarioAudioTO usuarioAudio, @PathParam("idUsuario") Long idUsuario, @PathParam("idAudio") Long idAudio) {
        // Garante que os IDs da URL sejam usados
        usuarioAudio.setIdUsuario(idUsuario);
        usuarioAudio.setIdAudio(idAudio);

        UsuarioAudioTO resultado = usuarioAudioBO.update(usuarioAudio);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(400);
        response.entity(resultado);
        return response.build();
    }
}