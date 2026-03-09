package cl.biblioteca.digital.daos;

import cl.biblioteca.digital.dtos.ClienteDTO;
import cl.biblioteca.digital.util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public List<ClienteDTO> listar() throws SQLException {
        List<ClienteDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY id DESC";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    @Override
    public void registrar(ClienteDTO cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nick, email, fecha_nacimiento, activo) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNick());
            ps.setString(2, cliente.getEmail());
            ps.setDate(3, cliente.getFechaNacimiento());
            ps.setBoolean(4, true);
            ps.executeUpdate();
        }
    }

    @Override
    public ClienteDTO obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void actualizar(ClienteDTO cliente) throws SQLException {
        String sql = "UPDATE clientes SET nick = ?, email = ? WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNick());
            ps.setString(2, cliente.getEmail());
            ps.setInt(3, cliente.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private ClienteDTO mapear(ResultSet rs) throws SQLException {
        ClienteDTO c = new ClienteDTO();
        c.setId(rs.getInt("id"));
        c.setNick(rs.getString("nick"));
        c.setEmail(rs.getString("email"));
        c.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        c.setActivo(rs.getBoolean("activo"));
        return c;
    }
}