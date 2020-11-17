package it.solvingteam.bibliotecaweb.web.servlet.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ListLibriServlet
 */
@WebServlet("/ListLibriServlet")
public class ListLibriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listaLibriAttribute", MyServiceFactory.getLibroServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("libro/lista_libri.jsp").forward(request, response);
	}


}
