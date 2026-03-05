package cl.biblioteca.digital.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import cl.biblioteca.digital.model.Usuario;
import cl.biblioteca.digital.servicios.UsuarioServicio;

@WebServlet("/login")
public class LoginServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioServicio usuarioServicio = new UsuarioServicio();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/login/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			Usuario usuario = usuarioServicio.loginPorEmail(email, password);

			if (usuario != null) {
				HttpSession session = request.getSession();
				session.setAttribute("usuario", usuario);
				session.setAttribute("nick", usuario.getNick());
				session.setAttribute("usuarioId", usuario.getId());

				// ✅ LÍNEAS NUEVAS — calcular y guardar edad
				LocalDate fechaNac = usuario.getFechaNacimiento().toLocalDate();
				int edad = Period.between(fechaNac, LocalDate.now()).getYears();
				session.setAttribute("edad", edad);

				response.sendRedirect(request.getContextPath() + "/dashboard");

			} else {
				request.setAttribute("error", "Usuario o contraseña incorrectos");
				request.getRequestDispatcher("/login/login.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error en login: " + e.getMessage());
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		}
	}
}