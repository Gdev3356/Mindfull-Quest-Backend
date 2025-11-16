package br.com.fiap.dao;

import br.com.fiap.to.CosmeticoTO;
import br.com.fiap.exception.DAOException;
import br.com.fiap.exception.IdNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CosmeticoDAO {

    private static final String TABLE_NAME = "T_DDD_COSMETICO";

    private CosmeticoTO mapResultSetToTO(ResultSet rs) throws SQLException {
        CosmeticoTO cosmetico = new CosmeticoTO();
        cosmetico.setIdCosmetico(rs.getLong("ID_COSMETICO"));
        cosmetico.setNmCosmetico(rs.getString("NM_COSMETICO"));
        cosmetico.setDsCaminhoImagem(rs.getString("DS_CAMINHO_IMAGEM"));
        cosmetico.setNrCustoPontos(rs.getInt("NR_CUSTO_PONTOS"));
        return cosmetico;
    }

    public List<CosmeticoTO> findAll() {
        List<CosmeticoTO> cosmeticos = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID_COSMETICO";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                cosmeticos.add(mapResultSetToTO(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Erro na consulta (findAll Cosmetico): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        return cosmeticos;
    }

    public CosmeticoTO findById(Long id) {
        CosmeticoTO cosmetico = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_COSMETICO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cosmetico = mapResultSetToTO(rs);
                } else {
                    // Lança exceção se não encontrar
                    throw new IdNotFoundException("Cosmético com ID " + id + " não encontrado.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erro na consulta (findById Cosmetico): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        return cosmetico;
    }

    public CosmeticoTO save(CosmeticoTO cosmetico) {
        String sql = "INSERT INTO " + TABLE_NAME +
                " (NM_COSMETICO, DS_CAMINHO_IMAGEM, NR_CUSTO_PONTOS) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, cosmetico.getNmCosmetico());
            ps.setString(2, cosmetico.getDsCaminhoImagem());
            ps.setInt(3, cosmetico.getNrCustoPontos());

            if (ps.executeUpdate() > 0) {
                return cosmetico;
            } else {
                throw new DAOException("Erro ao salvar (Cosmetico): Nenhuma linha afetada.", null);
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao salvar (Cosmetico): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_COSMETICO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new IdNotFoundException("Cosmético com ID " + id + " não encontrado para exclusão.");
            }
            return true;

        } catch (SQLException e) {
            throw new DAOException("Erro ao excluir (Cosmetico): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public CosmeticoTO update(CosmeticoTO cosmetico) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET NM_COSMETICO=?, DS_CAMINHO_IMAGEM=?, NR_CUSTO_PONTOS=? " +
                "WHERE ID_COSMETICO=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, cosmetico.getNmCosmetico());
            ps.setString(2, cosmetico.getDsCaminhoImagem());
            ps.setInt(3, cosmetico.getNrCustoPontos());
            ps.setLong(4, cosmetico.getIdCosmetico());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return cosmetico;
            } else {
                throw new IdNotFoundException("Cosmético com ID " + cosmetico.getIdCosmetico() + " não encontrado para atualização.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao atualizar (Cosmetico): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }
}