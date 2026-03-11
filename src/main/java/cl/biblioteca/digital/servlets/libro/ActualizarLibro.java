package cl.biblioteca.digital.servlets.libro;

import cl.biblioteca.digital.dtos.LibroDTO;
import cl.biblioteca.digital.servicios.LibroServicio;
import cl.biblioteca.digital.servicios.AutorServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Year;

@WebServlet("/actualizar/libros")
public class ActualizarLibro extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private LibroServicio  servicio      = new LibroServicio();
    private AutorServicio  autorServicio = new AutorServicio();

    // CARGAR FORMULARIO 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");

            if (idParam == null || idParam.isBlank()) {
                response.sendRedirect(request.getContextPath() + "/libros?error=id_requerido");
                return;
            }

            int     id    = Integer.parseInt(idParam);
            LibroDTO libro = servicio.obtenerPorId(id);

            if (libro == null) {
                response.sendRedirect(request.getContextPath() + "/libros?error=id_invalido");
                return;
            }

            request.setAttribute("libro",   libro);
            request.setAttribute("autores", autorServicio.listar());
            request.getRequestDispatcher("/libros/editar.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/libros?error=id_invalido");
        } catch (Exception e) {
            throw new ServletException("Error al cargar edición de libro", e);
        }
    }
    // PROCESAR ACTUALIZACIÓN con validaciones
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //  Leer parámetros
            String idStr    = request.getParameter("id");
            String titulo   = request.getParameter("titulo");
            String isbn     = request.getParameter("isbn");
            String anioStr  = request.getParameter("anio");
            String stockStr = request.getParameter("stock");
            String autorStr = request.getParameter("autor_id");

            //  Validar campos obligatorios vacíos 
            if (titulo == null || titulo.isBlank() ||
                isbn   == null || isbn.isBlank()   ||
                anioStr == null || anioStr.isBlank() ||
                stockStr == null || stockStr.isBlank() ||
                autorStr == null || autorStr.isBlank()) {

                recargarFormulario(request, response, idStr,
                        "Todos los campos son obligatorios.");
                return;
            }

            // Validar ISBN (10-50 dígitos, solo números y guiones)
            if (!isbn.trim().matches("[\\d\\-]{10,50}")) {
                recargarFormulario(request, response, idStr,
                        "ISBN inválido. Debe tener entre 10 y 50 caracteres (números y guiones).");
                return;
            }

            // Validar año
            int anioActual = Year.now().getValue();
            int anio;
            try {
                anio = Integer.parseInt(anioStr.trim());
            } catch (NumberFormatException e) {
                recargarFormulario(request, response, idStr, "El año debe ser un número válido.");
                return;
            }
            if (anio < 1000 || anio > anioActual) {
                recargarFormulario(request, response, idStr,
                        "El año debe estar entre 1000 y " + anioActual + ".");
                return;
            }

            // Validar stock (no negativo)
            int stock;
            try {
                stock = Integer.parseInt(stockStr.trim());
            } catch (NumberFormatException e) {
                recargarFormulario(request, response, idStr, "El stock debe ser un número válido.");
                return;
            }
            if (stock < 0) {
                recargarFormulario(request, response, idStr,
                        "El stock no puede ser negativo.");
                return;
            }

            // Validar autor seleccionado
            int autorId;
            try {
                autorId = Integer.parseInt(autorStr.trim());
            } catch (NumberFormatException e) {
                recargarFormulario(request, response, idStr, "Debes seleccionar un autor válido.");
                return;
            }

            // Todo válido → construir DTO y actualizar
            LibroDTO dto = new LibroDTO();
            dto.setId(Integer.parseInt(idStr.trim()));
            dto.setTitulo(titulo.trim());
            dto.setIsbn(isbn.trim());
            dto.setAnio(anio);
            dto.setAutorId(autorId);
            dto.setStock(stock);

            servicio.actualizar(dto);
            response.sendRedirect(request.getContextPath() + "/libros?actualizado=success");

        } catch (Exception e) {
            throw new ServletException("Error al actualizar libro", e);
        }
    }

    // MÉTODO AUXILIAR — recargar formulario con mensaje de error
    private void recargarFormulario(HttpServletRequest request,
                                     HttpServletResponse response,
                                     String idStr,
                                     String mensajeError)
            throws ServletException, IOException, SQLException {

        request.setAttribute("error", mensajeError);

        if (idStr != null && !idStr.isBlank()) {
            try {
                LibroDTO libro = servicio.obtenerPorId(Integer.parseInt(idStr));
                request.setAttribute("libro", libro);
            } catch (Exception ignored) {}
        }

        request.setAttribute("autores", autorServicio.listar());
        request.getRequestDispatcher("/libros/editar.jsp").forward(request, response);
    }
}