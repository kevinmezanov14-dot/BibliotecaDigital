package cl.biblioteca.digital.servlets.libro;

import cl.biblioteca.digital.dtos.LibroDTO;
import cl.biblioteca.digital.servicios.LibroServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/registrar/libro")
public class RegistrarLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LibroServicio servicio = new LibroServicio();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			LibroDTO dto = new LibroDTO();
			dto.setTitulo(request.getParameter("titulo"));
			dto.setIsbn(request.getParameter("isbn"));
			dto.setAnio(Integer.parseInt(request.getParameter("anio")));
			dto.setAutorId(Integer.parseInt(request.getParameter("autor_id")));
			dto.setStock(Integer.parseInt(request.getParameter("stock")));

			servicio.registrar(dto);
			response.sendRedirect(request.getContextPath() + "/listar/libros");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}