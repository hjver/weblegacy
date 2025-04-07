function ajax_gopage2(){
	var ajax, result;
	
	var data = new Array();  //빈배열
	data[0] = "홍길동";
	data[1] = "강감찬";
	data[2] = "유관순";
	
	ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			console.log(this.response);
		}
	}
	
	ajax.open("POST", "./ajax2.do", true);
	//post 전송시 해당 content-type 을 설정하여 전송
	ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
	ajax.send("product="+data+"&person=hong"); //해당 key name명 + 배열 데이터
}