package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Ruolo;
import it.solvingteam.bibliotecaweb.model.Utente;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteInsertUtenteServlet
 */
@WebServlet("/ExecuteInsertUtenteServlet")
public class ExecuteInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String usernameInputParam = request.getParameter("username");
		String passwordInputParam = request.getParameter("password");

		if (nomeInputParam.isEmpty() || cognomeInputParam.isEmpty() || usernameInputParam.isEmpty()
				|| passwordInputParam.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			try {
				request.setAttribute("listaRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().listAll());
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("utente/inserisci_utente.jsp").forward(request, response);
			return;
		}

		Utente utente = new Utente();
		utente.setNome(nomeInputParam);
		utente.setCognome(cognomeInputParam);
		utente.setUsername(usernameInputParam);
		utente.setPassword(passwordInputParam);
		Ruolo r = new Ruolo();
		try {
			for (Ruolo ruolo : MyServiceFactory.getRuoloServiceInstance().listAll()) {
				String ruoloInputParam = request.getParameter("idRuolo" + ruolo.getId());
				if (ruoloInputParam != null) {
					r.setId(Long.parseLong(ruoloInputParam));
					utente.getListaRuoli().add(r);
				}
			}
			MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utente);
			request.setAttribute("successMessage", "Utente inserito");
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("utente/inserisci_utente.jsp").forward(request, response);
	}

}
