package it.solvingteam.bibliotecaweb.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class GuestCheckFilter
 */
@WebFilter(urlPatterns={"/update/*","/insert/*","/delete/*","/libro/inserisci_libro.jsp","/libro/modifica_libro.jsp"
		,"/autore/inserisci_autore.jsp","/autore/modifica_autore.jsp","/utente/*"})
public class GuestCheckFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		Boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		Boolean isClassic = (Boolean)session.getAttribute("isClassic");
		if(!isAdmin && !isClassic) { //se l'utente non è admin ne classic 
			req.getSession().invalidate();
			res.sendRedirect("login.jsp"); // redirect alla login
		}else {
			chain.doFilter(request, response);
		}
	}

}
