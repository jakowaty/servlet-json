package com.jakowaty.piotrbe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jakowaty.piotrbe.router.SimpleRouter;

/**
 * This class is about starting "application".
 * Its role is only encapsulate application logic into servlet.
 * It will intercept all requests and push them through Router (as stated in web.xml).
 * Everything is encapsulated in one method - I want it to be portable.
 * For this moment service() method of HttpServlet serves as hipotetical 
 * Application class and its run() method. Everything is constructed and fired in few lines.
 * 
 * @author piotrbe|jakowaty <https://github.com/jakowaty>
 */
public class FrontController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	try {
    		SimpleRouter simpleRouter = new SimpleRouter();
    		resp.getWriter().append(req.getContextPath());
    		simpleRouter.get(
    			"/test-param/{:parametr}",
    			(request, response, router, route) -> {
    				try {
    					for (String k : route.getParams().keySet()) {
    						resp.getWriter().println(k + "==>" + route.getParams().get(k));
    					}
    				} catch (Exception e) {
						return false;
					}
    				return true;
				}
			);
 
    		simpleRouter.run(req, resp);
    	} catch (Exception e) {
			// TODO: handle exception
    		resp.getWriter().append("EXCEPTION: " + e.getMessage());
		}
    }
}