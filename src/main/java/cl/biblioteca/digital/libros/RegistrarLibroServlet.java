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
import java.time.LocalDate;
// manejod e solicitud de Registrar nuevos libros en sistema
@WebServlet("/libros/registrar")
public class RegistrarLibroServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final AutorServicio autorServicio = new AutorServicio();
	private final LibroServicio libroServicio = new LibroServicio();
	// Carga de Formulario cuando ingresa el usuario
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("autores", autorServicio.listar());
			request.getRequestDispatcher("/libros/formulario.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error al cargar autores. Intenta nuevamente.");
			request.getRequestDispatcher("/libros/formulario.jsp").forward(request, response);
		}
	}
	// Procesar formulario cuando el usuario presiona guardar
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String titulo = request.getParameter("titulo");
			String isbn = request.getParameter("isbn");
			String anioStr = request.getParameter("anio");
			String autorStr = request.getParameter("autor");
			String stockStr = request.getParameter("stock");

			// Validar campos obligatorios
			if (titulo == null || titulo.isBlank() || isbn == null || isbn.isBlank() || anioStr == null
					|| anioStr.isBlank() || autorStr == null || autorStr.isBlank()) {
				request.setAttribute("error", "Todos los campos son obligatorios.");
				doGet(request, response);
				return;
			}

			// Validar título
			if (titulo.trim().length() > 150) {
				request.setAttribute("error", "El título no puede superar los 150 caracteres.");
				doGet(request, response);
				return;
			}

			// Validar ISBN
			if (!isbn.trim().matches("[\\d\\-]{10,50}")) {
				request.setAttribute("error",
						"ISBN inválido. Debe tener entre 10 y 50 caracteres (números y guiones).");
				doGet(request, response);
				return;
			}

			// Validar año
			int anio;
			try {
				anio = Integer.parseInt(anioStr.trim());
				if (anio < 1000 || anio > LocalDate.now().getYear()) {
					request.setAttribute("error",
							"Año inválido. Debe estar entre 1000 y " + LocalDate.now().getYear() + ".");
					doGet(request, response);
					return;
				}
			} catch (NumberFormatException e) {
				request.setAttribute("error", "El año debe ser un número válido.");
				doGet(request, response);
				return;
			}

			// Validar stock
			int stock = 0;
			if (stockStr != null && !stockStr.isBlank()) {
				try {
					stock = Integer.parseInt(stockStr.trim());
					if (stock < 0) {
						request.setAttribute("error", "El stock no puede ser negativo.");
						doGet(request, response);
						return;
					}
				} catch (NumberFormatException e) {
					request.setAttribute("error", "El stock debe ser un número válido.");
					doGet(request, response);
					return;
				}
			}
			// Crear Objeto Libro
			LibroDTO libro = new LibroDTO();
			libro.setTitulo(titulo.trim());
			libro.setIsbn(isbn.trim());
			libro.setAnio(anio);
			libro.setAutorId(Integer.parseInt(autorStr.trim()));
			libro.setStock(stock);
			//Guardar libro
			libroServicio.registrar(libro);
			response.sendRedirect(request.getContextPath() + "/libros");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error al registrar el libro. Intenta nuevamente.");
			doGet(request, response);
		}
	}
}