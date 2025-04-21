package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.Properties;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Lambda 로 표현할 수 있는 functional interface종류
 *  ()->{}
 *  1. Consumer : 하나의 인자를 받고, 그에 대한 실행 함수를 표현, 반환타입이 없음. (소비한다) -> BiCinsumer 인자가 2개
 *  2. Function : 하나의 인자를 받고, 그에 대한 실행이 되면, 새로운 객체가 반환됨.
 *  3. Predicate : 하나의 인자를 받고, 그에 대한 필터링을 수행하고, 최종 반환 boolean이 반환됨.
 *  4. Supplier : 인자가 없고, 새로운 객체가 생성되고 반환됨.
 *  
 *  MIME (Multipurpose Internet Mail Extension) -> 컨텐츠의 소비형태를 결정한다
 *  동적 자원 ? 사용자의 요처잉 발생했을 때 실행처리의 결과물로 전송되는 응답 컨텐츠,
 *  		  요청에 따라 다양한 형태의 컨텐츠가 존재할 수 있다.
 *  전송 자원에 대해 해당 자원의 타입이나 형식에 대한 정보를 함께 제공할 수 있어야함.
 *  --> MIME 텍스트로 표현가능하다.
 *  MIME 형식 : main_type/sub_type;charset=encofing
 *  ex) text/html -> 문자열을 전송할거야 그 문자열은 html로 만들어진 문자열이야(브라우저에서 html을 번역하는 엔진 -> 브라우저 랜더링 엔진)
 *  	MIME을 통해 컨텐츠를 수신한 이후 사용 방식이 결정될 수 있다.
 *  ex) charset=UTF-8 -> 문자열은 UTF-8로 이루어져 있어	
 *  
 *  ex) text/html;charset=UTF-8
 *  ex) text/plain;charset=UTF-8
 *  ex) text/javascript
 *  ex) image/jpeg
 *  ex) video/mpeg
 *  ex) audio/mp3
 *  ex) application/json
 */
@WebServlet("/systemProps")
public class SystemPropertiesServlet extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.printf("%s 초기화 완료\n", this.getClass().getSimpleName());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.printf("현재 객체의 hashCode : %s, 현재 쓰레드 : %s\n"
					, this.hashCode(), Thread.currentThread().getName());
//		String javaVersion = System.getProperty("java.version");
//		System.out.println(javaVersion);

		Properties systemProps = System.getProperties();
//		for(Entry<Object, Object> entry : systemProps.entrySet()) {
//			Object key = entry.getKey();
//			Object value = entry.getValue();
//			System.out.printf("%s : %s\n", key, value);
//		}
		
		StringBuffer sb = new StringBuffer();
		                                        
		sb.append("<html>");
		sb.append("<body>");
		sb.append("<table>");
		sb.append("<thead>");
		sb.append("	<tr>");
		sb.append("		<th>프로퍼티명</th> ");
		sb.append("		<th>프로퍼티값</th> ");
		sb.append("	</tr>");
		sb.append("</thead>");
		sb.append("<tbody>");
		
		String pattern = "<tr><td>%s</td><td>%s</td></tr>";
		
		systemProps.forEach((k, v)->{
//			System.out.printf("%s : %s\n", k, v);
			sb.append(
				String.format(pattern, k, v)		
			);
		});
		sb.append("</tbody>");
		sb.append("</table>");
		sb.append("</body>");
		sb.append("</html>");
		
//		PrintWriter out =  resp.getWriter();
//		out.print(sb);
		resp.setContentType("text/plain;charset=utf-8");
		// try~with resource 문법
		try(
			PrintWriter out = resp.getWriter();
		){
			out.print(sb);
		}
		
	}  

}
