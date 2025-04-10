<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax - @PutMapping => DTO로 처리</title>
<!-- JQ -->
<script src="./jquery.js"></script>
<script>
	$(function(){
		
		$("#jqbtn").click(function(){
			//$("#pd1").val(); => 사용자가 입력한 값을 가져옴
			$.ajax({
				url : "./ajax14/a123456",
				cache : false,
				type : "PUT",
				dataType : "HTML",
				contentType : "application/json",
				data: JSON.stringify({
					pd1 : $("#pd1").val(),
					pd2 : $("#pd2").val(),
					pd3 : $("#pd3").val(),
					pd4 : $("#pd4").val(),
					pd5 : $("#pd5").val(),
				}),
				async : true,
				success:function($result){
					console.log($result)
				},
				error:function(){
					console.log("Back-end와 통신오류 발생!!")
				}
			});
		});
	});
</script>
</head>
<body>
입력값 : <input type="text" id="pd1"><br>
입력값 : <input type="text" id="pd2"><br>
입력값 : <input type="text" id="pd3"><br>
입력값 : <input type="text" id="pd4"><br>
입력값 : <input type="text" id="pd5"><br>
<input type="button" value="jquery 전송" id="jqbtn"><br>
<input type="button" value="AJAX 전송" onclick="ajax_put()"><br>
<input type="button" value="ES AJAX 전송" id="btn"><br>
</body>
<!-- JS -->
<script>
function ajax_put(){
	//eval() => 문자형태를 Script화 시켜주는 여갈 함수
	var a = 1;
	while(a < 6){
		eval("var pd"+ a + "= document.getElementById('pd'+a).value");
		a++;
	}
	//키배열 pd1 ~ pd5
	var keyarray = {
		pd1 : pd1,
		pd2 : pd2,
		pd3 : pd3,
		pd4 : pd4,
		pd5 : pd5,	
	};
	var json = JSON.stringify(keyarray)
	//console.log(json);
	
	var http,result;
	http = new XMLHttpRequest();
	http.open("PUT","./ajax14/a123456",false);
	http.setRequestHeader("content-type","application/json");
	http.onload = function(){
		result = this.response;
		if(result == "ok"){
			alert("해당 데이터가 올바르게 저장되었습니다.");
			location.reload();
		}
		else{
			alert("해당 정보가 올바르게 저장되지 않았습니다.")
		}
	}
	http.onerror = function(){
		console.log("통신오류!!");
	}
	http.send(json);
}
</script>
<!-- ES -->
<script type="module">
import {api_insert} from "./ajax13.js";
document.querySelector("#btn").addEventListener("click",function(){
	new api_insert().api_put();
});
</script>
</html>