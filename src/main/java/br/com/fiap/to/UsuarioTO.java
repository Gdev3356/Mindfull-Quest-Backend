package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class UsuarioTO {

    private Long idUsuario;

    @NotBlank(message = "O nome do usuário é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nmUsuario;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    private String dsEmail;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 64, message = "A senha deve ter entre 6 e 64 caracteres")
    private String dsSenha;

    @Min(value = 0, message = "Os pontos não podem ser negativos")
    private Integer nrPontosGamificacao;

    @NotBlank(message = "O status do usuário é obrigatório")
    @Pattern(regexp = "A|I|B", message = "Status inválido. Use: A (Ativo), I (Inativo) ou B (Bloqueado)")
    private String stUsuario;

    private Long idAvatarAtivo;

    public UsuarioTO() {}

    public UsuarioTO(Long idUsuario, String nmUsuario, String dsEmail, String dsSenha,
                     Integer nrPontosGamificacao, String stUsuario, Long idAvatarAtivo) {
        this.idUsuario = idUsuario;
        this.nmUsuario = nmUsuario;
        this.dsEmail = dsEmail;
        this.dsSenha = dsSenha;
        this.nrPontosGamificacao = nrPontosGamificacao;
        this.stUsuario = stUsuario;
        this.idAvatarAtivo = idAvatarAtivo;
    }

    // Getters e Setters
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public String getNmUsuario() { return nmUsuario; }
    public void setNmUsuario(String nmUsuario) { this.nmUsuario = nmUsuario; }
    public String getDsEmail() { return dsEmail; }
    public void setDsEmail(String dsEmail) { this.dsEmail = dsEmail; }
    public String getDsSenha() { return dsSenha; }
    public void setDsSenha(String dsSenha) { this.dsSenha = dsSenha; }
    public Integer getNrPontosGamificacao() { return nrPontosGamificacao; }
    public void setNrPontosGamificacao(Integer nrPontosGamificacao) { this.nrPontosGamificacao = nrPontosGamificacao; }
    public String getStUsuario() { return stUsuario; }
    public void setStUsuario(String stUsuario) { this.stUsuario = stUsuario; }
    public Long getIdAvatarAtivo() { return idAvatarAtivo; }
    public void setIdAvatarAtivo(Long idAvatarAtivo) { this.idAvatarAtivo = idAvatarAtivo; }
}