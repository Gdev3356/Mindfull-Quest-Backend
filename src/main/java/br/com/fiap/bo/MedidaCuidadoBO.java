package br.com.fiap.bo;

import br.com.fiap.dao.MedidaCuidadoDAO;
import br.com.fiap.to.MedidaCuidadoTO;
import br.com.fiap.exception.BusinessRuleException;
import br.com.fiap.exception.DAOException;
import br.com.fiap.exception.IdNotFoundException;

import java.util.List;

public class MedidaCuidadoBO {
    private final MedidaCuidadoDAO medidaCuidadoDAO = new MedidaCuidadoDAO();

    private void validarMedida(MedidaCuidadoTO medida) {
        if (medida == null) {
            throw new BusinessRuleException("Objeto MedidaCuidado não pode ser nulo.");
        }

        if (medida.getNrPontosGanhos() == null || medida.getNrPontosGanhos() <= 0) {
            throw new BusinessRuleException("A medida de cuidado deve ter pontos maior que zero.");
        }

        if ("PAUSA".equals(medida.getTpMedida()) && medida.getNrPontosGanhos() > 10) {
            throw new BusinessRuleException("Medidas do tipo 'PAUSA' não podem dar mais de 10 pontos.");
        }

        if (medida.getTpMedida() == null || !medida.getTpMedida().matches("MENTAL|FISICA|PAUSA")) {
            throw new BusinessRuleException("Tipo de Medida inválido. Deve ser 'MENTAL', 'FISICA' ou 'PAUSA'.");
        }

        if (medida.getDsTituloMedida() == null || medida.getDsTituloMedida().trim().isEmpty()) {
            throw new BusinessRuleException("O título da medida é obrigatório.");
        }
    }

    public List<MedidaCuidadoTO> findAll() {
        return medidaCuidadoDAO.findAll();
    }

    public MedidaCuidadoTO findById(Long id) {
        return medidaCuidadoDAO.findById(id);
    }

    public MedidaCuidadoTO save(MedidaCuidadoTO medida) {
        validarMedida(medida);
        return medidaCuidadoDAO.save(medida);
    }

    public boolean delete(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessRuleException("ID para exclusão deve ser válido.");
        }
        return medidaCuidadoDAO.delete(id);
    }

    public MedidaCuidadoTO update(MedidaCuidadoTO medida) {
        if (medida.getIdMedida() == null || medida.getIdMedida() <= 0) {
            throw new BusinessRuleException("ID da Medida é inválido para atualização.");
        }
        validarMedida(medida);
        return medidaCuidadoDAO.update(medida);
    }
}