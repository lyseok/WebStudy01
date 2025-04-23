<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JSP(Java Server Page)</h1>
<h1>현재 서버의 시간 : <%=LocalDateTime.now() %></h1>
<%@ include file="../fragments/commomTitle.jsp" %>
<pre>
	# JSP

서블릿의 하위 스펙으로 자바 기반의 스크립트 형태의 템플릿 엔진

(서버사이드에서 템플릿과 데이터를 결합하는 SSR방식을 사용함)

HTML형태의 동적 자원을 생성하기 위해 사용됨(Model2구조에서 주로 활용됨)

- 서블릿을 상속받고 있다 그러므로 JSP는 서블릿이다
- 스크립트 형태로 컴파일이 필요 없다

## JSP 소스 구성요소

1. 정적 텍스트(template)
2. 서버사이드에서 동작하는 스크립틀릿(scriptlet)
    1. scriptlet : 
    <%
    	String data = "DATA";
    %>
    2. directive(지시자) : 
    <%--@ 지시자명 속성... --%> 
    3. expression(표현식) : <%--= 값이나 혹은 출력할 expression --%>
    4. declaration(선언부) : 
    <%!
    	void test(){}
   	%>
    5. comment :
	<!-- HTML 주석 -->
	<script>
	// JS 주석
	</script>
	<style>
	/* CSS주석 */
	</style>
	<% // java 주석 %>
	<%-- scriptlet 주석 --%>
	6)EL : ${attr_name }
	7)JSTH(Java Standard Tag Library) : 커스텀 태그 라이브러리
</pre>
</body>
</html>