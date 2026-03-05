package cl.biblioteca.digital.clientes;

import cl.biblioteca.digital.daos.ClienteDAO;
import cl.biblioteca.digital.dtos.ClienteDTO;
import cl.biblioteca.digital.util.Conexion;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = Conexion.getConexion()) {

            ClienteDAO dao = new ClienteDAO(conn);
            List<ClienteDTO> lista = dao.listar();

            request.setAttribute("clientes", lista);
            request.getRequestDispatcher("/WEB-INF/jsp/clientes.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = Conexion.getConexion()) {

            ClienteDAO dao = new ClienteDAO(conn);

            ClienteDTO cliente = new ClienteDTO();
            cliente.setNick(request.getParameter("nick"));
            cliente.setEmail(request.getParameter("email"));

            dao.registrar(cliente);

            response.sendRedirect(request.getContextPath() + "/clientes");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}