package com.jakowaty.piotrbe.router;

import java.util.HashMap;
import java.util.Map;

import com.jakowaty.piotrbe.router.exception.RouteNotFoundException;

public class RouteMatcher {
	/**
	 * 
	 * @param requestPathInfo
	 * @param routePattern
	 * @return
	 */
	public boolean match(String requestPathInfo, String routePattern) {
		if (!this.validate(routePattern) || !this.validate(requestPathInfo)) {
			return false;
		}
		// trim both from whitespaces
		requestPathInfo = requestPathInfo.trim();
		routePattern = routePattern.trim();
		
		//trim both from trailing slash
		if (routePattern.endsWith("/")) {
			routePattern = routePattern.substring(0, (routePattern.length() - 1));
		}
		
		if (requestPathInfo.endsWith("/")) {
			requestPathInfo = requestPathInfo.substring(0, (requestPathInfo.length() - 1));
		}
		
		//do they math just like that?
		if (requestPathInfo.equals(routePattern)) {
			return true;
		}
		
		//explode both by '/'
		String[] pathInfoSplitted = requestPathInfo.split("/");
		String[] routePatternSplitted = routePattern.split("/");
		
		//if len matches start comparing
		if (pathInfoSplitted.length == routePatternSplitted.length) {
			for (int i = 0; i < routePatternSplitted.length; i++) {
				if (routePatternSplitted[i].equals(pathInfoSplitted[i])) {
					continue;
				}
				
				if (routePatternSplitted[i].startsWith("{:") && 
					routePatternSplitted[i].endsWith("}")) {
					continue;
				}
				
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param routePattern
	 * @return
	 */
	public boolean validate(String routePattern) {
		return true;
	}
	
	public Map<String, String> extractParams(String requestPathInfo, String routePattern)
	throws RouteNotFoundException {
		if (!this.validate(routePattern) || 
			!this.validate(requestPathInfo) ||
			!this.match(requestPathInfo, routePattern)) {
			throw new RouteNotFoundException();
		}
		
		Map<String, String> params = new HashMap<String, String>();
		String[] pathInfoSplitted = requestPathInfo.split("/");
		String[] routePatternSplitted = routePattern.split("/");
		
		if (requestPathInfo.equals(routePattern)) {
			return params;
		}
		
		if (pathInfoSplitted.length == routePatternSplitted.length) {
			for (int i = 0; i < routePatternSplitted.length; i++) {
				
				if (routePatternSplitted[i].startsWith("{:") && 
					routePatternSplitted[i].endsWith("}")) {
					params.put(
					routePatternSplitted[i]
						.substring(0, (routePatternSplitted[i].length() - 1))
						.replaceFirst("\\{:", ""),
						pathInfoSplitted[i]
					);
						
				}
			}
		}
		
		return params;
	}
}
