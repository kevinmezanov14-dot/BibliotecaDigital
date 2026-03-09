package cl.biblioteca.digital.servicios;

import cl.biblioteca.digital.daos.UsuarioDAO;
import cl.biblioteca.digital.daos.UsuarioDAOImpl;
import cl.biblioteca.digital.model.Usuario;
import java.sql.SQLException;

public class UsuarioServicio {

    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    // Login por email
    public Usuario loginPorEmail(String email, String password) throws SQLException {
        Usuario usuario = usuarioDAO.obtenerPorEmail(email);
        if (usuario != null && usuario.getPassword().equals(password) && usuario.isActivo()) {
            return usuario;
        }
        return null;
    }

    public int registrar(Usuario usuario) throws SQLException {
        return usuarioDAO.insertar(usuario);
    }

    public boolean existeEmail(String email) throws SQLException {
        return usuarioDAO.existeEmail(email);
    }

    public Usuario obtenerPorNick(String nick) throws SQLException {
        return usuarioDAO.obtenerPorNick(nick);
    }
}