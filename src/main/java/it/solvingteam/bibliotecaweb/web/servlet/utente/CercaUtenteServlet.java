package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Utente;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class CercaUtenteServlet
 */
@WebServlet("/CercaUtenteServlet")
public class CercaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String usernameInputParam = request.getParameter("username");

		Utente utente = new Utente();
		utente.setNome(nomeInputParam);
		utente.setCognome(cognomeInputParam);
		utente.setUsername(usernameInputParam);

		try {
			request.setAttribute("listaUtentiAttribute",
					MyServiceFactory.getUtenteServiceInstance().cercaUtente(utente));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("utente/risultati_cerca_utente.jsp").forward(request, response);
	}

}
