package it.solvingteam.bibliotecaweb.web.servlet.autore;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Autore;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteUpdateAutoreServlet
 */
@WebServlet("/ExecuteUpdateAutoreServlet")
public class ExecuteUpdateAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String dataNascitaInputParam = request.getParameter("dataNascita");
		String idAutoreInputParam = request.getParameter("idAutore");
		
		if (nomeInputParam.isEmpty() || cognomeInputParam.isEmpty() || dataNascitaInputParam.isEmpty()){
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("autore/modifica_autore.jsp").forward(request, response);
			return;
		}
		
		Autore autore = new Autore();
		autore.setId(Long.parseLong(idAutoreInputParam));
		autore.setNome(nomeInputParam);
		autore.setCognome(cognomeInputParam);
		autore.setDataNascita(LocalDate.parse(dataNascitaInputParam).plusDays(1L));
		
		try {
			MyServiceFactory.getAutoreServiceInstance().aggiorna(autore);
			request.setAttribute("successMessage", "Autore aggiornato");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Operazione fallita");
		}
		request.getRequestDispatcher("autore/modifica_autore.jsp").forward(request, response);
	}

}
