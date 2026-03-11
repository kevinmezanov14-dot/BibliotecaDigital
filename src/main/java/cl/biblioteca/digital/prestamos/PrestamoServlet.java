package cl.biblioteca.digital.prestamos;

import cl.biblioteca.digital.daos.PrestamoDAO;
import cl.biblioteca.digital.daos.PrestamoDAOImpl;
import cl.biblioteca.digital.daos.ClienteDAO;
import cl.biblioteca.digital.daos.ClienteDAOImpl;
import cl.biblioteca.digital.daos.LibroDAO;
import cl.biblioteca.digital.daos.LibroDAOImpl;
import cl.biblioteca.digital.dtos.PrestamoDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

// Gestion de prestamos, Listar,Registrar
@WebServlet("/prestamos")
public class PrestamoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final PrestamoDAO prestamoDAO = new PrestamoDAOImpl();
	private final ClienteDAO clienteDAO = new ClienteDAOImpl();
	private final LibroDAO libroDAO = new LibroDAOImpl();

	// Listar Prestamos cuando el usuario ingresa
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			prestamoDAO.actualizarPrestamosVencidos();

			request.setAttribute("prestamos", prestamoDAO.listar());
			request.setAttribute("clientes", clienteDAO.listar());
			request.setAttribute("libros", libroDAO.listar());

			request.getRequestDispatcher("/WEB-INF/jsp/prestamos.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error al cargar préstamos. Intenta nuevamente.");
			request.getRequestDispatcher("/WEB-INF/jsp/prestamos.jsp").forward(request, response);
		}
	}

	// Registro y devolución de prestamos
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");

		try {
			if ("registrar".equals(accion)) {

				String clienteIdStr = request.getParameter("clienteId");
				String libroIdStr = request.getParameter("libroId");
				String fecha = request.getParameter("fechaVencimiento");

				// Validar campos obligatorios
				if (clienteIdStr == null || clienteIdStr.isBlank() || libroIdStr == null || libroIdStr.isBlank()
						|| fecha == null || fecha.isBlank()) {

					request.setAttribute("error", "Todos los campos son obligatorios.");
					doGet(request, response);
					return;
				}
				// Crear Objeto Prestamo
				PrestamoDTO p = new PrestamoDTO();
				p.setClienteId(Integer.parseInt(clienteIdStr.trim()));
				p.setLibroId(Integer.parseInt(libroIdStr.trim()));
				p.setFechaVencimiento(LocalDateTime.parse(fecha));

				boolean registrado = prestamoDAO.registrar(p);

				if (!registrado) {
					request.setAttribute("error", "No hay stock disponible para este libro.");
					doGet(request, response);
					return;
				}
				// Devolver Libro

			} else if ("devolver".equals(accion)) {

				String idStr = request.getParameter("id");
				String libroIdStr = request.getParameter("libroId");

				if (idStr == null || idStr.isBlank() || libroIdStr == null || libroIdStr.isBlank()) {

					request.setAttribute("error", "Datos de devolución incompletos.");
					doGet(request, response);
					return;
				}
				// Registrar devolución de prestamo
				prestamoDAO.devolver(Integer.parseInt(idStr.trim()), Integer.parseInt(libroIdStr.trim()));
			}

			response.sendRedirect(request.getContextPath() + "/prestamos");

		} catch (NumberFormatException e) {
			request.setAttribute("error", "Los datos ingresados no son válidos.");
			doGet(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error al procesar la operación. Intenta nuevamente.");
			doGet(request, response);
		}
	}
}