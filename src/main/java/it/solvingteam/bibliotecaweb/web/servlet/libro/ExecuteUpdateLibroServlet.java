package it.solvingteam.bibliotecaweb.web.servlet.libro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Libro;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteUpdateLibroServlet
 */
@WebServlet("/update/ExecuteUpdateLibroServlet")
public class ExecuteUpdateLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idLibroInputParam = request.getParameter("id");
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
			request.getRequestDispatcher("../libro/modifica_libro.jsp").forward(request, response);
			return;
		}

		Long idAutoreInputParamLong;
		Long idLibroInputParamLong;

		try {
			idAutoreInputParamLong = Long.parseLong(idAutoreInputParam);
			idLibroInputParamLong = Long.parseLong(idLibroInputParam);
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			try {
				request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			request.getRequestDispatcher("../libro/modifica_libro.jsp").forward(request, response);
			return;
		}

		Libro libro = new Libro();
		libro.setId(idLibroInputParamLong);
		libro.setTitolo(titoloInputParam);
		libro.setGenere(genereInputParam);
		libro.setTrama(tramaInputParam);
		try {
			libro.setAutore(MyServiceFactory.getAutoreServiceInstance().caricaSingoloElemento(idAutoreInputParamLong));
			MyServiceFactory.getLibroServiceInstance().aggiorna(libro);
			request.setAttribute("successMessage", "Libro aggiornato");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("../libro/cerca_libro.jsp").forward(request, response);

	}

}
