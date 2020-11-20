package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Ruolo;
import it.solvingteam.bibliotecaweb.model.StatoUtente;
import it.solvingteam.bibliotecaweb.model.Utente;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteUpdateUtenteServlet
 */
@WebServlet("/update/ExecuteUpdateUtenteServlet")
public class ExecuteUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String usernameInputParam = request.getParameter("username");
		String passwordInputParam = request.getParameter("password");
		String statoInputParam = request.getParameter("stato");
		String idInputParam = request.getParameter("id");
		String[] idRuolo = request.getParameterValues("idRuolo");

		if (nomeInputParam.isEmpty() || cognomeInputParam.isEmpty() || usernameInputParam.isEmpty()
				|| passwordInputParam.isEmpty() || idRuolo == null) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			try {
				request.setAttribute("listaRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().listAll());
				request.setAttribute("utenteDaInviareAPaginaModifica",MyServiceFactory
						.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(idInputParam)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("../utente/modifica_utente.jsp").forward(request, response);
			return;
		}

		Utente utente = new Utente();
		utente.setId(Long.parseLong(idInputParam));
		utente.setNome(nomeInputParam);
		utente.setCognome(cognomeInputParam);
		utente.setUsername(usernameInputParam);
		if (statoInputParam.equals("DISABILITATO")) {
			utente.setStato(StatoUtente.DISABILITATO);
		} else {
			utente.setStato(StatoUtente.ATTIVO);
		}
		utente.setPassword(passwordInputParam);
		try {
			for (String idRuoloSingolo : idRuolo) {
				Ruolo ruolo = MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(Long.parseLong(idRuoloSingolo));
				utente.getListaRuoli().add(ruolo);
			}
			MyServiceFactory.getUtenteServiceInstance().aggiorna(utente);
			request.setAttribute("successMessage", "Utente aggiornato");
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Operazione fallita");
			try {
				request.setAttribute("listaRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().listAll());
				request.getRequestDispatcher("../utente/modifica_utente.jsp").forward(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		request.getRequestDispatcher("../utente/cerca_utente.jsp").forward(request, response);
	}

}
