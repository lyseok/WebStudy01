package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/05/factorialSEM")
public class FactorialServletJSONSEM extends HttpServlet{
	long factorial(int op) {
		if(op <= 0) throw new IllegalArgumentException("양의 정수만 처리함");
		if(op == 1) return 1;
		return op * factorial(op - 1);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * 1. op 파라미터 확보
		 * 2. 파라미너 누락 여부 확인
		 * 3. 문자열 파라미터를 숫자로 파싱
		 * 4. 연산 실행
		 * 5. {"result":55} 형태 json생성
		 * 6. MIME 결정 후 응답 기록 
		 */
		String opParam = req.getParameter("op");
		
		if(opParam == null || opParam.trim().isEmpty()) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		
		try {
			int op = Integer.parseInt(opParam);
			
			long result = factorial(op);
			
			Map<String, Long> resultMap = Map.of("result", result);
			
			String json = new Gson().toJson(resultMap);
			
			resp.setContentType("application/json;charset=UTF-8");
			
			try (PrintWriter out = resp.getWriter()) {
				out.print(json);
			}
		} catch (NumberFormatException e) {
			resp.sendError(400, e.getMessage());
		}

	}
}
