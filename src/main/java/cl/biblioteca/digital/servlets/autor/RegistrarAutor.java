package cl.biblioteca.digital.servlets.autor;

import cl.biblioteca.digital.dtos.AutorDTO;
import cl.biblioteca.digital.servicios.AutorServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Registrar nuevos autores
@WebServlet("/autores/registrar")
public class RegistrarAutor extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final AutorServicio autorServicio = new AutorServicio();

	// Mostrar Formulario
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/autores/registrar-autor.jsp").forward(request, response);
	}

	// Procesar el registro
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String nombre = request.getParameter("nombre");
			String nacionalidad = request.getParameter("nacionalidad");

			if (nombre == null || nombre.isBlank()) {
				request.setAttribute("error", "El nombre del autor es obligatorio.");
				request.getRequestDispatcher("/autores/registrar-autor.jsp").forward(request, response);
				return;
			}
			// Crear objeto autor
			AutorDTO autor = new AutorDTO();
			autor.setNombre(nombre.trim());
			autor.setNacionalidad(nacionalidad == null ? "" : nacionalidad.trim());
			// Guardar autor
			autorServicio.registrar(autor);
			response.sendRedirect(request.getContextPath() + "/autores/registrar?exito=true");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error al registrar el autor. Intenta nuevamente.");
			request.getRequestDispatcher("/autores/registrar-autor.jsp").forward(request, response);
		}
	}
}
