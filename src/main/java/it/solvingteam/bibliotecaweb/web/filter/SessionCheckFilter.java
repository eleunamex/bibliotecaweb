package it.solvingteam.bibliotecaweb.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SessionCheckFilter
 */
@WebFilter("/SessionCheckFilter")
public class SessionCheckFilter implements Filter {

	private String contextPath;

	public void init(FilterConfig fC) throws ServletException {
		contextPath = fC.getServletContext().getContextPath();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fC)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String path = ((HttpServletRequest) request).getRequestURI();
		if (path.equals("/bibliotecaweb/login.jsp") || path.equals("/bibliotecaweb/LoginServlet")
				|| path.contains("/assets/")) {
			fC.doFilter(request, response); // continua
		} else {
			if (req.getSession().getAttribute("user") == null) { // se non c'e' l'utente in sessione
				res.sendRedirect(contextPath + "/login.jsp"); // redirect al login
			} else {
				fC.doFilter(request, response);
			}
		}

	}

}
