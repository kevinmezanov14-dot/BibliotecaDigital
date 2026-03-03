package cl.biblioteca.digital.daos;

import cl.biblioteca.digital.dtos.LibroDTO;
import java.sql.SQLException;
import java.util.List;

public interface LibroDAO {
	void insertar(LibroDTO libro) throws SQLException;

	List<LibroDTO> listar() throws SQLException;

	LibroDTO obtenerPorId(int id) throws SQLException;

	void actualizar(LibroDTO libro) throws SQLException;

	void eliminar(int id) throws SQLException;
}