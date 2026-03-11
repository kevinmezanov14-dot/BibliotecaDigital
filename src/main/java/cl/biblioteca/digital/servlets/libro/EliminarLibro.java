package cl.biblioteca.digital.servlets.libro;

import cl.biblioteca.digital.servicios.LibroServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
// Eliminar libros del sistema
@WebServlet("/eliminar/libros")
public class EliminarLibro extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LibroServicio servicio = new LibroServicio();
	// Elimina libros cuando se ejecuta la petición previamente validando la información
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String idStr = request.getParameter("id");

			if (idStr == null || idStr.isEmpty()) {
				response.sendRedirect(request.getContextPath() + "/libros?error=id_requerido");
				return;
			}

			int id = Integer.parseInt(idStr);
			servicio.eliminar(id);
			response.sendRedirect(request.getContextPath() + "/libros?eliminado=success");

		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/libros?error=id_invalido");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/libros?error=eliminar");
		}
	}
	// Bloquear acceso si alguien intenta entrar directamente, lo redirige a libros
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/libros");
	}
}