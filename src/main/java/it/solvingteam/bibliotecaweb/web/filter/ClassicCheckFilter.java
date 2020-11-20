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

/**
 * Servlet Filter implementation class NotAdminCheckFilter
 */
@WebFilter("/ClassicCheckFilter")
public class ClassicCheckFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = ((HttpServletRequest) request).getRequestURI();
		
		if (path.equals("/LogoutServlet")) {
			chain.doFilter(request, response); // continua 
		}
		
		Boolean isAdmin = (Boolean)req.getSession(false).getAttribute("isAdmin");
		
		if(!isAdmin) { //se l'utente non è admin
			RequestDispatcher rq = req.getRequestDispatcher("/LogoutServlet"); // redirect alla login
			rq.forward(request, response);
		}else {
			chain.doFilter(request, response);
		}
		
	}

}
