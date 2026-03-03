package cl.biblioteca.digital.servlets.libro;

import cl.biblioteca.digital.dtos.LibroDTO;
import cl.biblioteca.digital.servicios.LibroServicio;
import cl.biblioteca.digital.servicios.AutorServicio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/actualizar/libros")
public class ActualizarLibro extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LibroServicio servicio = new LibroServicio();
    private AutorServicio autorServicio = new AutorServicio();

    // ==========================
    // CARGAR FORMULARIO EDITAR
    // ==========================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String idParam = request.getParameter("id");

            if (idParam == null || idParam.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/libros?error=id_requerido");
                return;
            }

            int id = Integer.parseInt(idParam);

            LibroDTO libro = servicio.obtenerPorId(id);

            if (libro == null) {
                response.sendRedirect(request.getContextPath() + "/libros?error=id_invalido");
                return;
            }

            // Enviar libro al JSP
            request.setAttribute("libro", libro);

            // 👇 Enviar lista de autores para el select
            request.setAttribute("autores", autorServicio.listar());

            request.getRequestDispatcher("/libros/editar.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/libros?error=id_invalido");
        } catch (Exception e) {
            throw new ServletException("Error al cargar edición de libro", e);
        }
    }

    // ==========================
    // PROCESAR ACTUALIZACIÓN
    // ==========================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            LibroDTO dto = new LibroDTO();

            dto.setId(Integer.parseInt(request.getParameter("id")));
            dto.setTitulo(request.getParameter("titulo"));
            dto.setIsbn(request.getParameter("isbn"));
            dto.setAnio(Integer.parseInt(request.getParameter("anio")));
            dto.setAutorId(Integer.parseInt(request.getParameter("autor_id")));
            dto.setStock(Integer.parseInt(request.getParameter("stock")));

            servicio.actualizar(dto);

            response.sendRedirect(request.getContextPath() + "/libros?actualizado=success");

        } catch (Exception e) {
            throw new ServletException("Error al actualizar libro", e);
        }
    }
}