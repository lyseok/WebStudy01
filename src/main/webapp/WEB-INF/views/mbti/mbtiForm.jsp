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

<form action="${pageContext.request.contextPath }/mbti">
	<select name="mbtiType" onchange="this.form.requestSubmit();">
		<c:forEach items="${mbtiProps }" var="mbti">
			<option value="${mbti.key }">${mbti.value }</option>
		</c:forEach>
	</select>
</form>

</body>
</html>