package cl.biblioteca.digital.servicios;

import cl.biblioteca.digital.daos.LibroDAO;
import cl.biblioteca.digital.daos.LibroDAOImpl;
import cl.biblioteca.digital.dtos.LibroDTO;

import java.sql.SQLException;
import java.util.List;

public class LibroServicio {

    private final LibroDAO libroDAO = new LibroDAOImpl();

    public void registrar(LibroDTO libro) throws SQLException {
        libroDAO.insertar(libro);
    }

    public List<LibroDTO> listar() throws SQLException {
        return libroDAO.listar();
    }

    public LibroDTO obtenerPorId(int id) throws SQLException {
        return libroDAO.obtenerPorId(id);
    }

    public void actualizar(LibroDTO libro) throws SQLException {
        libroDAO.actualizar(libro);
    }

    public void eliminar(int id) throws SQLException {
        libroDAO.eliminar(id);
    }
}