<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>HTTP Requsest packaging< : HttpSerlvetRequest</h4>
<% %>
<pre>
	- Request Line
	<% %>
	<%=request.getRequestURI() %>, <%=request.getMethod() %>
	${pageContext.request.requestURI }, ${pageContext.request.method }
    - 수신자(서버의 대상 자원에 대한 표현, URL)
    - 서버의 자원을 대상으로 한 액션의 표현(Request method)
    - Create(등록) : POST
        
        ex) form action  속성(url), method 속성
        
    - Read(조회) : GET(default method)
        
        ex) a href 속성, img src 속성, script src 속성, video src 속성
        
    - Update(수정) : PUT / PATCH
    - Delete(삭제) : DELETE
    - Preflight 요청 : OPTION
    - HEAD : 응답을 수신할때, 응답의 BODY가 없는 상태로 수신할 요청의 표현
    - TRACE : 서버를 대상으로 디버깅을 할때 사용되는 메소드
    
    ex) /member/memberInsert.do, /member/memberUpdate.do, /member/memberDelete.do
    
    RESTful URI구조
    
    /member (POST) : 등록
    
    /member (PUT) : 수정
    
    /member (DELETE) : 삭제 
    
    /member (GET)  : 조회
    
- Request Header : 요청에 대한 부가 정보를 표현하는 메타데이터 영역(name : value)
<%=request.getHeader("Accept") %>
<%-- ${pageContext.request.getHeader("accept") } --%>
${header['accept'] }, ${header.accept }
${header['accept-language'] }
    - Accept : 응답에 대한 조건을 표현
        
        MIME
        
        ex) Accept : application/json, (ajax - dataType : json)
        
        ex) Accept : application/xml, (ajax - dataType : xml)
        
        ex) Accept : text/html, (ajax - dataType : html)
        
        ex) Accept : text/javascript, (ajax - dataType : script)
        
        Accept-Language : 언어와 국가에 표현(locale)
        
        ex) Accept-Language : ko-KR(한글-한국)
        
        ex) Accept-Language : en-US(영어-미국)
        
        ex) Accept-Language : en-UK(영어-영국)
        
        Accept-Encoding : 응답의 압축 포맷 표현(gzip)
        
    - Content-* : 요청의 컨텐츠(content body)에 대한 표현
        
        Content-Length : content body의 길이
        
        Content-Type : content body의 MIME
        
        ex) Content-Type : application/json, 요청 페이로드 전송 형태, 비동기 요청에서 활용
        
        ex) Content-Type : application/x-www-form-urlencoded, 요청 파라미터 전송
        
        ex) Content-Type : multipart/form-data, 멀티파트 요청
        
    - User-Agent : 사용자의 OS, 랜더링 엔진, 브라우저 정보 표현.
- Request Body (Massage Body, Content Body)
    - method=”post”, content-* 해더가 존재하는 경우 형성됨
    1. 요청 파라미터
    2. 멀티 파트
    3. 요청 페이로드
</pre>
<form method="post" enctype="application/json">
	<input type="text" name="dummy1">
	<input type="text" name="dummy2">
	<button type="submit">전송</button>
</form>
<table>
	<thead>
		<tr>
			<th>헤더이름</th>
			<th>헤더값</th>
		</tr>
	</thead>
	<c:forEach items="${header }" var="entry" >
		<tr>
			<td>${entry.key }</td>
			<td>${entry.value }</td>
		</tr>
	</c:forEach>
	<tbody></tbody>
</table>
</body>
</html>