function ajax_gopage2(){
	var ajax, result;
	var data = [];
	data.push({"pd1":"홍길동"});
	data.push({"pd2":"강감찬"});
	data.push({"pd3":"유관순"});

	ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			console.log(this.response);
		}
	}
	
	ajax.open("POST", "./ajax4.do", true);
	//ajax.setRequestHeader("content-type","application/json");
	ajax.send(JSON.stringify(data));  
}


function ajax_gopage(){
	var ajax, result;
	
	//FormData => 동일한 키를 사용안할 경우 (문자열로 각 키별로 셋팅) - get
	//동일한 키를 사용할 경우 (원시배열 형태로 구성) getAll
	var data = new FormData();
	data.append("pd","홍길동");  //append("키명","데이터")
	data.append("pd","이순신");
	data.append("pd","강감찬");
	
	//console.log(data.get("pd1"));
	console.log(data.getAll("pd"));  //무조건 배열로 설정(같은 키 getAll)
	
	ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			console.log(this.response);
		}
	}
	/*
	setRequestHeader 전송방식
	1. applicaion/x-www-form-urlencoded : 문자로 전송 (name값으로 전송)
	2. applicaion/json : json배열의 값을 이용하여 전송
	3. multipart/form-data : IO 형태의 값을 전송
	4. 가상의 키와 value을 이용하여 Header에 적용하여 전송 (한글 value는 사용 불가능)
	*/
	ajax.open("POST", "./ajax3.do", true);
	ajax.setRequestHeader("content-type","applicaion/json");
	ajax.send(data);  //FormData객체를 전송
	
	//ajax.setRequestHeader("user",apink); //key : user, value : apink
	//ajax.send(); 
} 