package br.com.fiap.dao;

import br.com.fiap.to.UsuarioCosmeticoTO;
import br.com.fiap.exception.DAOException;
import br.com.fiap.exception.IdNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioCosmeticoDAO {

    private static final String TABLE_NAME = "T_DDD_USUARIO_COSMETICO";

    private UsuarioCosmeticoTO mapResultSetToTO(ResultSet rs) throws SQLException {
        UsuarioCosmeticoTO uc = new UsuarioCosmeticoTO();
        uc.setIdAquisicao(rs.getLong("ID_AQUISICAO"));
        uc.setIdUsuario(rs.getLong("ID_USUARIO"));
        uc.setIdCosmetico(rs.getLong("ID_COSMETICO"));
        uc.setDtAquisicao(rs.getDate("DT_AQUISICAO"));
        uc.setStFavorito(rs.getString("ST_FAVORITO"));
        return uc;
    }

    public List<UsuarioCosmeticoTO> findAll() {
        List<UsuarioCosmeticoTO> aquisicoes = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID_AQUISICAO";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                aquisicoes.add(mapResultSetToTO(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Erro na consulta (findAll UsuarioCosmetico): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }

        return aquisicoes;
    }

    public UsuarioCosmeticoTO findById(Long id) {
        UsuarioCosmeticoTO uc = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_AQUISICAO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    uc = mapResultSetToTO(rs);
                } else {
                    throw new IdNotFoundException("Aquisição de Cosmético com ID " + id + " não encontrada.");
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Erro na consulta (findById UsuarioCosmetico): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }

        return uc;
    }

    public UsuarioCosmeticoTO save(UsuarioCosmeticoTO uc) {
        String sql = "INSERT INTO " + TABLE_NAME +
                " (ID_USUARIO, ID_COSMETICO, DT_AQUISICAO, ST_FAVORITO) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, uc.getIdUsuario());
            ps.setLong(2, uc.getIdCosmetico());
            ps.setDate(3, uc.getDtAquisicao());
            ps.setString(4, uc.getStFavorito());

            if (ps.executeUpdate() == 0) {
                throw new DAOException("Erro ao salvar (UsuarioCosmetico): Nenhuma linha afetada.", null);
            }
            return uc;
        } catch (SQLException e) {
            throw new DAOException("Erro ao salvar (UsuarioCosmetico): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_AQUISICAO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new IdNotFoundException("Aquisição de Cosmético com ID " + id + " não encontrada para exclusão.");
            }
            return true;
        } catch (SQLException e) {
            throw new DAOException("Erro ao excluir (UsuarioCosmetico): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public UsuarioCosmeticoTO update(UsuarioCosmeticoTO uc) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET ID_USUARIO=?, ID_COSMETICO=?, DT_AQUISICAO=?, ST_FAVORITO=? " +
                "WHERE ID_AQUISICAO=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, uc.getIdUsuario());
            ps.setLong(2, uc.getIdCosmetico());
            ps.setDate(3, uc.getDtAquisicao());
            ps.setString(4, uc.getStFavorito());
            ps.setLong(5, uc.getIdAquisicao());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new IdNotFoundException("Aquisição de Cosmético com ID " + uc.getIdAquisicao() + " não encontrada para atualização.");
            }
            return uc;
        } catch (SQLException e) {
            throw new DAOException("Erro ao atualizar (UsuarioCosmetico): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }
}