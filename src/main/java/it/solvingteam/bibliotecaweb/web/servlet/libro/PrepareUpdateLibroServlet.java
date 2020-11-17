package it.solvingteam.bibliotecaweb.web.servlet.libro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.Libro;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;
import it.solvingteam.bibliotecaweb.service.libro.LibroService;

/**
 * Servlet implementation class PrepareUpdateLibroServlet
 */
@WebServlet("/PrepareUpdateLibroServlet")
public class PrepareUpdateLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String parametroIdLibroDaModificareString = request.getParameter("idDaInviareComeParametro");
		
		String contextPath = request.getContextPath() + "/index.jsp";
		Long parametroIdCategoriaDaModificare;
		//controlli
		if (parametroIdLibroDaModificareString == null || parametroIdLibroDaModificareString.isEmpty()) {
			response.sendRedirect(contextPath);
			return;
		} else {
			try {
				parametroIdCategoriaDaModificare = Long.parseLong(request.getParameter("idDaInviareComeParametro"));
			} catch (NumberFormatException nfe) {
				response.sendRedirect(contextPath);
				return;
			}
		}
		
		LibroService service = MyServiceFactory.getLibroServiceInstance();
		
		Libro libro = new Libro();
		
		try {
			libro=service.caricaSingoloElemento(parametroIdCategoriaDaModificare);
			request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("libroDaInviareAPaginaModifica", libro);

		RequestDispatcher dispatcher = request.getRequestDispatcher("libro/modifica_libro.jsp");
		dispatcher.forward(request, response);
		
		
		
	}


}
