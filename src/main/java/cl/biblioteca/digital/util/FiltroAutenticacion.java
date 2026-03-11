package cl.biblioteca.digital.util;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class FiltroAutenticacion implements Filter {

	// Rutas públicas que NO requieren login
	private static final String[] RUTAS_PUBLICAS = { "/login", "/registrar", "/css/", "/js/", "/img/", "/webjars/",
			"/favicon.ico" };

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String ruta = request.getServletPath();

		// ¿Es ruta pública? → dejar pasar sin verificar sesión
		for (String publica : RUTAS_PUBLICAS) {
			if (ruta.startsWith(publica)) {
				chain.doFilter(req, res);
				return;
			}
		}

		// ¿Tiene sesión activa con usuario?
		HttpSession session = request.getSession(false);
		boolean logueado = session != null && session.getAttribute("usuario") != null;

		if (logueado) {
			chain.doFilter(req, res); // Sesión válida → continuar
		} else {
			// ❌ Sin sesión → redirigir al login
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	@Override
	public void destroy() {
		// Sin recursos que liberar
	}
}