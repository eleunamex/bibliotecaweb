package it.solvingteam.bibliotecaweb.web.servlet.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.service.MyServiceFactory;
import it.solvingteam.bibliotecaweb.model.Autore;
import it.solvingteam.bibliotecaweb.model.Libro;

/**
 * Servlet implementation class CercaLibroServlet
 */
@WebServlet("/CercaLibroServlet")
public class CercaLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String genereInputParam = request.getParameter("genere");
		String titoloInputParam = request.getParameter("titolo");
		String tramaInputParam = request.getParameter("trama");
		String nomeAutoreInputParam = request.getParameter("nomeAutore");
		String cognomeAutoreInputParam = request.getParameter("cognomeAutore");

		Libro libro = new Libro();
		libro.setGenere(genereInputParam);
		libro.setTitolo(titoloInputParam);
		libro.setTrama(tramaInputParam);
		Autore autore = new Autore();
		autore.setNome(nomeAutoreInputParam);
		autore.setCognome(cognomeAutoreInputParam);
		libro.setAutore(autore);

		try {
			request.setAttribute("listaLibriAttribute", MyServiceFactory.getLibroServiceInstance().cercaLibri(libro));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("libro/risultati_cerca_libro.jsp").forward(request, response);
	}

}
