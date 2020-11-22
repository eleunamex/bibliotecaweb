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
		
		if(validate(request).isEmpty()) {
			Utente utente = new Utente();
			utente.setId(Long.parseLong(idInputParam));
			utente.setNome(nomeInputParam);
			utente.setCognome(cognomeInputParam);
			utente.setUsername(usernameInputParam);
			utente.setPassword(passwordInputParam);
			if (statoInputParam.equals("DISABILITATO")) {
				utente.setStato(StatoUtente.DISABILITATO);
			} else {
				utente.setStato(StatoUtente.ATTIVO);
			}
			try {
				for (String idRuoloSingolo : idRuolo) {
					Ruolo ruolo = MyServiceFactory.getRuoloServiceInstance()
							.caricaSingoloElemento(Long.parseLong(idRuoloSingolo));
					utente.getListaRuoli().add(ruolo);
				}
				MyServiceFactory.getUtenteServiceInstance().aggiorna(utente);
				request.setAttribute("successMessage", "Utente aggiornato");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Operazione fallita");
				try {
					request.setAttribute("listaRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().listAll());
					request.setAttribute("utenteDaInviareAPaginaModifica", utente);
					request.getRequestDispatcher("../utente/modifica_utente.jsp").forward(request, response);
					return;
				} catch (Exception e1) {
					e1.printStackTrace();
					request.setAttribute("errorMessage", "Errori interni");
				}
			}
			request.getRequestDispatcher("../utente/cerca_utente.jsp").forward(request, response);
		}else {
			request.setAttribute("errorValidation", validate(request));
			try {
				request.setAttribute("listaRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().listAll());
				Utente utente = new Utente();
				utente.setId(Long.parseLong(idInputParam));
				utente.setNome(nomeInputParam);
				utente.setCognome(cognomeInputParam);
				utente.setUsername(usernameInputParam);
				utente.setPassword(passwordInputParam);
				if (statoInputParam.equals("DISABILITATO")) {
					utente.setStato(StatoUtente.DISABILITATO);
				} else {
					utente.setStato(StatoUtente.ATTIVO);
				}
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
				request.setAttribute("utenteDaInviareAPaginaModifica", utente);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Errori interni");
			}
			request.getRequestDispatcher("../utente/modifica_utente.jsp").forward(request, response);
		}
		
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
