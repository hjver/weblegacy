<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
<!-- a 태그에 javascript 함수를 호출 시 javascript: 단어를 입력 후 호출 -->
${MID}님 환영합니다. <a href="javascript:kakao_logout()">[로그아웃]</a>
<br><br><br>
<input type="hidden" id="MID" value="${mydata.get(0).MID}">
이름 : ${mydata.get(0).MID}<br>
패스워드 : <input type="password" id="MPASS" value=""><br>
<!-- html : disabled를 적용시 Back-end에게 값을 전달하는 방식도 안되며, js에도 핸들링이 안됨-->
연락처 : <input type="text" id="MHP" value="${mydata.get(0).MHP}" maxlength="11" disabled>
<input type="button" value="연락처 수정" id="hpmodify"><br>
이메일 : <input type="text" id="MEMAIL" value="${mydata.get(0).MEMAIL}" disabled>
<input type="button" value="이메일 수정" id="emailmodify"><br>
<input type="button" value="개인정보수정" id="modify_myinfo">

</body>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/v1/kakao.js"></script>
<script src="../myinfo.js?v=1"></script>
<script>
Kakao.init('d1711f107e526eb56e2ce78197402446');
function kakao_logout(){
	console.log(Kakao.Auth.getAccessToken());
	if(!Kakao.Auth.getAccessToken()){
		location.href = './logout.do';
	}
	else{
		Kakao.Auth.setAccessToken(undefined);
		sessionStorage.clear();
		localStorage.clear();
		location.href = './logout.do';
	}
}
</script>
</html>