package it.solvingteam.bibliotecaweb.web.servlet.libro;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Autore;
import it.solvingteam.bibliotecaweb.model.Libro;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteInsertLibro
 */
@WebServlet("/insert/ExecuteInsertLibroServlet")
public class ExecuteInsertLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String genereInputParam = request.getParameter("genere");
		String titoloInputParam = request.getParameter("titolo");
		String tramaInputParam = request.getParameter("trama");
		String idAutoreInputParam = request.getParameter("idAutore");

		// controlli
		if (genereInputParam.isEmpty() || titoloInputParam.isEmpty() || tramaInputParam.isEmpty()
				|| idAutoreInputParam.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			try {
				request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("../libro/inserisci_libro.jsp").forward(request, response);
			return;
		}

		Long idAutoreInputParamLong;

		try {
			idAutoreInputParamLong = Long.parseLong(idAutoreInputParam);
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			try {
				request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			request.getRequestDispatcher("../libro/inserisci_libro.jsp").forward(request, response);
			return;
		}

		if (idAutoreInputParamLong == 0) {

			String nomeAutoreInputParam = request.getParameter("nome");
			String cognomeAutoreInputParam = request.getParameter("cognome");
			String dataNascitaAutoreInputParam = request.getParameter("dataNascita");

			Libro libro = new Libro();
			libro.setTitolo(titoloInputParam);
			libro.setGenere(genereInputParam);
			libro.setTrama(tramaInputParam);
			Autore autore = new Autore();
			autore.setNome(nomeAutoreInputParam);
			autore.setCognome(cognomeAutoreInputParam);
			autore.setDataNascita(LocalDate.parse(dataNascitaAutoreInputParam));
			libro.setAutore(autore);

			try {
				MyServiceFactory.getAutoreServiceInstance().inserisciNuovo(autore);
				MyServiceFactory.getLibroServiceInstance().inserisciNuovo(libro);
				request.setAttribute("successMessage", "Libro e autore inseriti");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Libro libro = new Libro();
			libro.setTitolo(titoloInputParam);
			libro.setGenere(genereInputParam);
			libro.setTrama(tramaInputParam);
			Autore autore = new Autore();
			autore.setId(idAutoreInputParamLong);
			libro.setAutore(autore);
			try {
				MyServiceFactory.getLibroServiceInstance().inserisciNuovo(libro);
				request.setAttribute("successMessage", "Libro inserito");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("../libro/cerca_libro.jsp").forward(request, response);

	}

}
