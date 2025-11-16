package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;
import br.com.fiap.exception.BusinessRuleException;
import java.util.List;
import java.util.regex.Pattern;

public class UsuarioBO {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private void validarUsuario(UsuarioTO usuario) {
        if (usuario == null) {
            throw new BusinessRuleException("Objeto Usuario não pode ser nulo.");
        }

        if (usuario.getNmUsuario() == null || usuario.getNmUsuario().trim().isEmpty()) {
            throw new BusinessRuleException("O nome do usuário é obrigatório.");
        }

        if (usuario.getNmUsuario().trim().length() < 3) {
            throw new BusinessRuleException("O nome do usuário deve ter no mínimo 3 caracteres.");
        }

        if (usuario.getDsEmail() == null || usuario.getDsEmail().trim().isEmpty()) {
            throw new BusinessRuleException("O e-mail é obrigatório.");
        }

        if (!EMAIL_PATTERN.matcher(usuario.getDsEmail()).matches()) {
            throw new BusinessRuleException("O formato do e-mail é inválido.");
        }

        if (usuario.getDsSenha() == null || usuario.getDsSenha().trim().isEmpty()) {
            throw new BusinessRuleException("A senha é obrigatória.");
        }

        if (usuario.getDsSenha().length() < 6) {
            throw new BusinessRuleException("A senha deve ter no mínimo 6 caracteres.");
        }

        if (usuario.getNrPontosGamificacao() != null && usuario.getNrPontosGamificacao() < 0) {
            throw new BusinessRuleException("Os pontos de gamificação não podem ser negativos.");
        }

        if (usuario.getStUsuario() == null || !usuario.getStUsuario().matches("ATIVO|INATIVO|BLOQUEADO")) {
            throw new BusinessRuleException("Status do usuário inválido. Deve ser 'ATIVO', 'INATIVO' ou 'BLOQUEADO'.");
        }
    }

    public List<UsuarioTO> findAll() {
        return usuarioDAO.findAll();
    }

    public UsuarioTO findById(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessRuleException("ID para consulta de Usuário é inválido.");
        }
        return usuarioDAO.findById(id);
    }

    public UsuarioTO save(UsuarioTO usuario) {
        validarUsuario(usuario);
        return usuarioDAO.save(usuario);
    }

    public boolean delete(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessRuleException("ID para exclusão de Usuário é inválido.");
        }
        return usuarioDAO.delete(id);
    }

    public UsuarioTO update(UsuarioTO usuario) {
        if (usuario.getIdUsuario() == null || usuario.getIdUsuario() <= 0) {
            throw new BusinessRuleException("ID do Usuário é inválido para atualização.");
        }
        validarUsuario(usuario);
        return usuarioDAO.update(usuario);
    }
}