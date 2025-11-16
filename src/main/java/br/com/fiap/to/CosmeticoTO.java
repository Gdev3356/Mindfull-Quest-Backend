package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CosmeticoTO {

    private Long idCosmetico;

    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nmCosmetico;

    @Size(max = 500, message = "O caminho da imagem deve ter no máximo 500 caracteres")
    private String dsCaminhoImagem;

    private Integer nrCustoPontos;

    @Size(max = 20, message = "O tipo deve ter no máximo 20 caracteres")
    private String tpCosmetico;

    // Construtores
    public CosmeticoTO() {}

    public CosmeticoTO(Long idCosmetico, String nmCosmetico, String dsCaminhoImagem, Integer nrCustoPontos, String tpCosmetico) {
        this.idCosmetico = idCosmetico;
        this.nmCosmetico = nmCosmetico;
        this.dsCaminhoImagem = dsCaminhoImagem;
        this.nrCustoPontos = nrCustoPontos;
        this.tpCosmetico = tpCosmetico;
    }

    // Getters e Setters
    public Long getIdCosmetico() { return idCosmetico; }
    public void setIdCosmetico(Long idCosmetico) { this.idCosmetico = idCosmetico; }
    public String getNmCosmetico() { return nmCosmetico; }
    public void setNmCosmetico(String nmCosmetico) { this.nmCosmetico = nmCosmetico; }
    public String getDsCaminhoImagem() { return dsCaminhoImagem; }
    public void setDsCaminhoImagem(String dsCaminhoImagem) { this.dsCaminhoImagem = dsCaminhoImagem; }
    public Integer getNrCustoPontos() { return nrCustoPontos; }
    public void setNrCustoPontos(Integer nrCustoPontos) { this.nrCustoPontos = nrCustoPontos; }
    public String getTpCosmetico() { return tpCosmetico; }
    public void setTpCosmetico(String tpCosmetico) { this.tpCosmetico = tpCosmetico; }
}