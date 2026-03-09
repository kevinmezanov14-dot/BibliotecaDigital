package cl.biblioteca.digital.servicios;

import cl.biblioteca.digital.daos.UsuarioDAO;
import cl.biblioteca.digital.daos.UsuarioDAOImpl;
import cl.biblioteca.digital.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;

public class UsuarioServicio {

    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    public Usuario loginPorEmail(String email, String password) throws SQLException {
        Usuario usuario = usuarioDAO.obtenerPorEmail(email);
        if (usuario != null && BCrypt.checkpw(password, usuario.getPassword()) && usuario.isActivo()) {
            return usuario;
        }
        return null;
    }
    public int registrar(Usuario usuario) throws SQLException {
        // Hashear contraseña antes de guardar
        String hash = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()); //genera unca cadena aleatoria llamada salt y la combina con la contraseña creada
        usuario.setPassword(hash); //reemplaza la contraseña con el hash generado
        return usuarioDAO.insertar(usuario);
    }

    public boolean existeEmail(String email) throws SQLException {
        return usuarioDAO.existeEmail(email);
    }

    public Usuario obtenerPorNick(String nick) throws SQLException {
        return usuarioDAO.obtenerPorNick(nick);
    }
}