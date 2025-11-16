package br.com.fiap.dao;

import br.com.fiap.to.RecursoAudioTO;
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
            System.out.println("Erro na consulta (findAll RecursoAudio): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return audios.isEmpty() ? null : audios;
    }

    public RecursoAudioTO findById(Long id) {
        RecursoAudioTO audio = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_AUDIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    audio = mapResultSetToTO(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById RecursoAudio): " + e.getMessage());
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

            if (ps.executeUpdate() > 0) {
                return audio;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (RecursoAudio): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_AUDIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (RecursoAudio): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
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

            if (ps.executeUpdate() > 0) {
                return audio;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (RecursoAudio): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }
}