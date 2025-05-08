package kr.or.ddit.mbti;

import java.io.IOException;
import java.util.Properties;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mbti/create")
public class MbtiCreateController extends HttpServlet{
	private Properties mbtiProps;
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext(); 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		mbtiProps = (Properties)application.getAttribute("mbtiProps");
//		1. 두개의 파라미터를 받는다
		String type = (String)req.getParameter("mbtiType");
		String desc = (String)req.getParameter("mbtiDesc");
		
//		2. 두개는 필수 파라미터이므로 누락시 400에러 발생
		if(type == null || desc == null || type.trim().isEmpty() || desc.trim().isEmpty()) {
			resp.sendError(400, "필수 파라미터 누락");
		}
//		3. 검증 통과시 mbti새로 추가
		mbtiProps.setProperty(type, desc);
		
//		4. 새로 갱신된 자원을 새로 가져갈 수 있어야한다
//		5. redirect로 이동
		
		resp.sendRedirect(req.getContextPath()+"/mbti/form");
//		6. 다시 16개로 갱신된 자원을 서비스 해야한다
		
	}
}
