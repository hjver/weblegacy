<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
<!-- a 태그에 javascript 함수를 호출 시 javascript: 단어를 입력 후 호출 -->
${MID}님 환영합니다. <a href="javascript:kakao_logout()">[로그아웃]</a>
</body>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/v1/kakao.js"></script>
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