package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class CosmeticoTO {

    private Long idCosmetico;

    @NotBlank(message = "O nome do cosmético é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nmCosmetico;

    @NotBlank(message = "O caminho da imagem é obrigatório")
    @Size(max = 500, message = "O caminho da imagem deve ter no máximo 500 caracteres")
    private String dsCaminhoImagem;

    @NotNull(message = "O custo em pontos é obrigatório")
    @Min(value = 0, message = "O custo não pode ser negativo")
    @Max(value = 500, message = "O custo não pode exceder 500 pontos")
    private Integer nrCustoPontos;

    @Size(max = 20, message = "O tipo deve ter no máximo 20 caracteres")
    private String tpCosmetico;

    public CosmeticoTO() {}

    public CosmeticoTO(Long idCosmetico, String nmCosmetico, String dsCaminhoImagem,
                       Integer nrCustoPontos, String tpCosmetico) {
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