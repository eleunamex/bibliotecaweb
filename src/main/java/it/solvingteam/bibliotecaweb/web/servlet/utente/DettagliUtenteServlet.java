package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Utente;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;
import it.solvingteam.bibliotecaweb.service.utente.UtenteService;

/**
 * Servlet implementation class DettagliUtenteServlet
 */
@WebServlet("/DettagliUtenteServlet")
public class DettagliUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametroIdString = request.getParameter("idDaInviareComeParametro");

		String contextPath = request.getContextPath() + "/CercaUtenteServlet";
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

		UtenteService service = MyServiceFactory.getUtenteServiceInstance();

		Utente utente = new Utente();

		try {
			utente = service.caricaSingoloElemento(parametroIdLong);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("utenteDaInviareAPaginaDettagli", utente);

		RequestDispatcher dispatcher = request.getRequestDispatcher("utente/dettagli_utente.jsp");
		dispatcher.forward(request, response);
	}

}
