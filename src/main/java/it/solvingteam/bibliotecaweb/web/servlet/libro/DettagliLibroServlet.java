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
 * Servlet implementation class DettagliLibroServlet
 */
@WebServlet("/DettagliLibroServlet")
public class DettagliLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametroIdString = request.getParameter("idDaInviareComeParametro");

		String contextPath = request.getContextPath() + "/CercaLibroServlet";
		Long parametroIdLong;

		// controlli
		if (parametroIdString == null || parametroIdString.isEmpty()) {
			response.sendRedirect(contextPath);
			return;
		} else {
			try {
				parametroIdLong = Long.parseLong(request.getParameter("idDaInviareComeParametro"));
			} catch (NumberFormatException nfe) {
				response.sendRedirect(contextPath);
				return;
			}
		}

		LibroService service = MyServiceFactory.getLibroServiceInstance();

		Libro libro = new Libro();

		try {
			libro = service.caricaSingoloElemento(parametroIdLong);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("libroDaInviareAPaginaDettagli", libro);

		RequestDispatcher dispatcher = request.getRequestDispatcher("libro/dettagli_libro.jsp");
		dispatcher.forward(request, response);

	}

}
