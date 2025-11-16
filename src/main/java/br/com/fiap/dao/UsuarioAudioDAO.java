package br.com.fiap.dao;

import br.com.fiap.to.UsuarioAudioTO;
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
            System.out.println("Erro na consulta (findAll UsuarioAudio): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return favoritos.isEmpty() ? null : favoritos;
    }

    // Adaptado para chave composta
    public UsuarioAudioTO findByIds(Long idUsuario, Long idAudio) {
        UsuarioAudioTO ua = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_USUARIO = ? AND ID_AUDIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUsuario);
            ps.setLong(2, idAudio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ua = mapResultSetToTO(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findByIds UsuarioAudio): " + e.getMessage());
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

            if (ps.executeUpdate() > 0) {
                return ua;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (UsuarioAudio): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    // Adaptado para chave composta
    public boolean delete(Long idUsuario, Long idAudio) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_USUARIO = ? AND ID_AUDIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUsuario);
            ps.setLong(2, idAudio);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (UsuarioAudio): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }

    // Adaptado para chave composta (só atualiza o ST_FAVORITO)
    public UsuarioAudioTO update(UsuarioAudioTO ua) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET ST_FAVORITO=? " +
                "WHERE ID_USUARIO=? AND ID_AUDIO=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, ua.getStFavorito());
            ps.setLong(2, ua.getIdUsuario());
            ps.setLong(3, ua.getIdAudio());

            if (ps.executeUpdate() > 0) {
                return ua;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (UsuarioAudio): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }
}