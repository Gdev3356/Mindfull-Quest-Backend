package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class UsuarioAudioTO {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long idUsuario;

    @NotNull(message = "O ID do áudio é obrigatório")
    private Long idAudio;

    @Pattern(regexp = "S|N", message = "Status inválido. Use: S ou N")
    @Size(max = 1, message = "O status de favorito deve ter no máximo 1 caracter")
    private String stFavorito;

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