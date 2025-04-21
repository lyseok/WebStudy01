package kr.or.ddit.servlet03;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/03/movieList")
public class MovieListServlet extends HttpServlet{
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
//		String folderPath = "D:/00.medias/movies";
//		Path filePath =  Paths.get(folderPath);
//		String pattern = "<option>%s</option>";
//		
//		
//		StringBuffer sb = new StringBuffer();
//		
//		sb.append("<html>");
//		sb.append("<body>");
//		sb.append("<form action='/ws01/03/Media'>");
//		sb.append("<select name='video'>");
//		Files.list(filePath).forEach(v->{
//			String fileName = v.getFileName().toString();
//			if(fileName.substring(fileName.indexOf("."), fileName.length()).equals(".mp4")){
//				System.out.println(fileName);
//				sb.append(String.format(pattern, fileName));
//			}
//		});
//		sb.append("</select>");
//		sb.append("<button type='submit'>전송</button>");
//		sb.append("</form>");
//		sb.append("</body>");
//		sb.append("</html>");
//		resp.setContentType("text/html;charset=utf-8");
//		// try~with resource 문법
//		try(
//			PrintWriter out = resp.getWriter();
//		){
//			out.print(sb);
//		}
		File folder = new File(folderPath);
		String[] children = folder.list();
		String options = Arrays.stream(children).filter((fn)->{
			String mime = application.getMimeType(fn);
			return mime!=null && mime.startsWith("video/");
		}).map((fn)->String.format("<option>%s</option>", fn))
		.collect(Collectors.joining("\n"));
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<html>");
		sb.append("<body>");
		sb.append("<form action='"+ req.getContextPath() +"/03/Media'>");
		sb.append("<select onchange='this.form.submit()' name='video'>");
		sb.append(options);
		sb.append("</select>");
//		sb.append("<button type='submit'>전송</button>");
		sb.append("</form>");
		sb.append("</body>");
		sb.append("</html>");
		
		resp.setContentType("text/html;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();
		){
			out.print(sb);
		}
	}
}
