package kr.or.ddit.servlet07;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

public class RequestHeaderUtils {
	Map<String, String> requestHeaderToMap(HttpServletRequest req){
		Map<String, String> header = new HashMap<String, String>();
		Enumeration<String> names = req.getHeaderNames();
		while (names.hasMoreElements()) {
			String headerName = (String) names.nextElement();
			header.put(headerName, req.getHeader(headerName));
		}
		req.setAttribute("header", header);
		return header;
	}
}
