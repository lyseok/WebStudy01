<%@page import="java.util.Optional"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
long f(int i){
	if(i <= 0) {
		throw new IllegalArgumentException("팩토리얼 연산의 피연산자는 양의 정수만 입력가능");
	}
	if(i == 1) {
		return 1;
	}
	return i * f(i-1);
}
%>
<%
	String input = request.getParameter("op");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form>
	<input type="number" name="op" min="1" max="10" value="<%=Objects.toString(input, "") %>" onchange="this.form.submit()"/>
</form>
<%
int op = Optional.ofNullable(input)
			.filter(in->in.matches("[0-9]{1,2}"))
			.map(str->Integer.parseInt(str))
			.filter(n->n>=1 && n<=10)
			.orElse(-1);

boolean valid = op != -1;

if(valid){
%>
<h1><%=op %>! = <%= f(op) %></h1>
<%
}
%>
</body>
</html>