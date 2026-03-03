package cl.biblioteca.digital.daos;

import java.sql.SQLException;
import java.util.List;
import cl.biblioteca.digital.model.Usuario;

public interface UsuarioDAO {

	// Buscar usuario por nick y password (para login)
	Usuario obtenerPorLogin(String nick, String password) throws SQLException;

	// Buscar usuario solo por nick
	Usuario obtenerPorNick(String nick) throws SQLException;

	// Buscar usuario por email
	Usuario obtenerPorEmail(String email) throws SQLException;

	// Verificar si existe un email
	boolean existeEmail(String email) throws SQLException;

	// Listar todos los usuarios
	List<Usuario> listarTodos() throws SQLException;

	// Insertar usuario
	int insertar(Usuario usuario) throws SQLException;

	// Actualizar usuario
	boolean actualizar(Usuario usuario) throws SQLException;

	// Eliminar usuario
	boolean eliminar(int id) throws SQLException;
}