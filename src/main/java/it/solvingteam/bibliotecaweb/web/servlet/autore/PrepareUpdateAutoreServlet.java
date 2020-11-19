package it.solvingteam.bibliotecaweb.web.servlet.autore;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Autore;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;
import it.solvingteam.bibliotecaweb.service.autore.AutoreService;

/**
 * Servlet implementation class PrepareUpdateAutoreServlet
 */
@WebServlet("/PrepareUpdateAutoreServlet")
public class PrepareUpdateAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String parametroIdString = request.getParameter("idDaInviareComeParametro");
		String cercaNomeAutore = request.getParameter("cercaNomeAutore");
		String cercaCognomeAutore = request.getParameter("cercaCognomeAutore");
		
		Long parametroIdLong;
		//controlli
		if (parametroIdString == null || parametroIdString.isEmpty()) {
			response.sendRedirect("index");
			return;
		} else {
			try {
				parametroIdLong = Long.parseLong(request.getParameter("idDaInviareComeParametro"));
			} catch (NumberFormatException nfe) {
				response.sendRedirect("index.jsp");
				return;
			}
		}
		
		AutoreService service = MyServiceFactory.getAutoreServiceInstance();
		
		Autore autore = new Autore();
		
		try {
			autore = service.caricaSingoloElemento(parametroIdLong);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("autoreDaInviareAPaginaModifica", autore);
		request.setAttribute("cercaNomeAutore", cercaNomeAutore);
		request.setAttribute("cercaCognomeAutore", cercaCognomeAutore);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("autore/modifica_autore.jsp");
		dispatcher.forward(request, response);
		
	}

}
