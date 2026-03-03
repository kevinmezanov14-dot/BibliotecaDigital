package cl.biblioteca.digital.daos;

import cl.biblioteca.digital.dtos.AutorDTO;
import cl.biblioteca.digital.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOImpl implements AutorDAO {

	@Override
	public List<AutorDTO> listar() throws SQLException {
		List<AutorDTO> autores = new ArrayList<>();
		String sql = "SELECT id, nombre, nacionalidad FROM autores";

		try (Connection conn = Conexion.getConexion();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

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
}