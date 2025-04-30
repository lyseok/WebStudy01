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
<h4> Http Method </h4>
<form id="target-form" action="${pageContext.request.contextPath }/08/dataProcess">
	<input type="text" name="p1" value="value1" />
	<input type="text" name="p2" value="한글값2" />
	<button type="submit">전송(사용안함)</button>
	<button type="button" data-method="GET">GET 요청</button>
	<button type="button" data-method="POST">POST 요청</button>
	<button type="button" data-method="PUT">PUT 요청</button>
	<button type="button" data-method="DELETE">DELETE 요청</button>
</form>
<pre>
GET(read), POST(insert), PUT(update), DELETE(delete)
ex) 회원 관리, RESTful URI 형태
조회 : /member/memberList.do, /member/memberView[Detail.do
→ /member GET, /member/a001 GET
등록: /member/memberInsert.do
→ /member POST
수정: /member/memberUpdate.do
→ /member/a001 PUT
탈퇴: /member/memberDelete.do
→ /member/a001 DELETE
</pre>
<c:url value="/resources/js/methodAndPayload.js" var="fullPath"></c:url>
<script src="${fullPath }" type="text/javascript"></script>
</body>
</html>