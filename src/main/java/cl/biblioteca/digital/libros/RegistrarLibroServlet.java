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

@WebServlet("/libros/registrar")
public class RegistrarLibroServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AutorServicio autorServicio = new AutorServicio();
    private LibroServicio libroServicio = new LibroServicio();

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String titulo   = request.getParameter("titulo");
            String isbn     = request.getParameter("isbn");
            String anioStr  = request.getParameter("anio");
            String autorStr = request.getParameter("autor");
            String stockStr = request.getParameter("stock");

            // Validar campos obligatorios
            if (titulo == null || titulo.isBlank() ||
                isbn   == null || isbn.isBlank()   ||
                anioStr == null || anioStr.isBlank() ||
                autorStr == null || autorStr.isBlank()) {

                request.setAttribute("error", "Todos los campos son obligatorios.");
                doGet(request, response);
                return;
            }

            LibroDTO libro = new LibroDTO();
            libro.setTitulo(titulo.trim());
            libro.setIsbn(isbn.trim());
            libro.setAnio(Integer.parseInt(anioStr.trim()));
            libro.setAutorId(Integer.parseInt(autorStr.trim()));
            libro.setStock(stockStr == null || stockStr.isBlank() ? 0 : Integer.parseInt(stockStr.trim()));

            libroServicio.registrar(libro);
            response.sendRedirect(request.getContextPath() + "/libros");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al registrar el libro. Intenta nuevamente.");
            doGet(request, response);
        }
    }
}