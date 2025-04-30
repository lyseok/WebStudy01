package kr.or.ddit.servlet08;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.vo.DummyData08VO;

@WebServlet("/08/dataProcess")
public class DataProcessServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		super.service(req, resp);
	}
	
	private DummyData08VO requestToCommandObject(HttpServletRequest req) throws ServletException {
		Map<String, String[]> parameterMap = req.getParameterMap();
		DummyData08VO vo = new DummyData08VO();
		try {
			BeanUtils.populate(vo, parameterMap);
			return vo;
		} catch (IllegalAccessException | InvocationTargetException e) {
			// 예외 전환
			throw new ServletException(e);
		}
	}
	
	private boolean validate(DummyData08VO vo) {
		boolean valid = true;
		if(vo.getP1() == null || vo.getP1().isEmpty()) {
			valid = false;			
		}
		if(vo.getP2() == null || vo.getP2().isEmpty()) {
			valid = false;						
		}
		return valid;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 두개의 파라미터가 모두 전달된경우
		
		DummyData08VO vo = requestToCommandObject(req);
		boolean valid = validate(vo);
		
		if(valid) {
			System.out.println(vo.toString());
			// 두 파라미터를 콘솔에 출력한 이후 하단의 메시지를 최종 응답으로 전송
			String message = "<html><body>정상 처리 되었음</body></html>";
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().print(message);
		} else {
			// 둘 중의 하나라도 누락시 400에러 전송
			resp.sendError(400, "파라미터 누락");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DummyData08VO vo = requestToCommandObject(req);
		boolean valid = validate(vo);
		
		if(valid) {
			System.out.println(vo.toString());
			// 두 파라미터를 콘솔에 출력한 이후 하단의 메시지를 최종 응답으로 전송
			String message = "<html><body>정상 처리 되었음</body></html>";
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().print(message);
		} else {
			// 둘 중의 하나라도 누락시 400에러 전송
			resp.sendError(400, "파라미터 누락");
		}
//		StringBuffer sb = new StringBuffer();
//		String line;
//		try {
//			BufferedReader reader = req.getReader();
//			while((line = reader.readLine()) != null) {
//				sb.append(line);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		Gson gson = new Gson();
//		DummyData08VO vo = gson.fromJson(sb.toString(), DummyData08VO.class);
//		System.out.println(vo.toString());
	}
	
	private DummyData08VO requestToCommandObjectFromJson(HttpServletRequest req) throws IOException {
		BufferedReader reader = req.getReader();
		Gson gson = new Gson();
		return gson.fromJson(reader, DummyData08VO.class);
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DummyData08VO vo = requestToCommandObjectFromJson(req);
		
		boolean valid = validate(vo);
		
		if(valid) {
			System.out.println(vo.toString());
			// 두 파라미터를 콘솔에 출력한 이후 하단의 메시지를 최종 응답으로 전송
			String message = "<html><body>정상 처리 되었음</body></html>";
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().print(message);
		} else {
			// 둘 중의 하나라도 누락시 400에러 전송
			resp.sendError(400, "파라미터 누락");
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("삭제 완료");
		resp.setContentType("application/json");
		Map<String, Object> map = Map.of("success", true, "message", "삭제 완료");
		Gson gson = new Gson();
		String json = gson.toJson(map);
		resp.getWriter().print(json);
	}

}
