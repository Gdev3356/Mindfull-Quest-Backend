package br.com.fiap.bo;

import br.com.fiap.dao.RelatorioDiarioDAO;
import br.com.fiap.to.RelatorioDiarioTO;
import java.util.List;

public class RelatorioDiarioBO {
    private RelatorioDiarioDAO relatorioDiarioDAO;

    public List<RelatorioDiarioTO> findAll() {
        relatorioDiarioDAO = new RelatorioDiarioDAO();
        return relatorioDiarioDAO.findAll();
    }

    public RelatorioDiarioTO findById(Long id) {
        relatorioDiarioDAO = new RelatorioDiarioDAO();
        return relatorioDiarioDAO.findById(id);
    }

    public RelatorioDiarioTO save(RelatorioDiarioTO relatorio) {
        relatorioDiarioDAO = new RelatorioDiarioDAO();
        return relatorioDiarioDAO.save(relatorio);
    }

    public boolean delete(Long id) {
        relatorioDiarioDAO = new RelatorioDiarioDAO();
        return relatorioDiarioDAO.delete(id);
    }

    public RelatorioDiarioTO update(RelatorioDiarioTO relatorio) {
        relatorioDiarioDAO = new RelatorioDiarioDAO();
        return relatorioDiarioDAO.update(relatorio);
    }
}