package br.com.fiap.bo;

import br.com.fiap.dao.RecursoAudioDAO;
import br.com.fiap.to.RecursoAudioTO;
import java.util.List;

public class RecursoAudioBO {
    private RecursoAudioDAO recursoAudioDAO;

    public List<RecursoAudioTO> findAll() {
        recursoAudioDAO = new RecursoAudioDAO();
        return recursoAudioDAO.findAll();
    }

    public RecursoAudioTO findById(Long id) {
        recursoAudioDAO = new RecursoAudioDAO();
        return recursoAudioDAO.findById(id);
    }

    public RecursoAudioTO save(RecursoAudioTO audio) {
        recursoAudioDAO = new RecursoAudioDAO();
        return recursoAudioDAO.save(audio);
    }

    public boolean delete(Long id) {
        recursoAudioDAO = new RecursoAudioDAO();
        return recursoAudioDAO.delete(id);
    }

    public RecursoAudioTO update(RecursoAudioTO audio) {
        recursoAudioDAO = new RecursoAudioDAO();
        return recursoAudioDAO.update(audio);
    }
}