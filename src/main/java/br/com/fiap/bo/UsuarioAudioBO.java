package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioAudioDAO;
import br.com.fiap.to.UsuarioAudioTO;
import br.com.fiap.exception.BusinessRuleException;
import java.util.List;

public class UsuarioAudioBO {
    private final UsuarioAudioDAO usuarioAudioDAO = new UsuarioAudioDAO();

    private void validarUsuarioAudio(UsuarioAudioTO ua) {
        if (ua == null) {
            throw new BusinessRuleException("Objeto UsuarioAudio não pode ser nulo.");
        }

        if (ua.getIdUsuario() == null || ua.getIdUsuario() <= 0) {
            throw new BusinessRuleException("ID do Usuário é obrigatório e deve ser positivo.");
        }

        if (ua.getIdAudio() == null || ua.getIdAudio() <= 0) {
            throw new BusinessRuleException("ID do Áudio é obrigatório e deve ser positivo.");
        }

        if (ua.getStFavorito() == null || !ua.getStFavorito().matches("S|N")) {
            throw new BusinessRuleException("Status de Favorito inválido. Deve ser 'S ou 'N'.");
        }
    }

    public List<UsuarioAudioTO> findAll() {
        return usuarioAudioDAO.findAll();
    }

    public UsuarioAudioTO findByIds(Long idUsuario, Long idAudio) {
        if (idUsuario == null || idUsuario <= 0) {
            throw new BusinessRuleException("ID do Usuário é inválido.");
        }
        if (idAudio == null || idAudio <= 0) {
            throw new BusinessRuleException("ID do Áudio é inválido.");
        }
        return usuarioAudioDAO.findByIds(idUsuario, idAudio);
    }

    public UsuarioAudioTO save(UsuarioAudioTO ua) {
        validarUsuarioAudio(ua);
        return usuarioAudioDAO.save(ua);
    }

    public boolean delete(Long idUsuario, Long idAudio) {
        if (idUsuario == null || idUsuario <= 0) {
            throw new BusinessRuleException("ID do Usuário é inválido para exclusão.");
        }
        if (idAudio == null || idAudio <= 0) {
            throw new BusinessRuleException("ID do Áudio é inválido para exclusão.");
        }
        return usuarioAudioDAO.delete(idUsuario, idAudio);
    }

    public UsuarioAudioTO update(UsuarioAudioTO ua) {
        validarUsuarioAudio(ua);
        return usuarioAudioDAO.update(ua);
    }
}