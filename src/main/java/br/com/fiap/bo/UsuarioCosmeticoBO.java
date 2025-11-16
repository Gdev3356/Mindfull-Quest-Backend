package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioCosmeticoDAO;
import br.com.fiap.to.UsuarioCosmeticoTO;
import java.util.List;

public class UsuarioCosmeticoBO {
    private UsuarioCosmeticoDAO usuarioCosmeticoDAO;

    public List<UsuarioCosmeticoTO> findAll() {
        usuarioCosmeticoDAO = new UsuarioCosmeticoDAO();
        return usuarioCosmeticoDAO.findAll();
    }

    public UsuarioCosmeticoTO findById(Long id) {
        usuarioCosmeticoDAO = new UsuarioCosmeticoDAO();
        return usuarioCosmeticoDAO.findById(id);
    }

    public UsuarioCosmeticoTO save(UsuarioCosmeticoTO uc) {
        usuarioCosmeticoDAO = new UsuarioCosmeticoDAO();
        return usuarioCosmeticoDAO.save(uc);
    }

    public boolean delete(Long id) {
        usuarioCosmeticoDAO = new UsuarioCosmeticoDAO();
        return usuarioCosmeticoDAO.delete(id);
    }

    public UsuarioCosmeticoTO update(UsuarioCosmeticoTO uc) {
        usuarioCosmeticoDAO = new UsuarioCosmeticoDAO();
        return usuarioCosmeticoDAO.update(uc);
    }
}