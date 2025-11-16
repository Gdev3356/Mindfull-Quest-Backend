package br.com.fiap.to;

import jakarta.validation.constraints.Size;

public class MedidaCuidadoTO {

    private Long idMedida;

    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    private String dsTituloMedida;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String dsDescricaoMedida;

    @Size(max = 20, message = "O tipo deve ter no máximo 20 caracteres")
    private String tpMedida;

    private Integer nrPontosGanhos;

    // Construtores
    public MedidaCuidadoTO() {}

    public MedidaCuidadoTO(Long idMedida, String dsTituloMedida, String dsDescricaoMedida, String tpMedida, Integer nrPontosGanhos) {
        this.idMedida = idMedida;
        this.dsTituloMedida = dsTituloMedida;
        this.dsDescricaoMedida = dsDescricaoMedida;
        this.tpMedida = tpMedida;
        this.nrPontosGanhos = nrPontosGanhos;
    }

    // Getters e Setters
    public Long getIdMedida() { return idMedida; }
    public void setIdMedida(Long idMedida) { this.idMedida = idMedida; }
    public String getDsTituloMedida() { return dsTituloMedida; }
    public void setDsTituloMedida(String dsTituloMedida) { this.dsTituloMedida = dsTituloMedida; }
    public String getDsDescricaoMedida() { return dsDescricaoMedida; }
    public void setDsDescricaoMedida(String dsDescricaoMedida) { this.dsDescricaoMedida = dsDescricaoMedida; }
    public String getTpMedida() { return tpMedida; }
    public void setTpMedida(String tpMedida) { this.tpMedida = tpMedida; }
    public Integer getNrPontosGanhos() { return nrPontosGanhos; }
    public void setNrPontosGanhos(Integer nrPontosGanhos) { this.nrPontosGanhos = nrPontosGanhos; }
}