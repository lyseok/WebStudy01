<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table{
	border-collapse: collapse;
}
tr, td {
	border: 1px solid black;
}
</style>
</head>
<body>
<form action="" method="post">
	<select name="optMinDan">
		<c:forEach var="optMinDan" begin="2" end="9">
			<option value="${optMinDan}">${optMinDan}단</option>
		</c:forEach>
	</select>
	<select name="optMaxDan">
		<c:forEach var="optMaxDan" begin="2" end="9">
			<option value="${optMaxDan}">${optMaxDan}단</option>
		</c:forEach>	
	</select>
	<button type="submit">전송</button>
</form>

<!-- 2단부터 9단까지 구구단 -->
<!-- 승수는 9까지. 출력형식 : 2 * 3 = 6 -->
<c:if test="${not empty gugudan }">
	<table>
	<c:forEach var="dan" begin="${minDan }" end="${maxDan }">
		<tr>
			<c:forEach var="i" begin="1" end="9">
				<td>
					${dan } * ${i } = ${dan*i }
				</td>
			</c:forEach>
		</tr>
	</c:forEach>
	</table>
	<c:remove var="gugudan"/>
	<c:remove var="minDan"/>
	<c:remove var="maxDan"/>
</c:if>

</body>
</html>