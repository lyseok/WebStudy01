package kr.or.ddit.filter;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 웹필터는
 */
public class CharacterEncodingFilter extends HttpFilter{
	private String encoding;
	@Override
	public void init(FilterConfig config) throws ServletException {
		super.init(config);
		encoding = Optional.ofNullable(config.getInitParameter("encoding"))
							.orElse("UTF-8");
	}
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		res.setCharacterEncoding(encoding);
		
		chain.doFilter(req, res);
		
	}
}
