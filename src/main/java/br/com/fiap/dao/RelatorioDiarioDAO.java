package br.com.fiap.dao;

import br.com.fiap.to.RelatorioDiarioTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDiarioDAO {

    private static final String TABLE_NAME = "T_DDD_RELATORIO_DIARIO";

    private RelatorioDiarioTO mapResultSetToTO(ResultSet rs) throws SQLException {
        RelatorioDiarioTO relatorio = new RelatorioDiarioTO();
        relatorio.setIdRelatorio(rs.getLong("ID_RELATORIO"));
        relatorio.setIdUsuario(rs.getLong("ID_USUARIO"));
        relatorio.setDtRegistro(rs.getDate("DT_REGISTRO"));
        relatorio.setNrNivelHumor(rs.getInt("NR_NIVEL_HUMOR"));
        relatorio.setNrNivelEstresse(rs.getInt("NR_NIVEL_ESTRESSE"));
        relatorio.setDsLogSemtimento(rs.getString("DS_LOG_SEMTIMENTO"));
        return relatorio;
    }

    public List<RelatorioDiarioTO> findAll() {
        List<RelatorioDiarioTO> relatorios = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID_RELATORIO";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                relatorios.add(mapResultSetToTO(rs));
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findAll RelatorioDiario): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return relatorios.isEmpty() ? null : relatorios;
    }

    public RelatorioDiarioTO findById(Long id) {
        RelatorioDiarioTO relatorio = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_RELATORIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    relatorio = mapResultSetToTO(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById RelatorioDiario): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return relatorio;
    }

    public RelatorioDiarioTO save(RelatorioDiarioTO relatorio) {
        String sql = "INSERT INTO " + TABLE_NAME +
                " (ID_USUARIO, DT_REGISTRO, NR_NIVEL_HUMOR, NR_NIVEL_ESTRESSE, DS_LOG_SEMTIMENTO) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, relatorio.getIdUsuario());
            ps.setDate(2, relatorio.getDtRegistro());
            ps.setInt(3, relatorio.getNrNivelHumor());
            ps.setInt(4, relatorio.getNrNivelEstresse());
            ps.setString(5, relatorio.getDsLogSemtimento());

            if (ps.executeUpdate() > 0) {
                return relatorio;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (RelatorioDiario): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_RELATORIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (RelatorioDiario): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }

    public RelatorioDiarioTO update(RelatorioDiarioTO relatorio) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET ID_USUARIO=?, DT_REGISTRO=?, NR_NIVEL_HUMOR=?, NR_NIVEL_ESTRESSE=?, DS_LOG_SEMTIMENTO=? " +
                "WHERE ID_RELATORIO=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, relatorio.getIdUsuario());
            ps.setDate(2, relatorio.getDtRegistro());
            ps.setInt(3, relatorio.getNrNivelHumor());
            ps.setInt(4, relatorio.getNrNivelEstresse());
            ps.setString(5, relatorio.getDsLogSemtimento());
            ps.setLong(6, relatorio.getIdRelatorio());

            if (ps.executeUpdate() > 0) {
                return relatorio;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (RelatorioDiario): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }
}