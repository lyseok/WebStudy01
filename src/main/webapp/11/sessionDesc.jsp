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
<pre>
<%=session.getId() %>
<a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">a태그 새로고침</a>
<%--
	session.invalidate();
--%>
</pre>
</body>
</html>