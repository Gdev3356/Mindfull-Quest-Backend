package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;
import java.sql.Date;

public class HistAtividadeTO {

    private Long idHistAtividades;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long idUsuario; // FK T_DDD_USUARIO

    @NotNull(message = "O ID da medida é obrigatório")
    private Long idMedida; // FK T_DDD_MEDIDA_CUIDADO

    private Date dtConclusao;

    private Integer nrPontosGanhos;

    // Construtores
    public HistAtividadeTO() {}

    public HistAtividadeTO(Long idHistAtividades, Long idUsuario, Long idMedida, Date dtConclusao, Integer nrPontosGanhos) {
        this.idHistAtividades = idHistAtividades;
        this.idUsuario = idUsuario;
        this.idMedida = idMedida;
        this.dtConclusao = dtConclusao;
        this.nrPontosGanhos = nrPontosGanhos;
    }

    // Getters e Setters
    public Long getIdHistAtividades() { return idHistAtividades; }
    public void setIdHistAtividades(Long idHistAtividades) { this.idHistAtividades = idHistAtividades; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdMedida() { return idMedida; }
    public void setIdMedida(Long idMedida) { this.idMedida = idMedida; }
    public Date getDtConclusao() { return dtConclusao; }
    public void setDtConclusao(Date dtConclusao) { this.dtConclusao = dtConclusao; }
    public Integer getNrPontosGanhos() { return nrPontosGanhos; }
    public void setNrPontosGanhos(Integer nrPontosGanhos) { this.nrPontosGanhos = nrPontosGanhos; }
}