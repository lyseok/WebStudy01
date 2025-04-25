package kr.or.ddit.servlet06;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/06/gugudanmy")
public class GugudanModel2ServletMy extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/06/gugudanMy.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int minDan = Integer.parseInt(req.getParameter("optMinDan"));
		int maxDan = Integer.parseInt(req.getParameter("optMaxDan"));
		
		HttpSession session = req.getSession();
		session.setAttribute("gugudan", "1");
		session.setAttribute("minDan", minDan);
		session.setAttribute("maxDan", maxDan);
		resp.sendRedirect(req.getContextPath()+"/06/gugudanmy");
	}
}
