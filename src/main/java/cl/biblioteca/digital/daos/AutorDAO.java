package cl.biblioteca.digital.daos;

import cl.biblioteca.digital.dtos.AutorDTO;
import java.sql.SQLException;
import java.util.List;

public interface AutorDAO {
	List<AutorDTO> listar() throws SQLException;
}