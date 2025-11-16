package br.com.fiap.dao;

import br.com.fiap.to.RecursoAudioTO;
import br.com.fiap.exception.DAOException;
import br.com.fiap.exception.IdNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecursoAudioDAO {

    private static final String TABLE_NAME = "T_DDD_RECURSO_AUDIO";

    private RecursoAudioTO mapResultSetToTO(ResultSet rs) throws SQLException {
        RecursoAudioTO audio = new RecursoAudioTO();
        audio.setIdAudio(rs.getLong("ID_AUDIO"));
        audio.setNmAudio(rs.getString("NM_AUDIO"));
        audio.setDsCaminhoArquivo(rs.getString("DS_CAMINHO_ARQUIVO"));
        audio.setNrDuracaoSeg(rs.getInt("NR_DURACAO_SEG"));
        audio.setTpAudio(rs.getString("TP_AUDIO"));
        return audio;
    }

    public List<RecursoAudioTO> findAll() {
        List<RecursoAudioTO> audios = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID_AUDIO";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                audios.add(mapResultSetToTO(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Erro na consulta (findAll RecursoAudio): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }

        return audios;
    }

    public RecursoAudioTO findById(Long id) {
        RecursoAudioTO audio = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_AUDIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    audio = mapResultSetToTO(rs);
                } else {
                    throw new IdNotFoundException("Recurso de Áudio com ID " + id + " não encontrado.");
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Erro na consulta (findById RecursoAudio): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }

        return audio;
    }

    public RecursoAudioTO save(RecursoAudioTO audio) {
        String sql = "INSERT INTO " + TABLE_NAME +
                " (NM_AUDIO, DS_CAMINHO_ARQUIVO, NR_DURACAO_SEG, TP_AUDIO) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, audio.getNmAudio());
            ps.setString(2, audio.getDsCaminhoArquivo());
            ps.setInt(3, audio.getNrDuracaoSeg());
            ps.setString(4, audio.getTpAudio());

            if (ps.executeUpdate() == 0) {
                throw new DAOException("Erro ao salvar (RecursoAudio): Nenhuma linha afetada.", null);
            }
            return audio;
        } catch (SQLException e) {
            throw new DAOException("Erro ao salvar (RecursoAudio): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_AUDIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new IdNotFoundException("Recurso de Áudio com ID " + id + " não encontrado para exclusão.");
            }
            return true;
        } catch (SQLException e) {
            throw new DAOException("Erro ao excluir (RecursoAudio): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public RecursoAudioTO update(RecursoAudioTO audio) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET NM_AUDIO=?, DS_CAMINHO_ARQUIVO=?, NR_DURACAO_SEG=?, TP_AUDIO=? " +
                "WHERE ID_AUDIO=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, audio.getNmAudio());
            ps.setString(2, audio.getDsCaminhoArquivo());
            ps.setInt(3, audio.getNrDuracaoSeg());
            ps.setString(4, audio.getTpAudio());
            ps.setLong(5, audio.getIdAudio());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new IdNotFoundException("Recurso de Áudio com ID " + audio.getIdAudio() + " não encontrado para atualização.");
            }
            return audio;
        } catch (SQLException e) {
            throw new DAOException("Erro ao atualizar (RecursoAudio): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }
}