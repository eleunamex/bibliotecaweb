package it.solvingteam.bibliotecaweb.web.servlet.autore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Autore;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class CercaAutoreServlet
 */
@WebServlet("/CercaAutoreServlet")
public class CercaAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");

		Autore autore = new Autore();
		autore.setNome(nomeInputParam);
		autore.setCognome(cognomeInputParam);

		try {
			request.setAttribute("listaAutoriAttribute",
					MyServiceFactory.getAutoreServiceInstance().cercaAutore(autore));
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("cercaNomeAutore", nomeInputParam);
		request.setAttribute("cercaCognomeAutore", cognomeInputParam);
		request.getRequestDispatcher("autore/risultati_cerca_autore.jsp").forward(request, response);
	}

}
