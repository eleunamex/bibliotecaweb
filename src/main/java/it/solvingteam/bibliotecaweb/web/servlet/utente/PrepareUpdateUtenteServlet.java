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
 * Servlet implementation class PrepareUpdateUtenteServlet
 */
@WebServlet("/update/PrepareUpdateUtenteServlet")
public class PrepareUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametroIdString = request.getParameter("idDaInviareComeParametro");
		Long parametroIdLong;
		// controlli
		if (parametroIdString == null || parametroIdString.isEmpty()) {
			response.sendRedirect("index");
			return;
		} else {
			try {
				parametroIdLong = Long.parseLong(request.getParameter("idDaInviareComeParametro"));
			} catch (NumberFormatException nfe) {
				response.sendRedirect("index.jsp");
				return;
			}
		}

		UtenteService service = MyServiceFactory.getUtenteServiceInstance();

		Utente utente = new Utente();

		try {
			utente = service.caricaSingoloElemento(parametroIdLong);
			request.setAttribute("listaRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("utenteDaInviareAPaginaModifica", utente);
		RequestDispatcher dispatcher = request.getRequestDispatcher("../utente/modifica_utente.jsp");
		dispatcher.forward(request, response);

	}

}
