package br.com.fiap.bo;

import br.com.fiap.dao.CosmeticoDAO;
import br.com.fiap.to.CosmeticoTO;
import br.com.fiap.exception.BusinessRuleException;
import java.util.List;

public class CosmeticoBO {
    private final CosmeticoDAO cosmeticoDAO = new CosmeticoDAO();

    private void validarCosmetico(CosmeticoTO cosmetico) {
        if (cosmetico == null) {
            throw new BusinessRuleException("Objeto Cosmetico não pode ser nulo.");
        }

        if (cosmetico.getNrCustoPontos() != null && cosmetico.getNrCustoPontos() > 500) {
            throw new BusinessRuleException("O custo do cosmético não pode exceder 500 pontos. Valor atual: " + cosmetico.getNrCustoPontos());
        }

        if (cosmetico.getNmCosmetico() == null || cosmetico.getNmCosmetico().trim().length() < 3) {
            throw new BusinessRuleException("O nome do cosmético deve ter no mínimo 3 caracteres.");
        }
    }

    public List<CosmeticoTO> findAll() {
        return cosmeticoDAO.findAll();
    }

    public CosmeticoTO findById(Long id) {
        return cosmeticoDAO.findById(id);
    }

    public CosmeticoTO save(CosmeticoTO cosmetico) {
        validarCosmetico(cosmetico);
        return cosmeticoDAO.save(cosmetico);
    }

    public boolean delete(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessRuleException("ID para exclusão de Cosmético é inválido.");
        }
        return cosmeticoDAO.delete(id);
    }

    public CosmeticoTO update(CosmeticoTO cosmetico) {
        if (cosmetico.getIdCosmetico() == null || cosmetico.getIdCosmetico() <= 0) {
            throw new BusinessRuleException("ID do Cosmético é inválido para atualização.");
        }
        validarCosmetico(cosmetico);
        return cosmeticoDAO.update(cosmetico);
    }
}