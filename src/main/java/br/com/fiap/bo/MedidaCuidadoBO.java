package br.com.fiap.bo;

import br.com.fiap.dao.MedidaCuidadoDAO;
import br.com.fiap.to.MedidaCuidadoTO;
import java.util.List;

public class MedidaCuidadoBO {
    private MedidaCuidadoDAO medidaCuidadoDAO;

    public List<MedidaCuidadoTO> findAll() {
        medidaCuidadoDAO = new MedidaCuidadoDAO();
        return medidaCuidadoDAO.findAll();
    }

    public MedidaCuidadoTO findById(Long id) {
        medidaCuidadoDAO = new MedidaCuidadoDAO();
        return medidaCuidadoDAO.findById(id);
    }

    public MedidaCuidadoTO save(MedidaCuidadoTO medida) {
        medidaCuidadoDAO = new MedidaCuidadoDAO();
        return medidaCuidadoDAO.save(medida);
    }

    public boolean delete(Long id) {
        medidaCuidadoDAO = new MedidaCuidadoDAO();
        return medidaCuidadoDAO.delete(id);
    }

    public MedidaCuidadoTO update(MedidaCuidadoTO medida) {
        medidaCuidadoDAO = new MedidaCuidadoDAO();
        return medidaCuidadoDAO.update(medida);
    }
}