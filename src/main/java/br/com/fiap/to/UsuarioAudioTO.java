package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioAudioTO {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long idUsuario; // PK, FK T_DDD_USUARIO

    @NotNull(message = "O ID do áudio é obrigatório")
    private Long idAudio; // PK, FK T_DDD_RECURSO_AUDIO

    @NotBlank(message = "O status de favorito é obrigatório")
    @Size(max = 1, message = "O status de favorito deve ter 1 caractere (S ou N)")
    private String stFavorito;

    // Construtores
    public UsuarioAudioTO() {}

    public UsuarioAudioTO(Long idUsuario, Long idAudio, String stFavorito) {
        this.idUsuario = idUsuario;
        this.idAudio = idAudio;
        this.stFavorito = stFavorito;
    }

    // Getters e Setters
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdAudio() { return idAudio; }
    public void setIdAudio(Long idAudio) { this.idAudio = idAudio; }
    public String getStFavorito() { return stFavorito; }
    public void setStFavorito(String stFavorito) { this.stFavorito = stFavorito; }
}