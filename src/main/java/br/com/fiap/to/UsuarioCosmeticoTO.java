package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Date;

public class UsuarioCosmeticoTO {

    private Long idAquisicao;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long idUsuario; // FK T_DDD_USUARIO

    @NotNull(message = "O ID do cosmético é obrigatório")
    private Long idCosmetico; // FK T_DDD_COSMETICO

    private Date dtAquisicao;

    @Size(max = 1, message = "O status de favorito deve ter 1 caractere (S ou N)")
    private String stFavorito;

    // Construtores
    public UsuarioCosmeticoTO() {}

    public UsuarioCosmeticoTO(Long idAquisicao, Long idUsuario, Long idCosmetico, Date dtAquisicao, String stFavorito) {
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