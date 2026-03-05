package cl.biblioteca.digital.daos;

import cl.biblioteca.digital.dtos.PrestamoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    private Connection conexion;

    public PrestamoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // ==============================
    // REGISTRAR PRÉSTAMO
    // ==============================
    public boolean registrar(PrestamoDTO p) throws SQLException {

        String verificarStock = "SELECT stock FROM libros WHERE id = ?";
        String insertarPrestamo = """
            INSERT INTO prestamos
            (cliente_id, libro_id, fecha_vencimiento, estado)
            VALUES (?, ?, ?, ?)
        """;
        String descontarStock = """
            UPDATE libros
            SET stock = stock - 1
            WHERE id = ?
        """;

        conexion.setAutoCommit(false);

        try (
            PreparedStatement psStock = conexion.prepareStatement(verificarStock);
            PreparedStatement psInsert = conexion.prepareStatement(insertarPrestamo);
            PreparedStatement psUpdate = conexion.prepareStatement(descontarStock)
        ) {

            // 🔎 Verificar stock
            psStock.setInt(1, p.getLibroId());
            ResultSet rs = psStock.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("stock");
                if (stock <= 0) {
                    conexion.rollback();
                    return false; // No hay stock
                }
            } else {
                conexion.rollback();
                return false; // Libro no existe
            }

            // 📌 Insertar préstamo
            psInsert.setInt(1, p.getClienteId());
            psInsert.setInt(2, p.getLibroId());
            psInsert.setTimestamp(3, Timestamp.valueOf(p.getFechaVencimiento()));
            psInsert.setString(4, "ACTIVO");

            psInsert.executeUpdate();

            // 📉 Descontar stock
            psUpdate.setInt(1, p.getLibroId());
            psUpdate.executeUpdate();

            conexion.commit();
            return true;

        } catch (Exception e) {
            conexion.rollback();
            throw e;
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    // ==============================
    // LISTAR PRÉSTAMOS
    // ==============================
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

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                PrestamoDTO p = new PrestamoDTO();

                p.setId(rs.getInt("id"));
                p.setClienteId(rs.getInt("cliente_id"));
                p.setLibroId(rs.getInt("libro_id"));

                p.setFechaSolicitud(
                    rs.getTimestamp("fecha_solicitud").toLocalDateTime()
                );

                p.setFechaVencimiento(
                    rs.getTimestamp("fecha_vencimiento").toLocalDateTime()
                );

                if (rs.getTimestamp("fecha_devolucion") != null) {
                    p.setFechaDevolucion(
                        rs.getTimestamp("fecha_devolucion").toLocalDateTime()
                    );
                }

                p.setEstado(rs.getString("estado"));

                // 🔥 IMPORTANTE
                p.setClienteNick(rs.getString("cliente_nick"));
                p.setLibroTitulo(rs.getString("libro_titulo"));

                lista.add(p);
            }
        }

        return lista;
    }

    // ==============================
    // DEVOLVER LIBRO
    // ==============================
    public boolean devolver(int prestamoId, int libroId) throws SQLException {

        String actualizarPrestamo = """
            UPDATE prestamos
            SET fecha_devolucion = NOW(),
                estado = 'DEVUELTO'
            WHERE id = ?
        """;

        String aumentarStock = """
            UPDATE libros
            SET stock = stock + 1
            WHERE id = ?
        """;

        conexion.setAutoCommit(false);

        try (
            PreparedStatement ps1 = conexion.prepareStatement(actualizarPrestamo);
            PreparedStatement ps2 = conexion.prepareStatement(aumentarStock)
        ) {

            ps1.setInt(1, prestamoId);
            ps1.executeUpdate();

            ps2.setInt(1, libroId);
            ps2.executeUpdate();

            conexion.commit();
            return true;

        } catch (Exception e) {
            conexion.rollback();
            throw e;
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    // ==============================
    // ACTUALIZAR ESTADO
    // ==============================
    public boolean actualizarEstado(int id, String estado) throws SQLException {

        String sql = "UPDATE prestamos SET estado=? WHERE id=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;
        }
    }
    public void actualizarPrestamosVencidos() throws SQLException {

        String sql = """
            UPDATE prestamos
            SET estado = 'VENCIDO'
            WHERE estado = 'ACTIVO'
            AND fecha_vencimiento < NOW()
        """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }

    // ==============================
    // ELIMINAR
    // ==============================
    public boolean eliminar(int id) throws SQLException {

        String sql = "DELETE FROM prestamos WHERE id=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}