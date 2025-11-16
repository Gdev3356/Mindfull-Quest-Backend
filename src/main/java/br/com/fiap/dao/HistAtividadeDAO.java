package br.com.fiap.dao;

import br.com.fiap.to.HistAtividadeTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistAtividadeDAO {

    private static final String TABLE_NAME = "T_DDD_HIST_ATIVIDADE";

    private HistAtividadeTO mapResultSetToTO(ResultSet rs) throws SQLException {
        HistAtividadeTO hist = new HistAtividadeTO();
        hist.setIdHistAtividades(rs.getLong("ID_HIST_ATIVIDADES"));
        hist.setIdUsuario(rs.getLong("ID_USUARIO"));
        hist.setIdMedida(rs.getLong("ID_MEDIDA"));
        hist.setDtConclusao(rs.getDate("DT_CONCLUSAO"));
        hist.setNrPontosGanhos(rs.getInt("NR_PONTOS_GANHOS"));
        return hist;
    }

    public List<HistAtividadeTO> findAll() {
        List<HistAtividadeTO> historico = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID_HIST_ATIVIDADES";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                historico.add(mapResultSetToTO(rs));
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findAll HistAtividade): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return historico.isEmpty() ? null : historico;
    }

    public HistAtividadeTO findById(Long id) {
        HistAtividadeTO hist = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_HIST_ATIVIDADES = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    hist = mapResultSetToTO(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById HistAtividade): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return hist;
    }

    public HistAtividadeTO save(HistAtividadeTO hist) {
        String sql = "INSERT INTO " + TABLE_NAME +
                " (ID_USUARIO, ID_MEDIDA, DT_CONCLUSAO, NR_PONTOS_GANHOS) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, hist.getIdUsuario());
            ps.setLong(2, hist.getIdMedida());
            ps.setDate(3, hist.getDtConclusao());
            ps.setInt(4, hist.getNrPontosGanhos());

            if (ps.executeUpdate() > 0) {
                return hist;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (HistAtividade): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_HIST_ATIVIDADES = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (HistAtividade): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }

    public HistAtividadeTO update(HistAtividadeTO hist) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET ID_USUARIO=?, ID_MEDIDA=?, DT_CONCLUSAO=?, NR_PONTOS_GANHOS=? " +
                "WHERE ID_HIST_ATIVIDADES=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, hist.getIdUsuario());
            ps.setLong(2, hist.getIdMedida());
            ps.setDate(3, hist.getDtConclusao());
            ps.setInt(4, hist.getNrPontosGanhos());
            ps.setLong(5, hist.getIdHistAtividades());

            if (ps.executeUpdate() > 0) {
                return hist;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (HistAtividade): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }
}