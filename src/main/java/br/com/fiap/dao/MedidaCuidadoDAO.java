package br.com.fiap.dao;

import br.com.fiap.to.MedidaCuidadoTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedidaCuidadoDAO {

    private static final String TABLE_NAME = "T_DDD_MEDIDA_CUIDADO";

    private MedidaCuidadoTO mapResultSetToTO(ResultSet rs) throws SQLException {
        MedidaCuidadoTO medida = new MedidaCuidadoTO();
        medida.setIdMedida(rs.getLong("ID_MEDIDA"));
        medida.setDsTituloMedida(rs.getString("DS_TITULO_MEDIDA"));
        medida.setDsDescricaoMedida(rs.getString("DS_DESCRICAO_MEDIDA"));
        medida.setTpMedida(rs.getString("TP_MEDIDA"));
        medida.setNrPontosGanhos(rs.getInt("NR_PONTOS_GANHOS"));
        return medida;
    }

    public List<MedidaCuidadoTO> findAll() {
        List<MedidaCuidadoTO> medidas = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID_MEDIDA";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                medidas.add(mapResultSetToTO(rs));
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findAll MedidaCuidado): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return medidas.isEmpty() ? null : medidas;
    }

    public MedidaCuidadoTO findById(Long id) {
        MedidaCuidadoTO medida = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_MEDIDA = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    medida = mapResultSetToTO(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById MedidaCuidado): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return medida;
    }

    public MedidaCuidadoTO save(MedidaCuidadoTO medida) {
        String sql = "INSERT INTO " + TABLE_NAME +
                " (DS_TITULO_MEDIDA, DS_DESCRICAO_MEDIDA, TP_MEDIDA, NR_PONTOS_GANHOS) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, medida.getDsTituloMedida());
            ps.setString(2, medida.getDsDescricaoMedida());
            ps.setString(3, medida.getTpMedida());
            ps.setInt(4, medida.getNrPontosGanhos());

            if (ps.executeUpdate() > 0) {
                return medida;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (MedidaCuidado): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_MEDIDA = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (MedidaCuidado): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }

    public MedidaCuidadoTO update(MedidaCuidadoTO medida) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET DS_TITULO_MEDIDA=?, DS_DESCRICAO_MEDIDA=?, TP_MEDIDA=?, NR_PONTOS_GANHOS=? " +
                "WHERE ID_MEDIDA=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, medida.getDsTituloMedida());
            ps.setString(2, medida.getDsDescricaoMedida());
            ps.setString(3, medida.getTpMedida());
            ps.setInt(4, medida.getNrPontosGanhos());
            ps.setLong(5, medida.getIdMedida());

            if (ps.executeUpdate() > 0) {
                return medida;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (MedidaCuidado): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }
}