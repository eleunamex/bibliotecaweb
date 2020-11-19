package it.solvingteam.bibliotecaweb.web.servlet.libro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Libro;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;
import it.solvingteam.bibliotecaweb.service.libro.LibroService;

/**
 * Servlet implementation class DeleteLibroServlet
 */
@WebServlet("/delete/DeleteLibroServlet")
public class DeleteLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String parametroIdString = request.getParameter("idDaInviareComeParametro");

		Long parametroIdLong;
		
		//controlli
		if (parametroIdString == null || parametroIdString.isEmpty()) {
			response.sendRedirect("index.jsp");
			return;
		} else {
			try {
				parametroIdLong = Long.parseLong(request.getParameter("idDaInviareComeParametro"));
			} catch (NumberFormatException nfe) {
				response.sendRedirect("index.jsp");
				return;
			}
		}
		
		LibroService service = MyServiceFactory.getLibroServiceInstance();
		
		Libro libro = new Libro();
		libro.setId(parametroIdLong);
		
		try {
			service.rimuovi(libro);
			request.setAttribute("successMessage", "Libro eliminato");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Operazione fallita");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("../CercaLibroServlet");

		dispatcher.forward(request, response);
	
	}

}
