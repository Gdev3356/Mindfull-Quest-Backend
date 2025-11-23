package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;
import br.com.fiap.exception.DAOException;
import br.com.fiap.exception.IdNotFoundException;

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

        int pontos = rs.getInt("NR_PONTOS_GAMIFICACAO");
        usuario.setNrPontosGamificacao(rs.wasNull() ? 0 : pontos);

        usuario.setStUsuario(rs.getString("ST_USUARIO"));

        Long idAvatar = rs.getLong("ID_AVATAR_ATIVO");
        usuario.setIdAvatarAtivo(rs.wasNull() ? null : idAvatar);

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
            throw new DAOException("Erro na consulta (findAll Usuario): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }

        return usuarios;
    }

    public UsuarioTO findById(Long id) {
        UsuarioTO usuario = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID_USUARIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = mapResultSetToTO(rs);
                } else {
                    throw new IdNotFoundException("Usuário com ID " + id + " não encontrado.");
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Erro na consulta (findById Usuario): " + e.getMessage(), e);
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
            ps.setInt(4, usuario.getNrPontosGamificacao() != null ? usuario.getNrPontosGamificacao() : 0);
            ps.setString(5, usuario.getStUsuario());

            if (usuario.getIdAvatarAtivo() != null) {
                ps.setLong(6, usuario.getIdAvatarAtivo());
            } else {
                ps.setNull(6, java.sql.Types.NUMERIC);  // NUMERIC para Oracle NUMBER
            }

            if (ps.executeUpdate() == 0) {
                throw new DAOException("Erro ao salvar (Usuario): Nenhuma linha afetada.", null);
            }
            return usuario;
        } catch (SQLException e) {
            throw new DAOException("Erro ao salvar (Usuario): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_USUARIO = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new IdNotFoundException("Usuário com ID " + id + " não encontrado para exclusão.");
            }
            return true;
        } catch (SQLException e) {
            throw new DAOException("Erro ao excluir (Usuario): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public UsuarioTO update(UsuarioTO usuario) {
        String sql = "UPDATE " + TABLE_NAME +
                " SET NM_USUARIO=?, DS_EMAIL=?, DS_SENHA=?, NR_PONTOS_GAMIFICACAO=?, ST_USUARIO=?, ID_AVATAR_ATIVO=? " +
                "WHERE ID_USUARIO=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNmUsuario());
            ps.setString(2, usuario.getDsEmail());
            ps.setString(3, usuario.getDsSenha());
            ps.setInt(4, usuario.getNrPontosGamificacao() != null ? usuario.getNrPontosGamificacao() : 0);
            ps.setString(5, usuario.getStUsuario());

            if (usuario.getIdAvatarAtivo() != null) {
                ps.setLong(6, usuario.getIdAvatarAtivo());
            } else {
                ps.setNull(6, java.sql.Types.NUMERIC);  // NUMERIC para Oracle NUMBER
            }

            ps.setLong(7, usuario.getIdUsuario());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new IdNotFoundException("Usuário com ID " + usuario.getIdUsuario() + " não encontrado para atualização.");
            }
            return usuario;
        } catch (SQLException e) {
            throw new DAOException("Erro ao atualizar (Usuario): " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }
}