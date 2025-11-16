package br.com.fiap.bo;

import br.com.fiap.dao.CosmeticoDAO;
import br.com.fiap.to.CosmeticoTO;
import java.util.List;

public class CosmeticoBO {
    private CosmeticoDAO cosmeticoDAO;

    public List<CosmeticoTO> findAll() {
        cosmeticoDAO = new CosmeticoDAO();
        return cosmeticoDAO.findAll();
    }

    public CosmeticoTO findById(Long id) {
        cosmeticoDAO = new CosmeticoDAO();
        return cosmeticoDAO.findById(id);
    }

    public CosmeticoTO save(CosmeticoTO cosmetico) {
        cosmeticoDAO = new CosmeticoDAO();
        return cosmeticoDAO.save(cosmetico);
    }

    public boolean delete(Long id) {
        cosmeticoDAO = new CosmeticoDAO();
        return cosmeticoDAO.delete(id);
    }

    public CosmeticoTO update(CosmeticoTO cosmetico) {
        cosmeticoDAO = new CosmeticoDAO();
        return cosmeticoDAO.update(cosmetico);
    }
}