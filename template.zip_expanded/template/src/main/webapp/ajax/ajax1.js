//ajax => GET 통신
function ajax_gopage1(){
	var ajax,result;
	
	//ajax 동기화 : Back-end에 값을 받아야만 처리 후 다음 결과를 작동시키는 원리
	//ajax 비동기화 : Back-end에 값을 받지 않아도 다음 처리할 항목을 전송시킬 수 있음
	
	var data = "1,2,3,4"; //문자열
	//var data = new Array();  //빈배열
	//data[0] = "홍길동";
	//data[1] = "강감찬";
	//data[2] = "유관순";
	
	ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			if(ajax.status == 200){
				console.log(this.response); //Back-end값 출력
			}
		}
	}
	ajax.open("GET","./ajax1.do?product="+data,true); //false : 동기화, true(기본) : 비동기화
	ajax.send();
}