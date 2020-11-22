package it.solvingteam.bibliotecaweb.web.servlet.autore;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Autore;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteUpdateAutoreServlet
 */
@WebServlet("/update/ExecuteUpdateAutoreServlet")
public class ExecuteUpdateAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String dataNascitaInputParam = request.getParameter("dataNascita");
		String idAutoreInputParam = request.getParameter("idAutore");

		// dati della precedente search
		String cercaNomeAutore = request.getParameter("cercaNomeAutore");
		String cercaCognomeAutore = request.getParameter("cercaCognomeAutore");

		if (validate(request).isEmpty()) {
			Autore autore = new Autore();
			autore.setNome(nomeInputParam);
			autore.setCognome(cognomeInputParam);
			autore.setId(Long.parseLong(idAutoreInputParam));
			try {
				autore.setDataNascita(LocalDate.parse(dataNascitaInputParam).plusDays(1L));
			} catch (DateTimeParseException d) {
				request.setAttribute("errorMessage", "Data di nascita non valida");
				request.setAttribute("autoreDaInviareAPaginaModifica", autore);
				request.getRequestDispatcher("../autore/modifica_autore.jsp").forward(request, response);
				return;
			}
			try {
				MyServiceFactory.getAutoreServiceInstance().aggiorna(autore);
				request.setAttribute("successMessage", "Autore aggiornato");
				// dati della precedente search
				Autore autoreSearch = new Autore();
				autoreSearch.setNome(cercaNomeAutore);
				autoreSearch.setCognome(cercaCognomeAutore);
				try {
					request.setAttribute("listaAutoriAttribute",
							MyServiceFactory.getAutoreServiceInstance().cercaAutore(autoreSearch));
					request.setAttribute("cercaNomeAutore", cercaNomeAutore);
					request.setAttribute("cercaCognomeAutore", cercaCognomeAutore);
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.getRequestDispatcher("../autore/risultati_cerca_autore.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("autore", autore);
				request.setAttribute("errorMessage", "Errori interni");
			}
		} else {
			request.setAttribute("errorValidation", validate(request));
			Autore autore = new Autore();
			autore.setNome(nomeInputParam);
			autore.setCognome(cognomeInputParam);
			autore.setId(Long.parseLong(idAutoreInputParam));
			autore.setDataNascita(LocalDate.parse(dataNascitaInputParam).plusDays(1L));
			request.setAttribute("autoreDaInviareAPaginaModifica", autore);
			request.getRequestDispatcher("../autore/modifica_autore.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("../autore/cerca_autore.jsp").forward(request, response);
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
		String dataNascita = req.getParameter("dataNascita");
		if ((dataNascita == null || dataNascita == "")) {
			messaggiErrore.add("Data di nascita non è valida");
		}
		return messaggiErrore;
	}

}
