package br.com.fiap.dao;

import br.com.fiap.to.UsuarioAudioTO;
import br.com.fiap.exception.DAOException;
import br.com.fiap.exception.IdNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioAudioDAO {

    private static final String TABLE_NAME = "T_DDD_USUARIO_AUDIO";

    private UsuarioAudioTO mapResultSetToTO(ResultSet rs) throws SQLException {
        UsuarioAudioTO ua = new UsuarioAudioTO();
        ua.setIdUsuario(rs.getLong("ID_USUARIO"));
        ua.setIdAudio(rs.getLong("ID_AUDIO"));
        ua.setStFavorito(rs.getString("ST_FAVORITO"));
        return ua;
    }

    public List<UsuarioAudioTO> findAll() {
        List<UsuarioAudioTO> favoritos = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID_USUARIO, ID_AUDIO";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                favoritos.add(mapResultSetToTO(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Erro na consulta (findAll UsuarioAudio): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }

        return favoritos;
    }

    public UsuarioAudioTO findByIds(Long idUsuario, Long idAudio) {
        UsuarioAudioTO ua = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_USUARIO = ? AND ID_AUDIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUsuario);
            ps.setLong(2, idAudio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ua = mapResultSetToTO(rs);
                } else {
                    throw new IdNotFoundException("UsuarioAudio com ID_USUARIO " + idUsuario + " e ID_AUDIO " + idAudio + " não encontrado.");
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Erro na consulta (findByIds UsuarioAudio): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }

        return ua;
    }

    public UsuarioAudioTO save(UsuarioAudioTO ua) {
        String sql = "INSERT INTO " + TABLE_NAME +
                " (ID_USUARIO, ID_AUDIO, ST_FAVORITO) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, ua.getIdUsuario());
            ps.setLong(2, ua.getIdAudio());
            ps.setString(3, ua.getStFavorito());

            if (ps.executeUpdate() == 0) {
                throw new DAOException("Erro ao salvar (UsuarioAudio): Nenhuma linha afetada.", null);
            }
            return ua;
        } catch (SQLException e) {
            throw new DAOException("Erro ao salvar (UsuarioAudio): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public boolean delete(Long idUsuario, Long idAudio) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_USUARIO = ? AND ID_AUDIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUsuario);
            ps.setLong(2, idAudio);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new IdNotFoundException("UsuarioAudio com ID_USUARIO " + idUsuario + " e ID_AUDIO " + idAudio + " não encontrado para exclusão.");
            }
            return true;
        } catch (SQLException e) {
            throw new DAOException("Erro ao excluir (UsuarioAudio): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public UsuarioAudioTO update(UsuarioAudioTO ua) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET ST_FAVORITO=? " +
                "WHERE ID_USUARIO=? AND ID_AUDIO=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, ua.getStFavorito());
            ps.setLong(2, ua.getIdUsuario());
            ps.setLong(3, ua.getIdAudio());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new IdNotFoundException("UsuarioAudio com ID_USUARIO " + ua.getIdUsuario() + " e ID_AUDIO " + ua.getIdAudio() + " não encontrado para atualização.");
            }
            return ua;
        } catch (SQLException e) {
            throw new DAOException("Erro ao atualizar (UsuarioAudio): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }
}