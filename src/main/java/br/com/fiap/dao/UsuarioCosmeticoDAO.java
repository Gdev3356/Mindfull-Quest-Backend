package br.com.fiap.dao;

import br.com.fiap.to.UsuarioCosmeticoTO;
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
            System.out.println("Erro na consulta (findAll UsuarioCosmetico): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return aquisicoes.isEmpty() ? null : aquisicoes;
    }

    public UsuarioCosmeticoTO findById(Long id) {
        UsuarioCosmeticoTO uc = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_AQUISICAO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    uc = mapResultSetToTO(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById UsuarioCosmetico): " + e.getMessage());
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

            if (ps.executeUpdate() > 0) {
                return uc;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (UsuarioCosmetico): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_AQUISICAO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (UsuarioCosmetico): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
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

            if (ps.executeUpdate() > 0) {
                return uc;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (UsuarioCosmetico): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }
}