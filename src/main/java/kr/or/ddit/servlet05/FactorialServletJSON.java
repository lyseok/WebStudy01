package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/05/factorial")
public class FactorialServletJSON extends HttpServlet{
	long factorial(int op) {
		if(op <= 0) throw new IllegalArgumentException("양의 정수만 처리함");
		if(op == 1) return 1;
		return op * factorial(op - 1);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = req.getParameter("op");
		
		
		try {
			int op = Optional
						.ofNullable(str)
						.map(Integer::parseInt)
						.orElseThrow();
			
			long result = factorial(op);
			Map<String, Long> map = new HashMap<String, Long>();
			map.put("result", result);
			Gson gson = new Gson();
			
			String json = gson.toJson(map);
			resp.setContentType("application/json;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(json);
			
		} catch (Exception e) {
			resp.sendError(400);
		}

	}
}
