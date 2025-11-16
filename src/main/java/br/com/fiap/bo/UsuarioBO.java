package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;
import java.util.List;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO;

    public List<UsuarioTO> findAll() {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findAll();
    }

    public UsuarioTO findById(Long id) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findById(id);
    }

    public UsuarioTO save(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.save(usuario);
    }

    public boolean delete(Long id) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.delete(id);
    }

    public UsuarioTO update(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.update(usuario);
    }
}