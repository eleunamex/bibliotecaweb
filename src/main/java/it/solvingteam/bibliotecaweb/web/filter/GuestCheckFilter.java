package it.solvingteam.bibliotecaweb.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class GuestCheckFilter
 */
@WebFilter("/GuestCheckFilter")
public class GuestCheckFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);

		String path = ((HttpServletRequest) request).getRequestURI();
		if (path.equals("/LogoutServlet")) {
			chain.doFilter(request, response); // continua
		}

		Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
		Boolean isClassic = (Boolean) session.getAttribute("isClassic");

		if (!isAdmin && !isClassic) { // se l'utente non è admin ne classic
			RequestDispatcher rq = req.getRequestDispatcher("/LogoutServlet"); // redirect alla login
			rq.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

}
