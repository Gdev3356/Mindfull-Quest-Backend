package br.com.fiap.dao;

import br.com.fiap.to.CosmeticoTO;
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
        cosmetico.setTpCosmetico(rs.getString("TP_COSMETICO"));
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
            System.out.println("Erro na consulta (findAll Cosmetico): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return cosmeticos.isEmpty() ? null : cosmeticos;
    }

    public CosmeticoTO findById(Long id) {
        CosmeticoTO cosmetico = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_COSMETICO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cosmetico = mapResultSetToTO(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById Cosmetico): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return cosmetico;
    }

    public CosmeticoTO save(CosmeticoTO cosmetico) {
        String sql = "INSERT INTO " + TABLE_NAME +
                " (NM_COSMETICO, DS_CAMINHO_IMAGEM, NR_CUSTO_PONTOS, TP_COSMETICO) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, cosmetico.getNmCosmetico());
            ps.setString(2, cosmetico.getDsCaminhoImagem());
            ps.setInt(3, cosmetico.getNrCustoPontos());
            ps.setString(4, cosmetico.getTpCosmetico());

            if (ps.executeUpdate() > 0) {
                return cosmetico;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (Cosmetico): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_COSMETICO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (Cosmetico): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }

    public CosmeticoTO update(CosmeticoTO cosmetico) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET NM_COSMETICO=?, DS_CAMINHO_IMAGEM=?, NR_CUSTO_PONTOS=?, TP_COSMETICO=? " +
                "WHERE ID_COSMETICO=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, cosmetico.getNmCosmetico());
            ps.setString(2, cosmetico.getDsCaminhoImagem());
            ps.setInt(3, cosmetico.getNrCustoPontos());
            ps.setString(4, cosmetico.getTpCosmetico());
            ps.setLong(5, cosmetico.getIdCosmetico());

            if (ps.executeUpdate() > 0) {
                return cosmetico;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (Cosmetico): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }
}