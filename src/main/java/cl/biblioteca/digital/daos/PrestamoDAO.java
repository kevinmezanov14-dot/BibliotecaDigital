package cl.biblioteca.digital.daos;

import cl.biblioteca.digital.dtos.PrestamoDTO;
import java.sql.SQLException;
import java.util.List;

public interface PrestamoDAO {
    boolean registrar(PrestamoDTO prestamo) throws SQLException;
    List<PrestamoDTO> listar() throws SQLException;
    boolean devolver(int prestamoId, int libroId) throws SQLException;
    boolean actualizarEstado(int id, String estado) throws SQLException;
    void actualizarPrestamosVencidos() throws SQLException;
    boolean eliminar(int id) throws SQLException;
}