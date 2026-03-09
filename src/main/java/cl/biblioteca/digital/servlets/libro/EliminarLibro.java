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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/libros");
	}
}