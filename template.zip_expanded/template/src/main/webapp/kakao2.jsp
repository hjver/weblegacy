<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 및 외부 API 로그인 방식(KaKao) - ECMA</title>
</head>
<body>
<form id="frm" method="post" action="./ajax/web_loginok.do">
<input type="hidden" name="code" value="1">
<input type="hidden" name="kakao_id" value="">
<input type="hidden" name="kakao_nicknm" value="">
아이디 : <input type="text" name="mid"><br>
패스워드 : <input type="password" name="mpass"><br>
<input type="submit" value="로그인">
</form><br><br>
<img src="./ajax/kakao_login.png" onclick="kakao_login()">
<p id="token-result"></p>
</body>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/v1/kakao.js"></script>
<script>
  Kakao.init('d1711f107e526eb56e2ce78197402446'); // 사용하려는 앱의 JavaScript 키 입력
  //kakao developers > 문서 > 시작하기 > JavaScript > 레퍼런스 > API
  function kakao_login(){
	  //Kakao.Auth.login : 카카오 회원가입 및 로그인 페이지를 출력하는 함수
	  Kakao.Auth.login({
		  //성공시 출력되는 형태
		  success:function(response){ //response 결과화면
			  Kakao.API.request({  //사용자 가입정보를 요청
				  url: '/v2/user/me', //사용자 정보 가져오기
				  success:function(response){  //API 서버에서 기입정보를 가져옴
					  let id = response["id"]; //고유값
					  //
					  let nickname = response["kakao_account"]["profile"]["nickname"]
					  frm.code.value = "2";
					  frm.kakao_id.value = id;
					  frm.kakao_nicknm.value = nickname;
					  frm.submit();
				  },
				  fail:function(error){
					  console.log("카카오 API 접속오류!!");
				  }
			  });
		  },
		  //API 키가 맞지 않을 경우 출력되는 함수
		  fail:function(error){
				console.log(error);
				console.log("카카로 key 접속 오류 및 환경설정 오류!!");			  
		  }
	  });
  }
</script>


<script>
//ECMA => submit 사용시 return, return false 가 앖음
document.querySelector("#frm").addEventListener("submit",function(e){
	e.preventDefault(); //강제정지
	if(frm.mid.value==""){
		alert("아이디를 입력하세요");
	}
	else if(frm.mpass.value==""){
		alert("패스워드를 입력하세요");
	}
	else{
		frm.submit();
	}
});
</script>
</html>