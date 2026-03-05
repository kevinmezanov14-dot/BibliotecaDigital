package cl.biblioteca.digital.daos;

import cl.biblioteca.digital.dtos.ClienteDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection conexion;

    public ClienteDAO(Connection conexion) {
        this.conexion = conexion;
    }

	// LISTAR
    public List<ClienteDTO> listar() throws SQLException {

        List<ClienteDTO> lista = new ArrayList<>();

        String sql = "SELECT * FROM clientes ORDER BY id DESC";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ClienteDTO c = new ClienteDTO();
                c.setId(rs.getInt("id"));
                c.setNick(rs.getString("nick"));
                c.setEmail(rs.getString("email"));

                lista.add(c);
            }
        }

        return lista;
    }

    // REGISTRAR
    public void registrar(ClienteDTO cliente) throws SQLException {

        String sql = "INSERT INTO clientes (nick, email) VALUES (?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getNick());
            ps.setString(2, cliente.getEmail());

            ps.executeUpdate();
        }
    }

    // OBTENER POR ID
    public ClienteDTO obtenerPorId(int id) throws SQLException {

        String sql = "SELECT * FROM clientes WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    ClienteDTO c = new ClienteDTO();
                    c.setId(rs.getInt("id"));
                    c.setNick(rs.getString("nick"));
                    c.setEmail(rs.getString("email"));
                    return c;
                }
            }
        }

        return null;
    }

    // ACTUALIZAR
    public void actualizar(ClienteDTO cliente) throws SQLException {

        String sql = "UPDATE clientes SET nick = ?, email = ? WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getNick());
            ps.setString(2, cliente.getEmail());
            ps.setInt(3, cliente.getId());

            ps.executeUpdate();
        }
    }

    // ELIMINAR
    public void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM clientes WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}