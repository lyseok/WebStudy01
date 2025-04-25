<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		<select name="video" onchange="chacngeHandler(event);">
<%-- 		<%=request.getAttribute("options") %>		 --%>
		${children }
		</select>
	</form>
	<div id='resultArea'></div>
</body>
<script>
function chacngeHandler(event){
	let select = event.target;
	console.log(select.value);
	resultArea.innerHTML = `<video src="${pageContext.request.contextPath}/03/Media?\${select.name}=\${select.value}" autoplay controls></video>`;
}
</script>
</html>