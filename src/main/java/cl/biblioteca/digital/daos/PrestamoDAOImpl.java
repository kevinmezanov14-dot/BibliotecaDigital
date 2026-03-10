package cl.biblioteca.digital.daos;

import cl.biblioteca.digital.dtos.PrestamoDTO;
import cl.biblioteca.digital.util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAOImpl implements PrestamoDAO {

    @Override
    public boolean registrar(PrestamoDTO p) throws SQLException {
        String verificarStock  = "SELECT stock FROM libros WHERE id = ?";
        String insertarPrestamo = """
            INSERT INTO prestamos (cliente_id, libro_id, fecha_vencimiento, estado)
            VALUES (?, ?, ?, ?)
        """;
        String descontarStock = "UPDATE libros SET stock = stock - 1 WHERE id = ?";

        try (Connection conn = Conexion.getConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement psStock  = conn.prepareStatement(verificarStock);
                 PreparedStatement psInsert = conn.prepareStatement(insertarPrestamo);
                 PreparedStatement psUpdate = conn.prepareStatement(descontarStock)) {

                // Verificar stock
                psStock.setInt(1, p.getLibroId());
                try (ResultSet rs = psStock.executeQuery()) {
                    if (rs.next()) {
                        if (rs.getInt("stock") <= 0) {
                            conn.rollback();
                            return false;
                        }
                    } else {
                        conn.rollback();
                        return false;
                    }
                }

                // Insertar préstamo
                psInsert.setInt(1, p.getClienteId());
                psInsert.setInt(2, p.getLibroId());
                psInsert.setTimestamp(3, Timestamp.valueOf(p.getFechaVencimiento()));
                psInsert.setString(4, "ACTIVO");
                psInsert.executeUpdate();

                // Descontar stock
                psUpdate.setInt(1, p.getLibroId());
                psUpdate.executeUpdate();

                conn.commit();
                return true;

            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    @Override
    public List<PrestamoDTO> listar() throws SQLException {
        List<PrestamoDTO> lista = new ArrayList<>();
        String sql = """
            SELECT p.*,
                   c.nick AS cliente_nick,
                   l.titulo AS libro_titulo
            FROM prestamos p
            JOIN clientes c ON p.cliente_id = c.id
            JOIN libros l ON p.libro_id = l.id
            ORDER BY p.fecha_solicitud DESC
        """;
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
    public boolean devolver(int prestamoId, int libroId) throws SQLException {

        String actualizarPrestamo = """
            UPDATE prestamos
            SET fecha_devolucion = NOW(),
                estado = 'DEVUELTO'
            WHERE id = ?
            AND estado IN ('ACTIVO','VENCIDO')
        """;

        String aumentarStock = "UPDATE libros SET stock = stock + 1 WHERE id = ?";

        try (Connection conn = Conexion.getConexion()) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement(actualizarPrestamo);
                 PreparedStatement ps2 = conn.prepareStatement(aumentarStock)) {

                ps1.setInt(1, prestamoId);
                int filas = ps1.executeUpdate();

                if (filas == 0) {
                    conn.rollback();
                    return false;
                }

                ps2.setInt(1, libroId);
                ps2.executeUpdate();

                conn.commit();
                return true;

            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    @Override
    public boolean actualizarEstado(int id, String estado) throws SQLException {
        String sql = "UPDATE prestamos SET estado = ? WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, estado);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public void actualizarPrestamosVencidos() throws SQLException {
        String sql = """
            UPDATE prestamos SET estado = 'VENCIDO'
            WHERE estado = 'ACTIVO' AND fecha_vencimiento < NOW()
        """;
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM prestamos WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private PrestamoDTO mapear(ResultSet rs) throws SQLException {
        PrestamoDTO p = new PrestamoDTO();
        p.setId(rs.getInt("id"));
        p.setClienteId(rs.getInt("cliente_id"));
        p.setLibroId(rs.getInt("libro_id"));
        p.setFechaSolicitud(rs.getTimestamp("fecha_solicitud").toLocalDateTime());
        p.setFechaVencimiento(rs.getTimestamp("fecha_vencimiento").toLocalDateTime());
        if (rs.getTimestamp("fecha_devolucion") != null) {
            p.setFechaDevolucion(rs.getTimestamp("fecha_devolucion").toLocalDateTime());
        }
        p.setEstado(rs.getString("estado"));
        p.setClienteNick(rs.getString("cliente_nick"));
        p.setLibroTitulo(rs.getString("libro_titulo"));
        return p;
    }
}