package cl.biblioteca.digital.servicios;

import cl.biblioteca.digital.daos.AutorDAO;
import cl.biblioteca.digital.daos.AutorDAOImpl;
import cl.biblioteca.digital.dtos.AutorDTO;
import java.sql.SQLException;
import java.util.List;

public class AutorServicio {
    private final AutorDAO autorDAO = new AutorDAOImpl();

    public List<AutorDTO> listar() throws SQLException {
        return autorDAO.listar();
    }

    public void registrar(AutorDTO autor) throws SQLException {
        autorDAO.insertar(autor);
    }
}