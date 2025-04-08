<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete - AJAX</title>
<script src="./jquery.js"></script>
</head>
<body>
<form>
<input type="hidden" name="midx" value="5,9,12">
</form>
<input type="button" value="JS삭제" onclick="ajax_del()">
<input type="button" value="ES삭제" id="ajax_del">
</body>
<script>
//ECMA => JSON.stringify(this.arr) => RequestBody
document.querySelector("#ajax_del").addEventListener("click",function(){
	this.arr = ["5", "9", "12", "15"];
	fetch("./ajax13/a123456",{
		method : "DELETE",
		body : JSON.stringify(this.arr)
	}).then(function (aa){
		return aa.text();
	}).then(function (bb){
	    console.log(bb);
	}).catch(function(error){
		console.log(error);
	});
});

//FormData()는 POST는 되지만 DELETE는 안됨
function ajax_del(){
	var formdata = new FormData();
	formdata.append("midx",5);
	formdata.append("midx",9);
	formdata.append("midx",12);
	//console.log(formdata.getAll("midx"));
	var http,result;
	http = new XMLHttpRequest();
	http.open("POST","./ajax13/a123456",false);  //FormData()는 POST는 되지만 DELETE는 안됨
	http.onload = function(){
		console.log(this.response);
	};
	http.onerror = function(){
		console.log("통신오류");
	};
	http.send(formdata);
}
</script>
</html>