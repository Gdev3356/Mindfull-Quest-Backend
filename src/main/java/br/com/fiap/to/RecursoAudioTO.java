package br.com.fiap.to;

import jakarta.validation.constraints.Size;

public class RecursoAudioTO {

    private Long idAudio;

    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nmAudio;

    @Size(max = 500, message = "O caminho do arquivo deve ter no máximo 500 caracteres")
    private String dsCaminhoArquivo;

    private Integer nrDuracaoSeg;

    @Size(max = 20, message = "O tipo deve ter no máximo 20 caracteres")
    private String tpAudio;

    // Construtores
    public RecursoAudioTO() {}

    public RecursoAudioTO(Long idAudio, String nmAudio, String dsCaminhoArquivo, Integer nrDuracaoSeg, String tpAudio) {
        this.idAudio = idAudio;
        this.nmAudio = nmAudio;
        this.dsCaminhoArquivo = dsCaminhoArquivo;
        this.nrDuracaoSeg = nrDuracaoSeg;
        this.tpAudio = tpAudio;
    }

    // Getters e Setters
    public Long getIdAudio() { return idAudio; }
    public void setIdAudio(Long idAudio) { this.idAudio = idAudio; }
    public String getNmAudio() { return nmAudio; }
    public void setNmAudio(String nmAudio) { this.nmAudio = nmAudio; }
    public String getDsCaminhoArquivo() { return dsCaminhoArquivo; }
    public void setDsCaminhoArquivo(String dsCaminhoArquivo) { this.dsCaminhoArquivo = dsCaminhoArquivo; }
    public Integer getNrDuracaoSeg() { return nrDuracaoSeg; }
    public void setNrDuracaoSeg(Integer nrDuracaoSeg) { this.nrDuracaoSeg = nrDuracaoSeg; }
    public String getTpAudio() { return tpAudio; }
    public void setTpAudio(String tpAudio) { this.tpAudio = tpAudio; }
}