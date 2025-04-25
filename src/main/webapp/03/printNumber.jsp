<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.color-red{
	color: red;
}
.color-yellow{
	color: yellow;
}
</style>
</head>
<body>
1부터 10까지의 숫자를 li태그로 완성
<ul>
<c:forEach var="i" begin="1" end="10" step="2">
<%-- 	<c:if test="${i % 2 eq 0 }"> --%>
<%-- 		<c:set var="clz" value="color-yellow"/> --%>
<%-- 	</c:if> --%>
	<c:if test="${i % 2 eq 1 }">
		<c:set var="clz" value="color-red"/>
	</c:if>
	<li class="${clz }">${i }</li>
</c:forEach>
</ul>
</body>
</html>