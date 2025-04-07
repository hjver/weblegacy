<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jquery - Jquery로 전송(POST) - JSON.stringify를 이용한 전송</title>
<script src="./jquery.js"></script>
<script>
$(function(){
	
	var $data = new Array();
	$data[0] = "aa";
	$data[1] = "bb";
	$data[2] = "cc";
	
	//JSON.stringify 전송 : 대표키로 보낼까요? 대표키 없이 보낼까요?
	$("#btn").click(function(){
		
		var $fdata = JSON.stringify($data);
		$.ajax({
			url : "./ajax6.do",
			cache:false,  
			type:"POST",   
			dataType:"HTML", 
			contentType:"application/json",
			//data : JSON.stringify($data), //대표키 없이 전송
			data : JSON.stringify({userdata, $data}), //대표키가 있는 상황
			async : true, 
			success:function($result){
				console.log($result)
			},
			error:function(){
				console.log("Back-end와 통신오류 발생!!");
			}
		});
	});
});
</script>
</head>
<body>
<input type="button" value="전송" id="btn">
</body>
</html>