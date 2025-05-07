package kr.or.ddit.mbti;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Properties;

import com.google.gson.Gson;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * HttpServlet
 * HttpServletRequest
 * HttpServletResponse
 * HttpSession
 * ServletContext : 하나의 어플리케이션내에서 싱글톤이다
 */
@WebServlet("/csr/mbti/form")
public class MbtiFormControllerCSR extends HttpServlet{
	private Properties mbtiProps;
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		String qualifiedName = "/kr/or/ddit/mbti/Mbti.properties";
		try (
				InputStream is = MbtiFormControllerCSR.class.getResourceAsStream(qualifiedName);
				Reader reader = new InputStreamReader(is, "UTF-8")
		) {	
			mbtiProps = new Properties();
			mbtiProps.load(reader);
			application.setAttribute("mbtiProps", mbtiProps);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (PrintWriter out = resp.getWriter()) {
			out.print(new Gson().toJson(mbtiProps));
		}
	}
}
