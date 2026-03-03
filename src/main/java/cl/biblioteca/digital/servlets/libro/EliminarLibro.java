package cl.biblioteca.digital.servlets.libro;

import cl.biblioteca.digital.servicios.LibroServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/eliminar/libros")
public class EliminarLibro extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private LibroServicio servicio = new LibroServicio();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            System.out.println("========================================");
            System.out.println("🗑️ ELIMINANDO LIBRO");
            
            // Obtener el ID
            String idStr = request.getParameter("id");
            
            if (idStr == null || idStr.isEmpty()) {
                System.out.println("❌ ID no proporcionado");
                response.sendRedirect(request.getContextPath() + "/libros?error=id_requerido");
                return;
            }
            
            int id = Integer.parseInt(idStr);
            System.out.println("ID del libro a eliminar: " + id);
            
            // Eliminar
            servicio.eliminar(id);
            
            System.out.println("✅ Libro eliminado exitosamente");
            System.out.println("========================================");
            
            // Redirigir con mensaje de éxito
            response.sendRedirect(request.getContextPath() + "/libros?eliminado=success");
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: ID inválido - " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/libros?error=id_invalido");
            
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar libro: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/libros?error=eliminar");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // ⚠️ SEGURIDAD: Eliminar debería ser solo POST, no GET
        // Redirigir al listado si intentan acceder por GET
        response.sendRedirect(request.getContextPath() + "/libros");
    }
}