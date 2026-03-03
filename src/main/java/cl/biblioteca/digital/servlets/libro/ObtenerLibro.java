package cl.biblioteca.digital.servlets.libro;

import cl.biblioteca.digital.dtos.LibroDTO;
import cl.biblioteca.digital.servicios.LibroServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/obtener/libro")
public class ObtenerLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LibroServicio servicio = new LibroServicio();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			LibroDTO libro = servicio.obtenerPorId(id);

			if (libro != null) {
				request.setAttribute("libro", libro);
				request.getRequestDispatcher("/libros/editar.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/listar/libros");
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}