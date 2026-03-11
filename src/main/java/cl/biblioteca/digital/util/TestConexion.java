package cl.biblioteca.digital.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) {
        try (Connection conn = Conexion.getInstancia().getConexion()) {
            System.out.println("Conexión exitosa!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}