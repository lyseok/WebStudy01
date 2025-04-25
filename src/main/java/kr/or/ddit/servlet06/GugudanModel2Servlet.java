package kr.or.ddit.servlet06;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/06/gugudan")
public class GugudanModel2Servlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/06/gugudan.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 파라미터 확보(optMinDan, optMaxDan)
		try {
			// 2. 문자열을 숫자로 파싱
			int minDan = Optional
					.ofNullable(req.getParameter("optMinDan"))
					.filter(s->!s.isEmpty())
					.map(Integer::parseInt)
					.filter(n->n>=2&&n<=9)
					.orElseThrow();
			
			int maxDan = Optional
					.ofNullable(req.getParameter("optMaxDan"))
					.filter(s->!s.isEmpty())
					.map(Integer::parseInt)
					.filter(n->n>=2&&n<=9)
					.orElseThrow();
			
			// 3. view로 데이터 전달(scope)
			HttpSession session = req.getSession();
			session.setAttribute("minDan", minDan);
			session.setAttribute("maxDan", maxDan);
			
			// PRG 패턴( Post Redirect Get)
			resp.sendRedirect(req.getContextPath()+"/06/gugudan");
		} catch (Exception e) {
			// 4. 파라미터 누락은 400으로 
			// 5. 숫자가 아닌 파라미터 400으로
			resp.sendError(400, "넘어온 파라미터가 잘못되었습니다.");
		}

	}
}
