<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4>클라이언트 전송 데이터의 종류</h4>
<a href="?p1=v1&p2=한글">쿼리 스트링 전송</a>
<form method="post" enctype="multipart/form-data">
	<input type="text" name="p1" value="v1" />
	<input type="text" name="p2" value="한글" />
	<input type="file" name="upload" />
	<button type="submit">전송</button>
</form>
<%
	request.setCharacterEncoding("UTF-8");
%>
<h2>p2 파라미터의 값 : <%=request.getParameter("p2") %></h2>
<pre>
1. query String : request line의 URL을 통해 전송됨.
    - (문자열만 전송, 길이 제한, 보안 취약성)
    - ex) URL?queryString
        section&section&…
        name=value
        value : 한글을 비롯한 특수문자가 url encoding 방식으로 인코딩 이 된 상태로 전송
        수신측(서버)에서 디코딩을 해서 수신해야한다(encoding charset == decoding charset)
        ex) tomcat인 경우, server.xml 변경
    http(비보호 데이터 전송)
    https(secure channel 사용, 암호화된 데이터 전송)
2. request body content 형태
	method(post, put/patch], content-* 헤더
	1. 일반적인 파라미터 형태 (application/x-www-form-urlencoded)
	    - form 태그 필요(method=”post”, enctype=”appliation/x-www-form-urlencoded”)
		- name : 입력태그(form-data)가 가진 name 속성으로 결정
		- String request.getParameter(name)
		- String[ ] request.getParameterValues(name)
		- Enumeration request.getParamerNames( )
		- Map&lt;String, String[ ]&gt; request.getParameterMap( )
	2. 멀티 파트 컨텐츠 형태 (multipart/form-data)
		- orm 태그 필요(method=”post”, enctype=”multipart/form-data”)
		- ex) 파일을 업로드
		- ex) 동시에 여러 형태의 데이터를 전송해야 할때
		- part name : form-data 가 가진 name 속성으로 결정
		- Part request.getPart(name)
		- Collection&lt;Part&gt; request.getParts()
	3. 요청 페이로드 형태 (application/json[xml])
	
	<script>
		<%
// 			request.getInputStream();
		%>
		// 1. request line
		let url = "${pageContext.request.contextPath}/08/sendJson";
		let method = "post";
		// 2. request header
		let headers = {
			"Accept": "text/html",
			"Content-Type": "application/json"
		}
		// 3. request body
		let obj = {prop1: "value1", prop2:34};
		let jsonBody =  JSON.stringify(obj);
		fetch(url,{
			method: method,
			headers: headers,
			body: jsonBody
		});
	</script>
</pre>
</body>
</html>