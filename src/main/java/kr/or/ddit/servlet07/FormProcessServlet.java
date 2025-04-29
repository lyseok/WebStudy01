package kr.or.ddit.servlet07;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.vo.StudentVO;

@WebServlet("/07/formProcess")
public class FormProcessServlet extends HttpServlet{
	private void case1GetParameter(HttpServletRequest req) {
		// 낱개의 파라미터 수신
		String name = req.getParameter("name");
		Integer age = Optional
						.ofNullable(req.getParameter("age"))
						.filter(str->str.matches("[0-9]{1,3}"))
						.map(Integer::parseInt)
						.orElse(null);
		String address = req.getParameter("address");
		String birthday = req.getParameter("birthday");
		String[] license = req.getParameterValues("license");
		String cereer = req.getParameter("cereer");
		String[] hobbies = req.getParameterValues("hobbies");
		String grade = req.getParameter("grade");
		System.out.printf("파라미터 명 : %s, 파라미터 값 : %s\n", "name", name);
		System.out.printf("파라미터 명 : %s, 파라미터 값 : %s\n", "hobbies", Arrays.toString(hobbies));
	}
	
	private void case2GetParameter(HttpServletRequest req) {
		// 파라미터의 이름으로 모든 파라미터를 수신
		Enumeration<String> names = req.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String[] values = req.getParameterValues(name);
			System.out.printf("%s : %s\n", name, Arrays.toString(values));
			
		}
	}
	
	private void case3GetParameter(HttpServletRequest req) {
		// parameter Map 활용
		req.getParameterMap()
			.forEach((k, y)->System.out.printf("%s : %s\n", k, Arrays.toString(y)));
	}
	
	private StudentVO case4GetParameterToCommandObject(HttpServletRequest req) {
		StudentVO commandObject = new StudentVO();
//		commandObject.setName(req.getParameter("name"));
		try {
			BeanUtils.populate(commandObject, req.getParameterMap());
			return commandObject;
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			// 예외 전환 정책
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		Map<String, String[]> paramMap = req.getParameterMap();
//		Set<String> paramKey = paramMap.keySet();
//		Iterator<String> itParam = paramKey.iterator();
//		while(itParam.hasNext()) {
//			String key = itParam.next();
//			String[] values = paramMap.get(key);
//			for(String value : values) {
//				System.out.printf("파라미터 명 : %s, 파라미터 값 : %s\n", key, value);				
//			}
//		}
		
//		case1GetParameter(req);
//		case2GetParameter(req);
//		case3GetParameter(req);
		StudentVO commandObject = case4GetParameterToCommandObject(req);
		System.out.println(commandObject);
		
	}



}
