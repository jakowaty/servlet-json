package com.jakowaty.piotrbe.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JspFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException {
		HttpServletRequest r = (HttpServletRequest)request;
		String path = r.getRequestURI().substring(r.getContextPath().length());

		if (path.endsWith(".jsp")) {
//			response.getWriter().append("1");
			chain.doFilter(request, response);
		} else {
//			response.getWriter().append("12");
			request.getRequestDispatcher("/app/" + path).forward(r, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
