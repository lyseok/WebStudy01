package kr.or.ddit.servlet02;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 서블릿 (What / Why / How) ??
 * (What)
 * 웹을 통해 전송된 요청을 수신하고, 그에 맞는 처리를 진행하고, 그 결과로 동적 응답을 생성할 수 있는
 * 자바 객체의 명세(spec). 해당 명세를 구현하고 있는 클래스 HttpServlet
 * 서블릿은 컨테이너에 종속형으로 동작하는 객체.
 * 
 * 컨테이너란?
 * 관리 대상이 되는 객체의 생명주기 관리자 생명주기 -> (객체 생성하는것, 객체가 쓸모없어지면 버리는 것)
 * 
 * (Why)
 * 1. 서블릿 개발 단계
 * 	1) HttpServlet 구현 클래스 정의
 *  2) 필요한 callback 메소드 재정의. callback -> 사람이 호출하는 것이 아닌 시스템이 내부적으로 호출하는 것
 *  	생명주기 callback : 싱글턴 특성에 따라 생명주기 내에서 1번씩 실행 
 *  		init(생성 직후) 
 *  		destroy(소멸 직전)
 *  	요청 callback : 매 요청마다 반복적으로 실행. 
 *  		service : 요청 발생 시 컨테이너가 직접 실행 -> 요청의 종류상관없이 모든 종류에 요청에서 처리하고 싶은경우
 *  		doXXXX	: http method에 따라 service 메소드 내에서 실행 -> 특정 요청에만 처리하고 싶은경우
 *  3) 서블릿 컨테이너에 서블릿 등록.
 *  	servlet 2.x : web.xml에 등록
 *  	servlet 3.x : @WebServlet 으로 등록
 *  		marker어노테이션 -> 무엇인가를 마킹 하기위한 어노테이션 -> @WebServlet
 *  		싱글벨류 어노테이션 -> 값을 하나 가지고 있는 어노테이션 -> @WebServlet("/desc") (value 속성만 속성명 생략 가능)
 *  4) 클라이언트가 사용할 URL 매핑.
 *  	servlet 2.x : web.xml에 매핑 설정
 *  	servlet 3.x : @WebServlet 으로 매핑 설정
 *  5) 서버의 재구동
 *  
 *  메이지로딩전략
 *  
 * 2. 컨테이너의 특성
 *  1) 싱긅턴 : 클래스의 인스턴스를 하나를 생성하고 그걸 공유하는 전략.
 *  2) lazy-loading : 클래스의 인스턴스를 필요한 시점이 되기 전까지 생성을 지연하는 전략
 *  	반대 전략 : eager-loading
 *  	제어가 역전되었다 (IOC패턴)
 *  3) CoC전략. gpt한테 spring과 coc의 관계를 질문해보아라
 *  
 *  web.xml -> 배포 서술자 (Deployment Descriptor)
 */

//@WebServlet("/desc")
// CoC(Convention Over Configuration) 패러다임에 따라 생략한 경우, 적용되는 속성값들이 있음 ex -> @WebServlet(name = "DescriptionServlet")
//@WebServlet(name = "DescriptionServlet", value = "/desc", loadOnStartup = 1, ) // multi value annotation (반드시 속성명 명시)
public class DescriptionServlet extends HttpServlet{
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Description servlet 초기화");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service 메소드 시작");
		super.service(req, resp); // do 계열의 메서드 실행
		System.out.println("service 메소드 종료");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("description servlet 실행");
	}
	
	@Override
	public void destroy() {
		System.out.println("Description servlet 소멸");		
	}
}
