package br.com.fiap.to;

import jakarta.validation.constraints.*;
import java.sql.Date;

public class UsuarioCosmeticoTO {

    private Long idAquisicao;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long idUsuario;

    @NotNull(message = "O ID do cosmético é obrigatório")
    private Long idCosmetico;

    private Date dtAquisicao;

    @NotBlank(message = "O status de favorito é obrigatório")
    @Pattern(regexp = "S|N", message = "Status inválido. Use: S ou N")
    @Size(max = 1, message = "O status de favorito deve ter no máximo 1 caracter")
    private String stFavorito;

    public UsuarioCosmeticoTO() {}

    public UsuarioCosmeticoTO(Long idAquisicao, Long idUsuario, Long idCosmetico,
                              Date dtAquisicao, String stFavorito) {
        this.idAquisicao = idAquisicao;
        this.idUsuario = idUsuario;
        this.idCosmetico = idCosmetico;
        this.dtAquisicao = dtAquisicao;
        this.stFavorito = stFavorito;
    }

    // Getters e Setters
    public Long getIdAquisicao() { return idAquisicao; }
    public void setIdAquisicao(Long idAquisicao) { this.idAquisicao = idAquisicao; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdCosmetico() { return idCosmetico; }
    public void setIdCosmetico(Long idCosmetico) { this.idCosmetico = idCosmetico; }
    public Date getDtAquisicao() { return dtAquisicao; }
    public void setDtAquisicao(Date dtAquisicao) { this.dtAquisicao = dtAquisicao; }
    public String getStFavorito() { return stFavorito; }
    public void setStFavorito(String stFavorito) { this.stFavorito = stFavorito; }
}