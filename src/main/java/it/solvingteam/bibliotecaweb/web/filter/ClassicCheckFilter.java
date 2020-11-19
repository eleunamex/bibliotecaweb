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

/**
 * Servlet Filter implementation class NotAdminCheckFilter
 */
@WebFilter(urlPatterns={"/utente/*","/CercaUtenteServlet","/delete/DeleteUtenteServlet","/DettagliUtenteServlet","/insert/ExecuteInsertUtenteServlet"
		,"/update/ExecuteUpdateUtenteServlet","/insert/PrepareInsertUtenteServlet","/update/PrepareUpdateUtenteServlet"})
public class ClassicCheckFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Boolean isAdmin = (Boolean)req.getSession(false).getAttribute("isAdmin");
		if(!isAdmin) { //se l'utente non è admin
			req.getSession().invalidate();
			res.sendRedirect("login.jsp"); // redirect alla login
		}else {
			chain.doFilter(request, response);
		}
		
	}

}
