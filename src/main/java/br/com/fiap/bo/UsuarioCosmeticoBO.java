package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioCosmeticoDAO;
import br.com.fiap.to.UsuarioCosmeticoTO;
import br.com.fiap.exception.BusinessRuleException;
import java.util.List;

public class UsuarioCosmeticoBO {
    private final UsuarioCosmeticoDAO usuarioCosmeticoDAO = new UsuarioCosmeticoDAO();

    private void validarUsuarioCosmetico(UsuarioCosmeticoTO uc) {
        if (uc == null) {
            throw new BusinessRuleException("Objeto UsuarioCosmetico não pode ser nulo.");
        }

        if (uc.getIdUsuario() == null || uc.getIdUsuario() <= 0) {
            throw new BusinessRuleException("ID do Usuário é obrigatório e deve ser positivo.");
        }

        if (uc.getIdCosmetico() == null || uc.getIdCosmetico() <= 0) {
            throw new BusinessRuleException("ID do Cosmético é obrigatório e deve ser positivo.");
        }

        if (uc.getDtAquisicao() != null && uc.getDtAquisicao().after(new java.util.Date())) {
            throw new BusinessRuleException("A Data de Aquisição não pode ser uma data futura.");
        }

        if (uc.getStFavorito() == null || !uc.getStFavorito().matches("S|N")) {
            throw new BusinessRuleException("Status de Favorito inválido. Deve ser 'S' ou 'N'.");
        }
    }

    public List<UsuarioCosmeticoTO> findAll() {
        return usuarioCosmeticoDAO.findAll();
    }

    public UsuarioCosmeticoTO findById(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessRuleException("ID para consulta de UsuarioCosmetico é inválido.");
        }
        return usuarioCosmeticoDAO.findById(id);
    }

    public UsuarioCosmeticoTO save(UsuarioCosmeticoTO uc) {
        validarUsuarioCosmetico(uc);
        return usuarioCosmeticoDAO.save(uc);
    }

    public boolean delete(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessRuleException("ID para exclusão de UsuarioCosmetico é inválido.");
        }
        return usuarioCosmeticoDAO.delete(id);
    }

    public UsuarioCosmeticoTO update(UsuarioCosmeticoTO uc) {
        if (uc.getIdAquisicao() == null || uc.getIdAquisicao() <= 0) {
            throw new BusinessRuleException("ID da Aquisição é inválido para atualização.");
        }
        validarUsuarioCosmetico(uc);
        return usuarioCosmeticoDAO.update(uc);
    }
}