package cl.biblioteca.digital.libros;

import cl.biblioteca.digital.dtos.LibroDTO;
import cl.biblioteca.digital.servicios.LibroServicio;
import cl.biblioteca.digital.servicios.AutorServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/libros/registrar")
public class RegistrarLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AutorServicio autorServicio = new AutorServicio();
	private LibroServicio libroServicio = new LibroServicio();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("autores", autorServicio.listar()); // <--- esto es clave
			request.getRequestDispatcher("/libros/formulario.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error al cargar autores: " + e.getMessage());
			request.getRequestDispatcher("/libros/formulario.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Obtener datos del formulario
			String titulo = request.getParameter("titulo");
			String isbn = request.getParameter("isbn");
			int anio = Integer.parseInt(request.getParameter("anio"));
			int autorId = Integer.parseInt(request.getParameter("autor"));
			int stock = Integer.parseInt(request.getParameter("stock"));

			// Crear DTO
			LibroDTO libro = new LibroDTO();
			libro.setTitulo(titulo);
			libro.setIsbn(isbn);
			libro.setAnio(anio);
			libro.setAutorId(autorId);
			libro.setStock(stock); // stock inicial

			// Guardar en DB
			libroServicio.registrar(libro);

			// Redirigir al listado de libros
			response.sendRedirect(request.getContextPath() + "/libros");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error al registrar libro: " + e.getMessage());
			doGet(request, response);
		}
	}
}