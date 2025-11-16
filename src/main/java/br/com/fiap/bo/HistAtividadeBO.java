package br.com.fiap.bo;

import br.com.fiap.dao.HistAtividadeDAO;
import br.com.fiap.to.HistAtividadeTO;
import br.com.fiap.exception.BusinessRuleException;
import java.util.List;

public class HistAtividadeBO {
    private final HistAtividadeDAO histAtividadeDAO = new HistAtividadeDAO();

    private void validarHistAtividade(HistAtividadeTO hist) {
        if (hist == null) {
            throw new BusinessRuleException("Objeto HistAtividade não pode ser nulo.");
        }

        if (hist.getIdUsuario() == null || hist.getIdUsuario() <= 0) {
            throw new BusinessRuleException("ID do Usuário é obrigatório e deve ser positivo.");
        }

        if (hist.getIdMedida() == null || hist.getIdMedida() <= 0) {
            throw new BusinessRuleException("ID da Medida é obrigatório e deve ser positivo.");
        }

        if (hist.getDtConclusao() != null && hist.getDtConclusao().after(new java.util.Date())) {
            throw new BusinessRuleException("A Data de Conclusão não pode ser uma data futura.");
        }

        if (hist.getNrPontosGanhos() != null && hist.getNrPontosGanhos() < 0) {
            throw new BusinessRuleException("Os pontos ganhos não podem ser negativos.");
        }

        if (hist.getNrPontosGanhos() != null && hist.getNrPontosGanhos() > 100) {
            throw new BusinessRuleException("Os pontos ganhos não podem exceder 100 pontos por atividade.");
        }
    }

    public List<HistAtividadeTO> findAll() {
        return histAtividadeDAO.findAll();
    }

    public HistAtividadeTO findById(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessRuleException("ID para consulta de HistAtividade é inválido.");
        }
        return histAtividadeDAO.findById(id);
    }

    public HistAtividadeTO save(HistAtividadeTO hist) {
        validarHistAtividade(hist);
        return histAtividadeDAO.save(hist);
    }

    public boolean delete(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessRuleException("ID para exclusão de HistAtividade é inválido.");
        }
        return histAtividadeDAO.delete(id);
    }

    public HistAtividadeTO update(HistAtividadeTO hist) {
        if (hist.getIdHistAtividades() == null || hist.getIdHistAtividades() <= 0) {
            throw new BusinessRuleException("ID do Histórico de Atividade é inválido para atualização.");
        }
        validarHistAtividade(hist);
        return histAtividadeDAO.update(hist);
    }
}