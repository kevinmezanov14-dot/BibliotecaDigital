package cl.biblioteca.digital.daos;

import cl.biblioteca.digital.dtos.LibroDTO;
import cl.biblioteca.digital.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Implementación del DAO para la entidad Libro.
// Contiene las operaciones CRUD para la tabla "libros".
public class LibroDAOImpl implements LibroDAO {

	// Inserta un nuevo libro en la base de datos
	@Override
	public void insertar(LibroDTO libro) throws SQLException {
		String sql = "INSERT INTO libros (titulo, isbn, anio, autor_id, stock) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = Conexion.getInstancia().getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, libro.getTitulo());
			ps.setString(2, libro.getIsbn());
			ps.setInt(3, libro.getAnio());
			ps.setInt(4, libro.getAutorId());
			ps.setInt(5, libro.getStock());

			ps.executeUpdate();
		}
	}

	// Obtiene todos los libros con el nombre del autor
	@Override
	public List<LibroDTO> listar() throws SQLException {

		List<LibroDTO> libros = new ArrayList<>();

		String sql = """
				SELECT l.id, l.titulo, l.isbn, l.anio, l.autor_id, l.stock,
				       a.nombre AS autor_nombre
				FROM libros l
				JOIN autores a ON l.autor_id = a.id
				""";

		try (Connection conn = Conexion.getInstancia().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				LibroDTO libro = mapear(rs);

				// se agrega el nombre del autor obtenido del JOIN
				libro.setAutorNombre(rs.getString("autor_nombre"));

				libros.add(libro);
			}
		}

		return libros;
	}

	// Obtiene un libro específico por su id
	@Override
	public LibroDTO obtenerPorId(int id) throws SQLException {

		String sql = "SELECT * FROM libros WHERE id = ?";

		try (Connection conn = Conexion.getInstancia().getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					return mapear(rs);
				}
			}
		}

		return null;
	}

	// Actualiza los datos de un libro
	@Override
	public void actualizar(LibroDTO libro) throws SQLException {

		String sql = "UPDATE libros SET titulo=?, isbn=?, anio=?, autor_id=?, stock=? WHERE id=?";

		try (Connection conn = Conexion.getInstancia().getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, libro.getTitulo());
			ps.setString(2, libro.getIsbn());
			ps.setInt(3, libro.getAnio());
			ps.setInt(4, libro.getAutorId());
			ps.setInt(5, libro.getStock());
			ps.setInt(6, libro.getId());

			ps.executeUpdate();
		}
	}

	// Elimina un libro según su id
	@Override
	public void eliminar(int id) throws SQLException {

		String sql = "DELETE FROM libros WHERE id=?";

		try (Connection conn = Conexion.getInstancia().getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	// Método privado que convierte una fila del ResultSet en un objeto LibroDTO
	private LibroDTO mapear(ResultSet rs) throws SQLException {

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