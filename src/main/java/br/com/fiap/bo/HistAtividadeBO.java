package br.com.fiap.bo;

import br.com.fiap.dao.HistAtividadeDAO;
import br.com.fiap.to.HistAtividadeTO;
import java.util.List;

public class HistAtividadeBO {
    private HistAtividadeDAO histAtividadeDAO;

    public List<HistAtividadeTO> findAll() {
        histAtividadeDAO = new HistAtividadeDAO();
        return histAtividadeDAO.findAll();
    }

    public HistAtividadeTO findById(Long id) {
        histAtividadeDAO = new HistAtividadeDAO();
        return histAtividadeDAO.findById(id);
    }

    public HistAtividadeTO save(HistAtividadeTO hist) {
        histAtividadeDAO = new HistAtividadeDAO();
        return histAtividadeDAO.save(hist);
    }

    public boolean delete(Long id) {
        histAtividadeDAO = new HistAtividadeDAO();
        return histAtividadeDAO.delete(id);
    }

    public HistAtividadeTO update(HistAtividadeTO hist) {
        histAtividadeDAO = new HistAtividadeDAO();
        return histAtividadeDAO.update(hist);
    }
}