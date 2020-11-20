package it.solvingteam.bibliotecaweb.web.servlet.autore;

import java.io.IOException;

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
 * Servlet implementation class DettagliLibroServlet
 */
@WebServlet("/DettagliAutoreServlet")
public class DettagliAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametroIdString = request.getParameter("idDaInviareComeParametro");

		String contextPath = request.getContextPath() + "/CercaAutoreServlet";
		Long parametroIdLong;

		// controlli parametri in get
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

		AutoreService service = MyServiceFactory.getAutoreServiceInstance();

		Autore autore = new Autore();

		try {
			autore = service.caricaSingoloElemento(parametroIdLong);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("autoreDaInviareAPaginaDettagli", autore);

		RequestDispatcher dispatcher = request.getRequestDispatcher("autore/dettagli_autore.jsp");
		dispatcher.forward(request, response);

	}

}
