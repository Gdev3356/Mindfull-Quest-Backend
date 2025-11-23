package br.com.fiap.dao;

import br.com.fiap.to.CosmeticoTO;
import br.com.fiap.exception.DAOException;
import br.com.fiap.exception.IdNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CosmeticoDAO {
    private static final String TABLE_NAME = "T_DDD_COSMETICO";

    private CosmeticoTO mapResultSetToTO(ResultSet rs) throws SQLException {
        CosmeticoTO cosmetico = new CosmeticoTO();
        long id = rs.getLong("ID_COSMETICO");
        cosmetico.setIdCosmetico(id);
        String nome = rs.getString("NM_COSMETICO");
        cosmetico.setNmCosmetico(nome);
        String imagem = rs.getString("DS_CAMINHO_IMAGEM");
        cosmetico.setDsCaminhoImagem(imagem);
        int custo = rs.getInt("NR_CUSTO_PONTOS");
        if (rs.wasNull()) {
            cosmetico.setNrCustoPontos(0);
        } else {
            cosmetico.setNrCustoPontos(custo);
        }

        String tipo = rs.getString("TP_COSMETICO");
        cosmetico.setTpCosmetico(tipo);
        return cosmetico;
    }

    public List<CosmeticoTO> findAll() {
        List<CosmeticoTO> cosmeticos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT ID_COSMETICO, NM_COSMETICO, DS_CAMINHO_IMAGEM, " +
                "NR_CUSTO_PONTOS, TP_COSMETICO FROM " + TABLE_NAME +
                " ORDER BY ID_COSMETICO";
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                try {
                    CosmeticoTO cosmetico = mapResultSetToTO(rs);
                    cosmeticos.add(cosmetico);
                } catch (SQLException e) {
                    System.err.println("Erro ao mapear cosmético: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erro na consulta (findAll Cosmetico): " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionFactory.closeConnection();
        }
        return cosmeticos;
    }

    public CosmeticoTO findById(Long id) {
        CosmeticoTO cosmetico = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID_COSMETICO, NM_COSMETICO, DS_CAMINHO_IMAGEM, " +
                "NR_CUSTO_PONTOS, TP_COSMETICO FROM " + TABLE_NAME +
                " WHERE ID_COSMETICO = ?";
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                cosmetico = mapResultSetToTO(rs);
            } else {
                throw new IdNotFoundException("Cosmético com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erro na consulta (findById Cosmetico): " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionFactory.closeConnection();
        }
        return cosmetico;
    }

    public CosmeticoTO save(CosmeticoTO cosmetico) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO " + TABLE_NAME +
                " (NM_COSMETICO, DS_CAMINHO_IMAGEM, NR_CUSTO_PONTOS, TP_COSMETICO) " +
                "VALUES (?, ?, ?, ?)";
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cosmetico.getNmCosmetico());
            ps.setString(2, cosmetico.getDsCaminhoImagem());
            ps.setInt(3, cosmetico.getNrCustoPontos() != null ? cosmetico.getNrCustoPontos() : 0);
            ps.setString(4, cosmetico.getTpCosmetico() != null ? cosmetico.getTpCosmetico() : "AVATAR");
            if (ps.executeUpdate() == 0) {
                throw new DAOException("Erro ao salvar (Cosmetico): Nenhuma linha afetada.", null);
            }
            return cosmetico;
        } catch (SQLException e) {
            throw new DAOException("Erro ao salvar (Cosmetico): " + e.getMessage(), e);
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionFactory.closeConnection();
        }
    }

    public boolean delete(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_COSMETICO = ?";

        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new IdNotFoundException("Cosmético com ID " + id + " não encontrado para exclusão.");
            }
            return true;

        } catch (SQLException e) {
            throw new DAOException("Erro ao excluir (Cosmetico): " + e.getMessage(), e);
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionFactory.closeConnection();
        }
    }

    public CosmeticoTO update(CosmeticoTO cosmetico) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE " + TABLE_NAME +
                " SET NM_COSMETICO=?, DS_CAMINHO_IMAGEM=?, NR_CUSTO_PONTOS=?, TP_COSMETICO=? " +
                "WHERE ID_COSMETICO=?";

        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, cosmetico.getNmCosmetico());
            ps.setString(2, cosmetico.getDsCaminhoImagem());
            ps.setInt(3, cosmetico.getNrCustoPontos() != null ? cosmetico.getNrCustoPontos() : 0);
            ps.setString(4, cosmetico.getTpCosmetico() != null ? cosmetico.getTpCosmetico() : "AVATAR");
            ps.setLong(5, cosmetico.getIdCosmetico());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new IdNotFoundException("Cosmético com ID " + cosmetico.getIdCosmetico() + " não encontrado para atualização.");
            }
            return cosmetico;
        } catch (SQLException e) {
            throw new DAOException("Erro ao atualizar (Cosmetico): " + e.getMessage(), e);
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionFactory.closeConnection();
        }
    }
}