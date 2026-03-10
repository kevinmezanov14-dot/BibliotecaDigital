package cl.biblioteca.digital.servlets.usuario;

import cl.biblioteca.digital.model.Usuario;
import cl.biblioteca.digital.servicios.UsuarioServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

@WebServlet("/registrar")
public class RegistrarUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final UsuarioServicio usuarioServicio = new UsuarioServicio();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/login/registro.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String nick = request.getParameter("nick");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String fechaNacimientoStr = request.getParameter("fechaNacimiento");

			// Validaciones básicas
			if (nick == null || email == null || password == null || fechaNacimientoStr == null || nick.isBlank()
					|| email.isBlank() || password.isBlank()) {
				request.setAttribute("error", "Todos los campos son obligatorios");
				request.getRequestDispatcher("/login/registro.jsp").forward(request, response);
				return;
			}

			// Verificar duplicado
			if (usuarioServicio.existeEmail(email)) {
				request.setAttribute("error", "El email ya está registrado");
				request.getRequestDispatcher("/login/registro.jsp").forward(request, response);
				return;
			}

			// Convertir fecha y validar edad mínima
			LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
			int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
			if (edad < 13) {
				request.setAttribute("error", "Debes tener al menos 13 años");
				request.getRequestDispatcher("/login/registro.jsp").forward(request, response);
				return;
			}

			// Crear modelo
			Usuario usuario = new Usuario();
			usuario.setNick(nick);
			usuario.setEmail(email);
			usuario.setPassword(password);
			usuario.setFechaNacimiento(Date.valueOf(fechaNacimiento));
			usuario.setActivo(true);

			// Guardar en BD — pasa por UsuarioServicio que hashea la contraseña
			usuarioServicio.registrar(usuario);

			response.sendRedirect(request.getContextPath() + "/login?registro=ok");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error interno al registrar usuario");
			request.getRequestDispatcher("/login/registro.jsp").forward(request, response);
		}
	}
}