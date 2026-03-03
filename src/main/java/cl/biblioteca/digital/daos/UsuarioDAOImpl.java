package cl.biblioteca.digital.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cl.biblioteca.digital.model.Usuario;
import cl.biblioteca.digital.util.Conexion;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public Usuario obtenerPorLogin(String nick, String password) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Usuario usuario = null;

		try {
			conn = Conexion.getConexion();
			String sql = "SELECT * FROM usuarios WHERE nick = ? AND password = ? AND activo = TRUE";
			ps = conn.prepareStatement(sql);
			ps.setString(1, nick);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				usuario = mapearUsuario(rs);
			}
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarStatement(ps);
			Conexion.cerrarConexion(conn);
		}

		return usuario;
	}

	@Override
	public Usuario obtenerPorNick(String nick) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Usuario usuario = null;

		try {
			conn = Conexion.getConexion();
			String sql = "SELECT * FROM usuarios WHERE nick = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, nick);
			rs = ps.executeQuery();

			if (rs.next()) {
				usuario = mapearUsuario(rs);
			}
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarStatement(ps);
			Conexion.cerrarConexion(conn);
		}

		return usuario;
	}

	@Override
	public Usuario obtenerPorEmail(String email) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Usuario usuario = null;

		try {
			conn = Conexion.getConexion();
			String sql = "SELECT * FROM usuarios WHERE email = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				usuario = mapearUsuario(rs);
			}
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarStatement(ps);
			Conexion.cerrarConexion(conn);
		}

		return usuario;
	}

	@Override
	public boolean existeEmail(String email) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarStatement(ps);
			Conexion.cerrarConexion(conn);
		}

		return false;
	}

	@Override
	public List<Usuario> listarTodos() throws SQLException {
		List<Usuario> usuarios = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			String sql = "SELECT * FROM usuarios ORDER BY nick";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				usuarios.add(mapearUsuario(rs));
			}
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarStatement(ps);
			Conexion.cerrarConexion(conn);
		}

		return usuarios;
	}

	@Override
	public int insertar(Usuario usuario) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			String sql = "INSERT INTO usuarios (nick, email, password, fecha_nacimiento, activo) "
					+ "VALUES (?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, usuario.getNick());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getPassword());
			ps.setDate(4, usuario.getFechaNacimiento());
			ps.setBoolean(5, usuario.isActivo());

			int filasAfectadas = ps.executeUpdate();

			if (filasAfectadas > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					return rs.getInt(1); // Retorna el ID generado
				}
			}
		} finally {
			Conexion.cerrarResultSet(rs);
			Conexion.cerrarStatement(ps);
			Conexion.cerrarConexion(conn);
		}

		return 0;
	}

	@Override
	public boolean actualizar(Usuario usuario) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = Conexion.getConexion();
			String sql = "UPDATE usuarios SET nick=?, email=?, password=?, "
					+ "fecha_nacimiento=?, activo=? WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getNick());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getPassword());
			ps.setDate(4, usuario.getFechaNacimiento());
			ps.setBoolean(5, usuario.isActivo());
			ps.setInt(6, usuario.getId());

			return ps.executeUpdate() > 0;
		} finally {
			Conexion.cerrarStatement(ps);
			Conexion.cerrarConexion(conn);
		}
	}

	@Override
	public boolean eliminar(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = Conexion.getConexion();
			String sql = "DELETE FROM usuarios WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			return ps.executeUpdate() > 0;
		} finally {
			Conexion.cerrarStatement(ps);
			Conexion.cerrarConexion(conn);
		}
	}

	// Método auxiliar para mapear ResultSet a Usuario
	private Usuario mapearUsuario(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(rs.getInt("id"));
		usuario.setNick(rs.getString("nick"));
		usuario.setEmail(rs.getString("email"));
		usuario.setPassword(rs.getString("password"));
		usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
		usuario.setActivo(rs.getBoolean("activo"));
		return usuario;
	}
}