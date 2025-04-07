function gopage(){
	var ajax;
	var data = new FormData();
	data.append("pd", "홍길동");
	
	ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			console.log(this.response);
		}
	}
	ajax.open("POST", "./test.do", true);
	ajax.send(data)
}