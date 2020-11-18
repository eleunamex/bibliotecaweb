package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareInsertUtenteServlet
 */
@WebServlet("/PrepareInsertUtenteServlet")
public class PrepareInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setAttribute("listaRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("utente/inserisci_utente.jsp").forward(request, response);
	}

	

}
