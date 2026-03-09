package cl.biblioteca.digital.clientes;

import cl.biblioteca.digital.daos.ClienteDAO;
import cl.biblioteca.digital.daos.ClienteDAOImpl;
import cl.biblioteca.digital.dtos.ClienteDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final ClienteDAO dao = new ClienteDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<ClienteDTO> lista = dao.listar();
			request.setAttribute("clientes", lista);
			request.getRequestDispatcher("/WEB-INF/jsp/clientes.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error al cargar clientes. Intenta nuevamente.");
			request.getRequestDispatcher("/WEB-INF/jsp/clientes.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String nick = request.getParameter("nick");
			String email = request.getParameter("email");
			String fechaStr = request.getParameter("fechaNacimiento");

			if (nick == null || nick.isBlank() || email == null || email.isBlank()) {
				request.setAttribute("error", "Nick y email son obligatorios.");
				doGet(request, response);
				return;
			}

			ClienteDTO cliente = new ClienteDTO();
			cliente.setNick(nick.trim());
			cliente.setEmail(email.trim());

			if (fechaStr != null && !fechaStr.isBlank()) {
				cliente.setFechaNacimiento(java.sql.Date.valueOf(fechaStr));
			}

			dao.registrar(cliente);
			response.sendRedirect(request.getContextPath() + "/clientes");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error al registrar cliente. Intenta nuevamente.");
			doGet(request, response);
		}
	}
}