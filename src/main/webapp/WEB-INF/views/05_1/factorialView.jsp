<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form method="post">
	<input type="number" name="op" min="1" max="10" value="${op}" required="required"/>=${result}
	<%
		session.removeAttribute("op");
		session.removeAttribute("result");
	%>
	<button type="submit">전송</button>
</form>
<!-- <h4>절대경로</h4> -->
<%-- <img src="<%=request.getContextPath() %>/resources/images/cat1.jpg"> --%>
<!-- <h4>상대경로</h4> -->
<!-- <img src="../../resources/images/cat1.jpg"> -->
</body>
</html>