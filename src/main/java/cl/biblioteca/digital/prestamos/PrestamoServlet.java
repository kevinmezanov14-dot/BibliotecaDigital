package cl.biblioteca.digital.prestamos;

import cl.biblioteca.digital.daos.PrestamoDAO;
import cl.biblioteca.digital.daos.ClienteDAO;
import cl.biblioteca.digital.daos.LibroDAO;
import cl.biblioteca.digital.daos.LibroDAOImpl;
import cl.biblioteca.digital.dtos.PrestamoDTO;
import cl.biblioteca.digital.dtos.ClienteDTO;
import cl.biblioteca.digital.dtos.LibroDTO;
import cl.biblioteca.digital.util.Conexion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/prestamos")
public class PrestamoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	try (Connection con = Conexion.getConexion()) {

    	    PrestamoDAO prestamoDAO = new PrestamoDAO(con);
    	    ClienteDAO clienteDAO = new ClienteDAO(con); 
    	    LibroDAO libroDAO = new LibroDAOImpl(); 
    	    prestamoDAO.actualizarPrestamosVencidos();

    	    List<PrestamoDTO> prestamos = prestamoDAO.listar();
    	    List<ClienteDTO> clientes = clienteDAO.listar();
    	    List<LibroDTO> libros = libroDAO.listar();

    	    request.setAttribute("prestamos", prestamos);
    	    request.setAttribute("clientes", clientes);
    	    request.setAttribute("libros", libros);

    	    request.getRequestDispatcher("/WEB-INF/jsp/prestamos.jsp")
    	           .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try (Connection con = Conexion.getConexion()) {

            PrestamoDAO dao = new PrestamoDAO(con);

            if ("registrar".equals(accion)) {

                PrestamoDTO p = new PrestamoDTO();
                p.setClienteId(Integer.parseInt(request.getParameter("clienteId")));
                p.setLibroId(Integer.parseInt(request.getParameter("libroId")));
                String fecha = request.getParameter("fechaVencimiento");
                p.setFechaVencimiento(LocalDateTime.parse(fecha));

                dao.registrar(p);

            } else if ("devolver".equals(accion)) {

                int prestamoId = Integer.parseInt(request.getParameter("id"));
                int libroId = Integer.parseInt(request.getParameter("libroId"));

                dao.devolver(prestamoId, libroId);
            }

            response.sendRedirect("prestamos");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}