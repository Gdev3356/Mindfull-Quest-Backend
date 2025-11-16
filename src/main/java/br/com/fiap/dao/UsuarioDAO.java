package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static final String TABLE_NAME = "T_DDD_USUARIO";

    private UsuarioTO mapResultSetToTO(ResultSet rs) throws SQLException {
        UsuarioTO usuario = new UsuarioTO();
        usuario.setIdUsuario(rs.getLong("ID_USUARIO"));
        usuario.setNmUsuario(rs.getString("NM_USUARIO"));
        usuario.setDsEmail(rs.getString("DS_EMAIL"));
        usuario.setDsSenha(rs.getString("DS_SENHA"));
        usuario.setNrPontosGamificacao(rs.getInt("NR_PONTOS_GAMIFICACAO"));
        usuario.setStUsuario(rs.getString("ST_USUARIO"));
        usuario.setIdAvatarAtivo(rs.getLong("ID_AVATAR_ATIVO"));
        // Trata caso o getLong retorne 0 para FKs nulas
        if (rs.wasNull()) {
            usuario.setIdAvatarAtivo(null);
        }
        return usuario;
    }

    public List<UsuarioTO> findAll() {
        List<UsuarioTO> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID_USUARIO";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                usuarios.add(mapResultSetToTO(rs));
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findAll Usuario): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return usuarios.isEmpty() ? null : usuarios;
    }

    public UsuarioTO findById(Long id) {
        UsuarioTO usuario = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_USUARIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = mapResultSetToTO(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta (findById Usuario): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return usuario;
    }

    public UsuarioTO save(UsuarioTO usuario) {
        String sql = "INSERT INTO " + TABLE_NAME +
                " (NM_USUARIO, DS_EMAIL, DS_SENHA, NR_PONTOS_GAMIFICACAO, ST_USUARIO, ID_AVATAR_ATIVO) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNmUsuario());
            ps.setString(2, usuario.getDsEmail());
            ps.setString(3, usuario.getDsSenha());
            ps.setInt(4, usuario.getNrPontosGamificacao());
            ps.setString(5, usuario.getStUsuario());

            if (usuario.getIdAvatarAtivo() != null) {
                ps.setLong(6, usuario.getIdAvatarAtivo());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            if (ps.executeUpdate() > 0) {
                // Nota: O ID gerado pela sequence/trigger não é retornado aqui,
                // seguindo o estilo do EspecialidadeDAO.
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar (Usuario): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_USUARIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir (Usuario): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }

    public UsuarioTO update(UsuarioTO usuario) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET NM_USUARIO=?, DS_EMAIL=?, DS_SENHA=?, NR_PONTOS_GAMIFICACAO=?, ST_USUARIO=?, ID_AVATAR_ATIVO=? " +
                "WHERE ID_USUARIO=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNmUsuario());
            ps.setString(2, usuario.getDsEmail());
            ps.setString(3, usuario.getDsSenha());
            ps.setInt(4, usuario.getNrPontosGamificacao());
            ps.setString(5, usuario.getStUsuario());

            if (usuario.getIdAvatarAtivo() != null) {
                ps.setLong(6, usuario.getIdAvatarAtivo());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            ps.setLong(7, usuario.getIdUsuario());

            if (ps.executeUpdate() > 0) {
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar (Usuario): " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }
}