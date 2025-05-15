package kr.or.ddit.login;

import java.io.IOException;


import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

/**
 * request
 */
@WebServlet("/login/loginCheck")
public class LoginCheckServlet extends HttpServlet{
	private AuthenticateService service = new AuthenticateServiceImpl(); 
	/**
	 * 로그인 처리 과정
	 * 1. username/password입력 loginForm.jsp
	 * 2. 데이터베이스로부터 username에 해당하는 회원 정보 조회 - 신원 확인
	 * 3. 저장된 비밀번호와 입력받은 비밀번호 비교 - 본인 여부 확인
	 * 4. 상황에 따른 흐름 제어 구조 필요
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
//	private boolean authenticate(String username, String password) {
//		// 인증여부 판단 기준 : 입력한 username과 password가 동일하면 인증 성공
//		return username.equals(password);
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*
		// 1. 파라미터 받기
		String username = req.getParameter("username");
		String password = req.getParameter("password");
//		Map<String , String> errors = new HashMap<String, String>();
		boolean valid = true;
		
		// 2. 파라미터 검증
		if(username == null || username.trim().isEmpty()) {
			valid = false;
//			errors.put("username", "아이디를 입력하지 않았습니다");
		}
		if(password == null || password.trim().isEmpty()) {
			valid = false;
//			errors.put("password", "비밀번호를 입력하지 않았습니다");
		}
		
		if(!valid) {
			resp.sendRedirect(req.getContextPath() + "/login/loginForm.jsp");
		} else {
			// 3. 로그인 여부 검증
			if(authenticate(username, password)) {
				// 4-1. 로그인 성공 시 메인페이지로 이동
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			} else {
				// 4-2. 로그인 실패시 다시 로그인 폼으로 이동
				resp.sendRedirect(req.getContextPath() + "/login/loginForm.jsp");
			}
			
			
		}*/
		HttpSession session = req.getSession();
//		1. 디코딩 설정
		req.setCharacterEncoding("UTF-8");
		
//		2. 파라미터 수신
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		String dest = null;
		String message = null;
//		3. 파라미터 검증
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
//		5. 검증실패 : 로그인페이지 이동
			dest = "/login/loginForm.jsp";
			message = "아이디나 비밀번호 누락";
		} else {
			// 4. 검증 통과
			MemberVO inputData = new MemberVO();
			inputData.setMemId(username);
			inputData.setMemPassword(password);
			// 4-1. 인증 여부 판단
			if(service.authenticate(inputData)) {
				// 4-2. 인증 성공: 웰컴페이지 이동
				// 1) Principal 구현 객체 생성
				// 2) request.getUserPrincipal에서 반환될 수 있도록 세팅
				// --> Web Filter의 활용
				
				dest = "/";
				session.setAttribute("authUser", inputData);
			} else {
				// 4-3. 인증 실패 : 로그인 페이지 이동
				dest = "/login/loginForm.jsp";
				message = "아이디나 비밀번호가 서로 다른 경우, 로그인 실패";
			} // if(authenticate(username, password)) end
			
		} // if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) end
		
		if(StringUtils.isNotBlank(message)) {
			session.setAttribute("message", message);
		}
		
		String location = req.getContextPath() + dest;
		resp.sendRedirect(location);
		
		
	}
	
}
