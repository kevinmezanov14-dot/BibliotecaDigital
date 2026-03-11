package cl.biblioteca.digital.daos;

import cl.biblioteca.digital.dtos.AutorDTO;
import cl.biblioteca.digital.util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOImpl implements AutorDAO {
	// Método que obtiene todos los autores desde la base de datos
	@Override
	public List<AutorDTO> listar() throws SQLException {
		List<AutorDTO> autores = new ArrayList<>();
		String sql = "SELECT id, nombre, nacionalidad FROM autores";
		try (Connection conn = Conexion.getInstancia().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				AutorDTO autor = new AutorDTO();
				autor.setId(rs.getInt("id"));
				autor.setNombre(rs.getString("nombre"));
				autor.setNacionalidad(rs.getString("nacionalidad"));
				autores.add(autor);
			}
		}
		return autores;
	}
	// Método que inserta un nuevo autor en la base de datos
	@Override
	public void insertar(AutorDTO autor) throws SQLException {
		String sql = "INSERT INTO autores (nombre, nacionalidad) VALUES (?, ?)";
		try (Connection conn = Conexion.getInstancia().getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, autor.getNombre());
			ps.setString(2, autor.getNacionalidad());
			ps.executeUpdate();
		}
	}
}