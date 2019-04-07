package com.jakowaty.piotrbe.router;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class Router
{
	public GenericMapAnalyzer routeMapAnalyzer;
	
	public Router(InputStream routeMapFile, String mapResource) throws Exception
	{		
		this.routeMapAnalyzer = GenericMapAnalyzer.getAnalyzerByFileType(mapResource);
		
		if (!this.routeMapAnalyzer.loadStream(routeMapFile)) {
			throw new Exception("Failed to load routes");
		}
		
	}
	

	public boolean find(String method, String path, PrintWriter response)
	{
//		try {
//			List<?> list = this.routeMapAnalyzer.getKeys();
//			Iterator<?> i = list.iterator();
//			while (i.hasNext()) {
//				response.append((String)i.next());
//			
//			}
//		} catch (Exception e) {
//			response.append("EXCEPTION: " + e.getMessage());
//		}
		
		return false;
	}
}
