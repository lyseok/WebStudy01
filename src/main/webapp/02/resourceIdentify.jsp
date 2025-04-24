<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>자원의 식별 체계</h4>
<pre>
# 자원의 위치에 따른 자원의 종류

1. 파일 시스템 자원
    파일 시스템 상의 절대 경로(물리 경로)를 통해 접근 하는 자원
    ex) D:\00.media\images\cat1.jpg
    
2. 클래스패스 자원
    빌드 및 실행 환경에 존재하는 자원으로 클래스패스 이후의 경로를 qulified name의 형태로 접근하는 자원
    ex) kr.or..ddit.servlet01.HelloServlet.class
    ex) /kr/or/ddit/dummy.xml
    
3. 네트워크  자원(웹 자원)
	
</pre>
<img src="../resources/images/cat1.jpg">
<img src="https://www.google.com/logos/2024/moon/moon_april-rc2/cta.png">
<img src="<%=request.getContextPath() %>/resources/images/cat1.jpg">
</body>
</html>