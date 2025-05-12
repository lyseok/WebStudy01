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
<h1>요기 타이틀, 로그인 되어있는 사용자의 정보 : <%=request.getUserPrincipal().getName() %>${pageContext.request.userPrincipal.name }</h1>
<a href="${pageContext.request.contextPath }/10/redirectDesc.jsp">새로운 유형 추가</a>
<form action="${pageContext.request.contextPath }/mbti">
	<select name="mbtiType" onchange="this.form.requestSubmit();">
		<c:forEach items="${mbtiProps }" var="mbti">
			<option value="${mbti.key }">${mbti.value }</option>
		</c:forEach>
	</select>
</form>

</body>
</html>