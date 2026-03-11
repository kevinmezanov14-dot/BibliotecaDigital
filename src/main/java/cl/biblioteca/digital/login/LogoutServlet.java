package cl.biblioteca.digital.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

// cerrar sesion del usuario cuando se accese a la URL logout
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtiene sesion actual sin crear una nueva
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); //Elimina atributos  guardados
		}

		response.sendRedirect(request.getContextPath() + "/login");
	}
}