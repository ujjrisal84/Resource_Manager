package de.jura.login.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		LoginController_New session = (LoginController_New) req.getSession().getAttribute(
				"loginController");
		String url = req.getRequestURI();
		
		if(session==null||!session.isLogged){
			
			if(url.indexOf("Welcome_New.xhtml")>=0 ||url.indexOf("insert.xhtml")>=0 ||url.indexOf("search.xhtml")>=0  ||url.indexOf("logout.xhtml")>=0){
				res.sendRedirect(((FilterConfig) req).getServletContext().getContextPath()+"/login.xhtml");
			}else{
				chain.doFilter(request, response);
			}
			
			
		} else{
			if(url.indexOf("signup.xhtml")>=0||url.indexOf("login.xhtml")>=0){
				res.sendRedirect(((FilterConfig) req).getServletContext().getContextPath()+"/Welcome_New.xhtml");
			}else if(url.indexOf("logout.xhtml")>=0){
				req.getSession().removeAttribute("loginController");
				res.sendRedirect(((FilterConfig) req).getServletContext().getContextPath()+"/login.xhtml");
				
			}else{
				chain.doFilter(request, response);
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
