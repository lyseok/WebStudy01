<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Http Response Packageing(HttopServletResponse)</h4>
<pre>
Response Line

- Status Code(요청 처리 결과를 표현하는 3자리의 숫자
    - 100~ : ing…
    - 200~ : OK
    - 300~ : response body가 없고, 최종처리가 되릭 위해 클라이언트로부터 추가 엑션이 필요함
        - 302/307 (Moved) : 클라이언트의 요구 자원이 위치가 이미 서버에서 변경됐음을 의미
        Location: (자원의 새로운 위치) 헤더와 함꼐 응답이 전송됨
        → 새로운 위치를 대상으로 새로운 요청을 전송함
        - 304(Not Modified) : 브라우저는 정적 자원에ㅔ 대한 캐싱 정책이 있고, 한번 로드한 정적자원은 캐시 저장소에 저장한다
        이미 캐싱된 정적 자원에 대해 서버쪽으로 요청을 보낸 경우, 서버는 자원의 수정 여부를 응답으로 전송함
        캐싱된 이후 변경된 적이 없는 오래된 자원임을 표현할떄 사용하는 상태코드
        → 캐싱된 자원을 로드하고 소비함
    - 400~ Fail client side error
        - 400(Bad Request) : 백엔드 개발자가 요청을 검증하는 경우에는 직접 사용하는 경우가 많음
        - 404 (Not Found)
        - 405 (Method Not Allowed
        - 406(Not Acceptable) : 클라이언트의 요청 헤더 중 ‘accept헤더 응답 컨텐츠의 종류를 결정
            - accept 헤더에 해당하는 컨텐츠를 서버가 제공할 수 없음을 표현
            - ex) request Accept: application/xml - response content-type : application/json
        - 415(UnSupported Media Type) : 클라이언트의 요청 중 “content-type”헤더는 클라이언트의전송 데이터 종류를 표현함
        - content-type에 해당하는 컨텐츠를 서버가 읽을 수 없음을 표현함
        - ex) request content-type : application/json → 역직렬화가 불가능함을 표현
        - 401(UnAuthorized) 보안처리에 사용되는 상태 코드, 미인증 사용자 표현(인증을 유도하기 위해 사용)
        - 403(Forbidden) 보안처링 사용되는 상태 코드, 인증된 사용자의 권한이 없음을 표현(접근 금지)
    - 500~ : Fail 500(Internal Servet error)

Response Header :  response의 body에 대한 메타 데이터 영역

- Content-Type : response body를 통해 전송되는 컨텐츠의 타입과 종류를 표현하는 MIME
- Content-Length : response body를 통해 전송되는 컨텐츠의 길이
<%--
	response.setContentLengthLong(100);
--%>
- Location
- Refresh
<%--
	out.println(LocalDateTime.now());
	response.setHeader("Refresh", "1");
--%>
<%
	response.setHeader("cache-control", "no-cache");
	response.addHeader("cache-control", "no-store");
%>
- Cache-Control

Response Body

- message-body, content-body
</pre>
</body>
</html>