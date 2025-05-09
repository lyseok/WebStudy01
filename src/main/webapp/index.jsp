<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>웹컴 페이지</h1>
인증되지 않은 사용자 여부

<c:if test="${not empty authUser }">
	${authUser }님 <a href='<c:url value="/login/logout"></c:url>'>로그아웃</a>
</c:if>
<c:if test="${empty authUser }">
	<a href='<c:url value="login/loginForm.jsp"></c:url>'>로그인하러가기</a>
</c:if>

</body>
</html>