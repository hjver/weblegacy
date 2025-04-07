<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX (GET) - 문자열, Array를 이용하여 데이터 전송</title>
</head>
<body>
<input type="button" value="전송" onclick="ajax_gopage1()">

</body>
<!-- 
1. ajax => form 태그로 전송되지 않습니다.(<form></form>(X))
2. FormData함수를 이용하여 전송은 합니다.
3. ajax 브라우져의 URL이 변경되지 않습니다.
4. Back-end가 무조건 결과를 Front-end에게 전송해 주어야 함
 -->
<!-- 
ajax GET 통신 (선택된 상뭄만 Back-end 전송)
1. 같은 이음으로 문자열로 보내면 될까요? => ./ajax1.do?product=1,2,3,4,5
2. 키를 이용하여 배열로 보내면 될까요? => product=['1','2','4']
 -->
<script src="./ajax1.js?v=2"></script>
</html>