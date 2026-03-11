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

	// Obtener Usuario por nick y contraseña solo si está activo
	@Override
	public Usuario obtenerPorLogin(String nick, String password) throws SQLException {
		String sql = "SELECT * FROM usuarios WHERE nick = ? AND password = ? AND activo = TRUE";
		try (Connection conn = Conexion.getInstancia().getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, nick);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return mapearUsuario(rs);
			}
		}
		return null;
	}
	// Buscar usuario por Nick
	@Override
	public Usuario obtenerPorNick(String nick) throws SQLException {
		String sql = "SELECT * FROM usuarios WHERE nick = ?";
		try (Connection conn = Conexion.getInstancia().getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, nick);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return mapearUsuario(rs);
			}
		}
		return null;
	}
	// Buscar usuario por Email
	@Override
	public Usuario obtenerPorEmail(String email) throws SQLException {
		String sql = "SELECT * FROM usuarios WHERE email = ?";
		try (Connection conn = Conexion.getInstancia().getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return mapearUsuario(rs);
			}
		}
		return null;
	}
	// Verificar si existe el Email
	@Override
	public boolean existeEmail(String email) throws SQLException {
		String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
		try (Connection conn = Conexion.getInstancia().getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return rs.getInt(1) > 0;
			}
		}
		return false;
	}
	// Obtener Lista de Usuarios
	@Override
	public List<Usuario> listarTodos() throws SQLException {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuarios ORDER BY nick";
		try (Connection conn = Conexion.getInstancia().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				usuarios.add(mapearUsuario(rs));
			}
		}
		return usuarios;
	}
	// Insertar nuevo Usuario
	@Override
	public int insertar(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO usuarios (nick, email, password, fecha_nacimiento, activo) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = Conexion.getInstancia().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) { // permite obtener id de usuario recien creado
			ps.setString(1, usuario.getNick());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getPassword());
			ps.setDate(4, usuario.getFechaNacimiento());
			ps.setBoolean(5, usuario.isActivo());

			int filasAfectadas = ps.executeUpdate();
			if (filasAfectadas > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next())
						return rs.getInt(1);
				}
			}
		}
		return 0;
	}
	// Actualizar Usuario
	@Override
	public boolean actualizar(Usuario usuario) throws SQLException {
		String sql = "UPDATE usuarios SET nick=?, email=?, password=?, " + "fecha_nacimiento=?, activo=? WHERE id=?";
		try (Connection conn = Conexion.getInstancia().getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, usuario.getNick());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getPassword());
			ps.setDate(4, usuario.getFechaNacimiento());
			ps.setBoolean(5, usuario.isActivo());
			ps.setInt(6, usuario.getId());
			return ps.executeUpdate() > 0;
		}
	}
	// Eliminar Usuario
	@Override
	public boolean eliminar(int id) throws SQLException {
		String sql = "DELETE FROM usuarios WHERE id = ?";
		try (Connection conn = Conexion.getInstancia().getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		}
	}
	// Convertir lista de usuario en un objeto UsuarioDTO así no se repite el bloque de codigo
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