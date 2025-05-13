<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Http Session</h4>
<h1>session id : ${pageContext.session.id }</h1>

<pre>
<%=session.getId() %>
<a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">a태그 새로고침</a>
<%--
	session.invalidate();
--%>
생성 시점 : ${pageContext.session.creationTime }
마지막 접속 시점 : ${pageContext.session.lastAccessedTime }
timeout : ${pageContext.session.maxInactiveInterval }
<%session.setMaxInactiveInterval(4*60); %>
timeout : ${pageContext.session.maxInactiveInterval }
</pre>
</body>
</html>