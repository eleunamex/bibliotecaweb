package it.solvingteam.bibliotecaweb.web.servlet.libro;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

		String nomeAutoreInputParam = request.getParameter("nome");
		String cognomeAutoreInputParam = request.getParameter("cognome");
		String dataNascitaAutoreInputParam = request.getParameter("dataNascita");

		Long idAutoreInputParamLong = null;
		idAutoreInputParamLong = Long.parseLong(idAutoreInputParam);

//		try {
//			idAutoreInputParamLong = Long.parseLong(idAutoreInputParam);
//		} catch (NumberFormatException e) {
//			try {
//				request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			request.setAttribute("errorMessage", "Selezionare o inserire almeno un autore");
//			request.getRequestDispatcher("../libro/inserisci_libro.jsp").forward(request, response);
//			return;
//		}

		if (idAutoreInputParamLong != 0) { // SE HO UN AUTORE
			if (validateLibro(request).isEmpty()) {
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
					request.setAttribute("errorMessage", "Operazione fallita");
					try {
						request.setAttribute("listaAutoriAttribute",
								MyServiceFactory.getAutoreServiceInstance().listAll());
					} catch (Exception e1) {
						e1.printStackTrace();
						request.setAttribute("errorMessage", "Errori interni");
					}
					request.getRequestDispatcher("../libro/inserisci_libro.jsp").forward(request, response);
					return;
				}
				request.getRequestDispatcher("../libro/cerca_libro.jsp").forward(request, response);

			} else { // SE CI SONO ERRORI DI VALIDAZIONE
				request.setAttribute("errorValidation", validateLibro(request));
				Libro libro = new Libro();
				libro.setTitolo(titoloInputParam);
				libro.setGenere(genereInputParam);
				libro.setTrama(tramaInputParam);
				try {
					libro.setAutore(MyServiceFactory.getAutoreServiceInstance()
							.caricaSingoloElemento(Long.parseLong(idAutoreInputParam)));
					request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
					request.setAttribute("erroValidation", validateLibro(request));
					request.setAttribute("libro", libro);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("errorMessage", "Errori interni");
				}
				request.getRequestDispatcher("../libro/inserisci_libro.jsp").forward(request, response);
				return;
			}

		} else {// SE NON HO UN AUTORE
			if (validateLibro(request).isEmpty() && validateAutore(request).isEmpty()) {
				Autore autore = new Autore();
				autore.setNome(nomeAutoreInputParam);
				autore.setCognome(cognomeAutoreInputParam);
				autore.setDataNascita(LocalDate.parse(dataNascitaAutoreInputParam));
				Libro libro = new Libro();
				libro.setGenere(genereInputParam);
				libro.setTitolo(titoloInputParam);
				libro.setTrama(tramaInputParam);
				libro.setAutore(autore);
				try {
					MyServiceFactory.getAutoreServiceInstance().inserisciNuovo(autore);
					MyServiceFactory.getLibroServiceInstance().inserisciNuovo(libro);
					request.setAttribute("successMessage", "Libro e autore inseriti");
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("errorMessage", "Operazione fallita");
					request.getRequestDispatcher("../libro/inserisci_libro.jsp").forward(request, response);
					return;
				}
				request.getRequestDispatcher("../libro/cerca_libro.jsp").forward(request, response);
			} else {// SE CI SONO ERRORI DI VALIDAZIONE
				
				List<String> listaErrori = new ArrayList<>();
				for(String errore : validateLibro(request)) {
					listaErrori.add(errore);
				}
				for(String errore : validateAutore(request)) {
					listaErrori.add(errore);
				}
				request.setAttribute("errorValidation", listaErrori);
				try {
					request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("errorMessage", "Errori interni");
					request.getRequestDispatcher("../libro/insert_libro.jsp").forward(request, response);
					return;
				}
				Autore autore = new Autore();
				autore.setNome(nomeAutoreInputParam);
				autore.setCognome(cognomeAutoreInputParam);
				if (dataNascitaAutoreInputParam != null && dataNascitaAutoreInputParam != "") {
					autore.setDataNascita(LocalDate.parse(dataNascitaAutoreInputParam));
				}
				Libro libro = new Libro();
				libro.setGenere(genereInputParam);
				libro.setTitolo(titoloInputParam);
				libro.setTrama(tramaInputParam);
				request.setAttribute("libro", libro);
				request.setAttribute("autore", autore);
			}
			request.getRequestDispatcher("../libro/inserisci_libro.jsp").forward(request, response);
		}
	}

	List<String> validateLibro(HttpServletRequest req) {
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

	List<String> validateAutore(HttpServletRequest req) {
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
