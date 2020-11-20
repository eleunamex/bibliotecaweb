package it.solvingteam.bibliotecaweb.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.solvingteam.bibliotecaweb.model.Ruolo;
import it.solvingteam.bibliotecaweb.model.Utente;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;
import it.solvingteam.bibliotecaweb.service.utente.UtenteService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usernameParameter = request.getParameter("username");
		String passwordParameter = request.getParameter("password");

		Utente utente = new Utente();

		UtenteService service = MyServiceFactory.getUtenteServiceInstance();

		try {
			utente = service.autenticazione(usernameParameter, passwordParameter);
			if (utente != null) {
				HttpSession oldSession = request.getSession(false);
				if (oldSession != null) { // se già esiste una sessione
					oldSession.invalidate(); // la invalida
				}
				Boolean isAdmin = false;
				Boolean isClassic = false;
				Boolean isGuest = false;
				HttpSession currentSession = request.getSession(); // crea una nuova sessione
				currentSession.setAttribute("user", utente); // setta l'utente nella sessione
				for (Ruolo r : utente.getListaRuoli()) {
					if (r.getCodice().equals("ADMIN_ROLE")) {
						isAdmin = true;
					}
					if (r.getCodice().equals("CLASSIC_ROLE")) {
						isClassic = true;
					}
					if (r.getCodice().equals("GUEST_ROLE")) {
						isGuest = true;
					}
				}
				currentSession.setAttribute("isAdmin", isAdmin);
				currentSession.setAttribute("isClassic", isClassic);
				currentSession.setAttribute("isGuest", isGuest);
				currentSession.setMaxInactiveInterval(3200);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Username o password errati");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Errori interni");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
