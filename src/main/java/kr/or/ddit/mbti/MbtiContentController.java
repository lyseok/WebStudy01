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

@WebServlet("/mbti")
public class MbtiContentController extends HttpServlet {
	private Properties mbtiProps;
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext(); 
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		mbtiProps = (Properties)application.getAttribute("mbtiProps");
		
		// 1. 데이터 받아오기
		String mbtiType = req.getParameter("mbtiType");
		String layout = req.getParameter("layout");
		
		System.out.println(mbtiType);
		// 2-1. 입력받은 데이터가 있는지
		if(mbtiType == null || mbtiType.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		// 2-2. 입력 받은 데이터가 유효한지
		 if(!mbtiProps.containsKey(mbtiType)) {
			 resp.sendError(HttpServletResponse.SC_NOT_FOUND, String.format("%s에 해당하는 mbti는 없음", mbtiType));
			 return;
		 }
		 
		 // 3. 있다면 해당 데이터의 jsp로 forward
		 String prefix = "/WEB-INF/views/mbti/";
		 String suffix = ".html";
		 String path = prefix + mbtiType + suffix;
		 String contentPage = path;
		 
		 // 4. 이동 방식 결정
		 if(layout!=null&&layout.equals("none")) {
			 // 비동기 요청으로 fragment한 경우
			 req.getRequestDispatcher(path).forward(req, resp);			 
		 } else {
			 req.setAttribute("contentPage", contentPage);
			 req.getRequestDispatcher("/WEB-INF/views/mbti/mbtiModule/layout.jsp").forward(req, resp);			 
		 }
		 
	}
}
