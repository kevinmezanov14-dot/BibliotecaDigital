package cl.biblioteca.digital.daos;

import cl.biblioteca.digital.dtos.LibroDTO;
import cl.biblioteca.digital.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {

	@Override
	public void insertar(LibroDTO libro) throws SQLException {
		String sql = "INSERT INTO libros (titulo, isbn, anio, autor_id, stock) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, libro.getTitulo());
			ps.setString(2, libro.getIsbn());
			ps.setInt(3, libro.getAnio());
			ps.setInt(4, libro.getAutorId());
			ps.setInt(5, libro.getStock());

			ps.executeUpdate();
		}
	}
	@Override
	public List<LibroDTO> listar() throws SQLException {
		List<LibroDTO> libros = new ArrayList<>();
		String sql = """
			    SELECT l.id, l.titulo, l.isbn, l.anio, l.autor_id, l.stock,
			           a.nombre AS autor_nombre
			    FROM libros l
			    JOIN autores a ON l.autor_id = a.id
			""";

		try (Connection conn = Conexion.getConexion();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
			    LibroDTO libro = new LibroDTO();
			    libro.setId(rs.getInt("id"));
			    libro.setTitulo(rs.getString("titulo"));
			    libro.setIsbn(rs.getString("isbn"));
			    libro.setAnio(rs.getInt("anio"));
			    libro.setAutorId(rs.getInt("autor_id"));
			    libro.setStock(rs.getInt("stock"));
			    libro.setAutorNombre(rs.getString("autor_nombre"));
			    libros.add(libro);
			}
		}
		return libros;
	}

	@Override
	public LibroDTO obtenerPorId(int id) throws SQLException {
		String sql = "SELECT * FROM libros WHERE id = ?";
		try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					LibroDTO libro = new LibroDTO();
					libro.setId(rs.getInt("id"));
					libro.setTitulo(rs.getString("titulo"));
					libro.setIsbn(rs.getString("isbn"));
					libro.setAnio(rs.getInt("anio"));
					libro.setAutorId(rs.getInt("autor_id"));
					libro.setStock(rs.getInt("stock"));
					return libro;
				}
			}
		}
		return null;
	}

	@Override
	public void actualizar(LibroDTO libro) throws SQLException {
		String sql = "UPDATE libros SET titulo=?, isbn=?, anio=?, autor_id=?, stock=? WHERE id=?";
		try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, libro.getTitulo());
			ps.setString(2, libro.getIsbn());
			ps.setInt(3, libro.getAnio());
			ps.setInt(4, libro.getAutorId());
			ps.setInt(5, libro.getStock());
			ps.setInt(6, libro.getId());

			ps.executeUpdate();
		}
	}

	@Override
	public void eliminar(int id) throws SQLException {
		String sql = "DELETE FROM libros WHERE id=?";
		try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}
}