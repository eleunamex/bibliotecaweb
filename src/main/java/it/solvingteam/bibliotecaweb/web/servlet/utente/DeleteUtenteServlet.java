package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Utente;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;
import it.solvingteam.bibliotecaweb.service.utente.UtenteService;

/**
 * Servlet implementation class DeleteUtenteServlet
 */
@WebServlet("/delete/DeleteUtenteServlet")
public class DeleteUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
String parametroIdString = request.getParameter("idDaInviareComeParametro");
		
		Long parametroIdLong;
		
		//controlli
		if (parametroIdString == null || parametroIdString.isEmpty()) {
			response.sendRedirect("index.jsp");
			return;
		} else {
			try {
				parametroIdLong = Long.parseLong(request.getParameter("idDaInviareComeParametro"));
			} catch (NumberFormatException nfe) {
				response.sendRedirect("index.jsp");
				return;
			}
		}
		
		UtenteService service = MyServiceFactory.getUtenteServiceInstance();
		
		Utente utente = new Utente();
		utente.setId(parametroIdLong);
		
		try {
			service.rimuovi(utente);
			request.setAttribute("successMessage", "Utente Eliminato");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Operazione fallita");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("../CercaUtenteServlet");

		dispatcher.forward(request, response);
	
	}


}
