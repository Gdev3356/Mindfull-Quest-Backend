package br.com.fiap.dao;

import br.com.fiap.to.RelatorioDiarioTO;
import br.com.fiap.exception.DAOException;
import br.com.fiap.exception.IdNotFoundException;

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
            throw new DAOException("Erro na consulta (findAll RelatorioDiario): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        return relatorios;
    }

    public RelatorioDiarioTO findById(Long id) {
        RelatorioDiarioTO relatorio = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_RELATORIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    relatorio = mapResultSetToTO(rs);
                } else {
                    throw new IdNotFoundException("Relatório com ID " + id + " não encontrado.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erro na consulta (findById RelatorioDiario): " + e.getMessage(), e);
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

            if (ps.executeUpdate() == 0) {
                throw new DAOException("Erro ao salvar (RelatorioDiario): Nenhuma linha afetada.", null);
            }
            return relatorio;
        } catch (SQLException e) {
            throw new DAOException("Erro ao salvar (RelatorioDiario): " + e.getMessage(), e);

        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_RELATORIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            if (ps.executeUpdate() == 0) {
                throw new IdNotFoundException("Relatório com ID " + id + " não encontrado para exclusão.");
            }
            return true;
        } catch (SQLException e) {
            throw new DAOException("Erro ao excluir (RelatorioDiario): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
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

            if (ps.executeUpdate() == 0) {
                throw new IdNotFoundException("Relatório com ID " + relatorio.getIdRelatorio() + " não encontrado para atualização.");
            }
            return relatorio;
        } catch (SQLException e) {
            throw new DAOException("Erro ao atualizar (RelatorioDiario): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public RelatorioDiarioTO findLastByUserId(Long idUsuario) {
        RelatorioDiarioTO relatorio = null;
        String sql = "SELECT * FROM " + TABLE_NAME +
                " WHERE ID_USUARIO = ? ORDER BY DT_REGISTRO DESC FETCH NEXT 1 ROWS ONLY";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    relatorio = mapResultSetToTO(rs);
                } else {
                    throw new IdNotFoundException("Nenhum relatório encontrado para o usuário " + idUsuario);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar último relatório do usuário: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        return relatorio;
    }
}