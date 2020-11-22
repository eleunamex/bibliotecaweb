package it.solvingteam.bibliotecaweb.web.servlet.libro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		
		if(validate(request).isEmpty()) {
			Libro libro = new Libro();
			libro.setId(Long.parseLong(idLibroInputParam));
			libro.setTitolo(titoloInputParam);
			libro.setGenere(genereInputParam);
			libro.setTrama(tramaInputParam);
			Long idAutoreInputParamLong;
			try {
				idAutoreInputParamLong = Long.parseLong(idAutoreInputParam);
			} catch (NumberFormatException e) {
				request.setAttribute("errorMessage", "Autore non valido");
				try {
					request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
					request.setAttribute("libroDaInviareAPaginaModifica", libro);
				} catch (Exception e1) {
					e1.printStackTrace();
					request.setAttribute("errorMessage", "Errori interni");
				}
				request.getRequestDispatcher("../libro/modifica_libro.jsp").forward(request, response);
				return;
			}
			try {
				libro.setAutore(MyServiceFactory.getAutoreServiceInstance().caricaSingoloElemento(idAutoreInputParamLong));
				MyServiceFactory.getLibroServiceInstance().aggiorna(libro);
				request.setAttribute("successMessage", "Libro aggiornato");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Operazione fallita");
				request.setAttribute("libroDaInviareAPaginaModifica", libro);
				request.getRequestDispatcher("../libro/modifica_libro.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("../libro/cerca_libro.jsp").forward(request, response);
		}else{
			request.setAttribute("errorValidation", validate(request));
			Libro libro = new Libro();
			libro.setId(Long.parseLong(idLibroInputParam));
			libro.setTitolo(titoloInputParam);
			libro.setGenere(genereInputParam);
			libro.setTrama(tramaInputParam);
			Long idAutoreInputParamLong;
			try {
				idAutoreInputParamLong = Long.parseLong(idAutoreInputParam);
				libro.setAutore(MyServiceFactory.getAutoreServiceInstance().caricaSingoloElemento(idAutoreInputParamLong));
				request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
			} catch (Exception e) {
				request.setAttribute("errorMessage", "Errori interni");
			}
			request.setAttribute("libroDaInviareAPaginaModifica", libro);
			request.getRequestDispatcher("../libro/modifica_libro.jsp").forward(request, response);
		}
	}
	
	List<String> validate(HttpServletRequest req) {
		List<String> messaggiErrore = new ArrayList<>();
		String genere = req.getParameter("genere");
		if (genere == null || genere == "") {
			messaggiErrore.add("Genere non è valido");
		}
		String titolo = req.getParameter("titolo");
		if (titolo == null || titolo == "") {
			messaggiErrore.add("Titolo non è valido");
		}
		String trama = req.getParameter("trama");
		if (trama == null || trama == "") {
			messaggiErrore.add("Trama non è valida");
		}
		String idAutore = req.getParameter("idAutore");
		if (idAutore == null || idAutore == "") {
			messaggiErrore.add("Selezionare un'autore");
		}
		return messaggiErrore;
	}

}
