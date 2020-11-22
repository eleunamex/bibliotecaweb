package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
@WebServlet("/insert/ExecuteInsertUtenteServlet")
public class ExecuteInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String usernameInputParam = request.getParameter("username");
		String passwordInputParam = request.getParameter("password");
		String[] idRuolo = request.getParameterValues("idRuolo");

		if (validate(request).isEmpty()) {
			Utente utente = new Utente();
			utente.setNome(nomeInputParam);
			utente.setCognome(cognomeInputParam);
			utente.setUsername(usernameInputParam);
			utente.setPassword(passwordInputParam);
			try {
				for (String idRuoloSingolo : idRuolo) {
					Ruolo ruolo = MyServiceFactory.getRuoloServiceInstance()
							.caricaSingoloElemento(Long.parseLong(idRuoloSingolo));
					utente.getListaRuoli().add(ruolo);
				}
				MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utente);
				request.setAttribute("successMessage", "Utente inserito");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Operazione fallita");
				request.setAttribute("utente", utente);
				try {
					request.setAttribute("listaRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().listAll());
					request.getRequestDispatcher("../utente/inserisci_utente.jsp").forward(request, response);
				} catch (Exception e1) {
					e1.printStackTrace();
					request.setAttribute("errorMessage", "Errori interni");
				}
			}
			request.getRequestDispatcher("../utente/cerca_utente.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("errorValidation", validate(request));
			Utente utente = new Utente();
			utente.setNome(nomeInputParam);
			utente.setCognome(cognomeInputParam);
			utente.setUsername(usernameInputParam);
			utente.setPassword(passwordInputParam);
			if (idRuolo != null && idRuolo.length!=0) {
				for (String idRuoloSingolo : idRuolo) {
					Ruolo ruolo;
					try {
						ruolo = MyServiceFactory.getRuoloServiceInstance()
								.caricaSingoloElemento(Long.parseLong(idRuoloSingolo));
						utente.getListaRuoli().add(ruolo);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("errorMessage", "Errori interni");
					}
				}
			}
			request.setAttribute("utente", utente);
			try {
				request.setAttribute("listaRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().listAll());
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Errori interni");
			}
			request.getRequestDispatcher("../utente/inserisci_utente.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("../utente/cerca_utente.jsp").forward(request, response);

	}

	List<String> validate(HttpServletRequest req) {
		List<String> messaggiErrore = new ArrayList<>();
		String nome = req.getParameter("nome");
		if (nome == null || nome == "") {
			messaggiErrore.add("Nome non è valido");
		}
		String cognome = req.getParameter("cognome");
		if ((cognome == null || cognome == "")) {
			messaggiErrore.add("Cognome non è valido");
		}
		String dataNascita = req.getParameter("username");
		if ((dataNascita == null || dataNascita == "")) {
			messaggiErrore.add("Username non è valido");
		}
		String password = req.getParameter("password");
		if ((password == null || password == "")) {
			messaggiErrore.add("password non è valido");
		}
		String[] idRuolo = req.getParameterValues("idRuolo");
		if ((idRuolo == null || idRuolo.length == 0)) {
			messaggiErrore.add("Devi selezionare almeno un ruolo");
		}
		return messaggiErrore;
	}

}
