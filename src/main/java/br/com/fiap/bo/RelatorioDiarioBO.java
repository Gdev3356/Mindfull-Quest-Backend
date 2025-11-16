package br.com.fiap.bo;

import br.com.fiap.dao.RelatorioDiarioDAO;
import br.com.fiap.to.RelatorioDiarioTO;
import br.com.fiap.exception.BusinessRuleException;
import java.util.List;

public class RelatorioDiarioBO {
    private final RelatorioDiarioDAO relatorioDiarioDAO = new RelatorioDiarioDAO();

    private void validarRelatorio(RelatorioDiarioTO relatorio) {
        if (relatorio.getIdUsuario() == null || relatorio.getIdUsuario() <= 0) {
            throw new BusinessRuleException("ID do Usuário é obrigatório e deve ser positivo.");
        }

        if (relatorio.getNrNivelHumor() < 1 || relatorio.getNrNivelHumor() > 5) {
            throw new BusinessRuleException("Nível de Humor deve ser um valor entre 1 e 5.");
        }

        if (relatorio.getNrNivelEstresse() < 1 || relatorio.getNrNivelEstresse() > 5) {
            throw new BusinessRuleException("Nível de Estresse deve ser um valor entre 1 e 5.");
        }

        if (relatorio.getDtRegistro() != null && relatorio.getDtRegistro().after(new java.util.Date())) {
            throw new BusinessRuleException("A Data de Registro não pode ser uma data futura.");
        }
    }

    public List<RelatorioDiarioTO> findAll() {
        return relatorioDiarioDAO.findAll();
    }

    public RelatorioDiarioTO findById(Long id) {
        return relatorioDiarioDAO.findById(id);
    }

    public RelatorioDiarioTO save(RelatorioDiarioTO relatorio) {
        validarRelatorio(relatorio);
        return relatorioDiarioDAO.save(relatorio);
    }

    public boolean delete(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessRuleException("ID para exclusão de Relatório é inválido.");
        }
        return relatorioDiarioDAO.delete(id);
    }

    public RelatorioDiarioTO update(RelatorioDiarioTO relatorio) {
        if (relatorio.getIdRelatorio() == null || relatorio.getIdRelatorio() <= 0) {
            throw new BusinessRuleException("ID do Relatório é inválido para atualização.");
        }
        validarRelatorio(relatorio);
        return relatorioDiarioDAO.update(relatorio);
    }
}