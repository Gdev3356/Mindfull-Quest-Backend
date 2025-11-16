package br.com.fiap.bo;

import br.com.fiap.dao.RecursoAudioDAO;
import br.com.fiap.to.RecursoAudioTO;
import br.com.fiap.exception.BusinessRuleException;
import java.util.List;

public class RecursoAudioBO {
    private final RecursoAudioDAO recursoAudioDAO = new RecursoAudioDAO();

    private void validarRecursoAudio(RecursoAudioTO audio) {
        if (audio == null) {
            throw new BusinessRuleException("Objeto RecursoAudio não pode ser nulo.");
        }

        if (audio.getNmAudio() == null || audio.getNmAudio().trim().isEmpty()) {
            throw new BusinessRuleException("O nome do áudio é obrigatório.");
        }

        if (audio.getDsCaminhoArquivo() == null || audio.getDsCaminhoArquivo().trim().isEmpty()) {
            throw new BusinessRuleException("O caminho do arquivo de áudio é obrigatório.");
        }

        if (audio.getNrDuracaoSeg() != null && audio.getNrDuracaoSeg() <= 0) {
            throw new BusinessRuleException("A duração do áudio deve ser maior que zero.");
        }

        if (audio.getNrDuracaoSeg() != null && audio.getNrDuracaoSeg() > 3600) {
            throw new BusinessRuleException("A duração do áudio não pode exceder 3600 segundos (1 hora).");
        }

        if (audio.getTpAudio() == null || !audio.getTpAudio().matches("MEDITACAO|RESPIRACAO|MUSICA|PODCAST")) {
            throw new BusinessRuleException("Tipo de Áudio inválido. Deve ser 'MEDITACAO', 'RESPIRACAO', 'MUSICA' ou 'PODCAST'.");
        }
    }

    public List<RecursoAudioTO> findAll() {
        return recursoAudioDAO.findAll();
    }

    public RecursoAudioTO findById(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessRuleException("ID para consulta de Recurso de Áudio é inválido.");
        }
        return recursoAudioDAO.findById(id);
    }

    public RecursoAudioTO save(RecursoAudioTO audio) {
        validarRecursoAudio(audio);
        return recursoAudioDAO.save(audio);
    }

    public boolean delete(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessRuleException("ID para exclusão de Recurso de Áudio é inválido.");
        }
        return recursoAudioDAO.delete(id);
    }

    public RecursoAudioTO update(RecursoAudioTO audio) {
        if (audio.getIdAudio() == null || audio.getIdAudio() <= 0) {
            throw new BusinessRuleException("ID do Recurso de Áudio é inválido para atualização.");
        }
        validarRecursoAudio(audio);
        return recursoAudioDAO.update(audio);
    }
}