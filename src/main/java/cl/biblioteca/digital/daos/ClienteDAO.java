package cl.biblioteca.digital.daos;

import cl.biblioteca.digital.dtos.ClienteDTO;
import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {
    List<ClienteDTO> listar() throws SQLException;
    void registrar(ClienteDTO cliente) throws SQLException;
    ClienteDTO obtenerPorId(int id) throws SQLException;
    void actualizar(ClienteDTO cliente) throws SQLException;
    void eliminar(int id) throws SQLException;
}