package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioAudioDAO;
import br.com.fiap.to.UsuarioAudioTO;
import java.util.List;

public class UsuarioAudioBO {
    private UsuarioAudioDAO usuarioAudioDAO;

    public List<UsuarioAudioTO> findAll() {
        usuarioAudioDAO = new UsuarioAudioDAO();
        return usuarioAudioDAO.findAll();
    }

    public UsuarioAudioTO findByIds(Long idUsuario, Long idAudio) {
        usuarioAudioDAO = new UsuarioAudioDAO();
        return usuarioAudioDAO.findByIds(idUsuario, idAudio);
    }

    public UsuarioAudioTO save(UsuarioAudioTO ua) {
        usuarioAudioDAO = new UsuarioAudioDAO();
        return usuarioAudioDAO.save(ua);
    }

    public boolean delete(Long idUsuario, Long idAudio) {
        usuarioAudioDAO = new UsuarioAudioDAO();
        return usuarioAudioDAO.delete(idUsuario, idAudio);
    }

    public UsuarioAudioTO update(UsuarioAudioTO ua) {
        usuarioAudioDAO = new UsuarioAudioDAO();
        return usuarioAudioDAO.update(ua);
    }
}