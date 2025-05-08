<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="${pageContext.request.contextPath }/mbti/create" method="post">
		Type: <input type="text" name="mbtiType"><br/>
		Desc: <input type="text" name="mbtiDesc"><br/>
		<button type="submit">전송</button>
	</form>
</body>
</html>