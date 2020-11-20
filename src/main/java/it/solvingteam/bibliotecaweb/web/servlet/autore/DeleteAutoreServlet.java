package it.solvingteam.bibliotecaweb.web.servlet.autore;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Autore;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;
import it.solvingteam.bibliotecaweb.service.autore.AutoreService;

/**
 * Servlet implementation class DeleteAutoreServlet
 */
@WebServlet("/delete/DeleteAutoreServlet")
public class DeleteAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametroIdString = request.getParameter("idDaInviareComeParametro");

		Long parametroIdLong;

		// controlli
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

		AutoreService service = MyServiceFactory.getAutoreServiceInstance();

		Autore autore = new Autore();
		autore.setId(parametroIdLong);

		try {
			service.rimuovi(autore);
			request.setAttribute("successMessage", "Autore eliminato");
		} catch (SQLIntegrityConstraintViolationException con) {
			con.printStackTrace();
			request.setAttribute("errorMessage", "Non puoi eliminare un autore se ha dei libri");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Operazione fallita");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("../CercaAutoreServlet");

		dispatcher.forward(request, response);

	}

}
