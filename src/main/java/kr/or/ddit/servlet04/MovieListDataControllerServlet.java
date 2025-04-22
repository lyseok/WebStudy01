package kr.or.ddit.servlet04;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/04/movieListData")
public class MovieListDataControllerServlet extends HttpServlet{
	private ServletContext application;
	private String folderPath;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		folderPath = application.getInitParameter("movieFolder");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File folder = new File(folderPath);
		String[] children = folder.list((d, n)->{
			String mime = application.getMimeType(n);
			return mime != null & mime.startsWith("video");
		});
		// Marshalling: native data의 표현 구조를 공통 메시지 형식(json)으로 바꾸는 작업
		Gson gson = new Gson();
		String json = gson.toJson(children);
		resp.setContentType("application/json;charset=utf-8");
		try(
			// try with~
			// Closable 객체 생성 및 선언 구문
			// closable -> close가 가능한 객체
			PrintWriter out = resp.getWriter();				
		){
			out.print(json);
		}
		
	}
}
