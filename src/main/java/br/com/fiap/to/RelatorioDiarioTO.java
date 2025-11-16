package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Date;

public class RelatorioDiarioTO {

    private Long idRelatorio;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long idUsuario; // FK T_DDD_USUARIO

    private Date dtRegistro;

    private Integer nrNivelHumor;

    private Integer nrNivelEstresse;

    @Size(max = 255, message = "O log deve ter no máximo 255 caracteres")
    private String dsLogSemtimento;

    // Construtores
    public RelatorioDiarioTO() {}

    public RelatorioDiarioTO(Long idRelatorio, Long idUsuario, Date dtRegistro, Integer nrNivelHumor, Integer nrNivelEstresse, String dsLogSemtimento) {
        this.idRelatorio = idRelatorio;
        this.idUsuario = idUsuario;
        this.dtRegistro = dtRegistro;
        this.nrNivelHumor = nrNivelHumor;
        this.nrNivelEstresse = nrNivelEstresse;
        this.dsLogSemtimento = dsLogSemtimento;
    }

    // Getters e Setters
    public Long getIdRelatorio() { return idRelatorio; }
    public void setIdRelatorio(Long idRelatorio) { this.idRelatorio = idRelatorio; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Date getDtRegistro() { return dtRegistro; }
    public void setDtRegistro(Date dtRegistro) { this.dtRegistro = dtRegistro; }
    public Integer getNrNivelHumor() { return nrNivelHumor; }
    public void setNrNivelHumor(Integer nrNivelHumor) { this.nrNivelHumor = nrNivelHumor; }
    public Integer getNrNivelEstresse() { return nrNivelEstresse; }
    public void setNrNivelEstresse(Integer nrNivelEstresse) { this.nrNivelEstresse = nrNivelEstresse; }
    public String getDsLogSemtimento() { return dsLogSemtimento; }
    public void setDsLogSemtimento(String dsLogSemtimento) { this.dsLogSemtimento = dsLogSemtimento; }
}