package cl.biblioteca.digital.libros;

import cl.biblioteca.digital.dtos.LibroDTO;
import cl.biblioteca.digital.servicios.LibroServicio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//Manejo de Solicitud listar de Libros 
@WebServlet("/libros")
public class LibroServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final LibroServicio libroServicio = new LibroServicio();
	
	// metodo se ejecuta cuando se hace petición de get
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// Obtener lista de libros desde la base de datos
			List<LibroDTO> libros = libroServicio.listar();

			// Guardar la lista en el request
			request.setAttribute("libros", libros);

			// Enviar a JSP para mostrar
			request.getRequestDispatcher("/libros/listar.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error al obtener los libros. Intenta nuevamente.");
			request.getRequestDispatcher("/libros/listar.jsp").forward(request, response);
		}
	}
}