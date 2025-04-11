document.querySelector("#hpmodify").addEventListener("click",function(){
	//해당 연락처 수정 버튼을 클릭시 연락처 입력 부분을 수정할 수 있도록 disable 정지시킴
	if(confirm("연락처를 수정하시겠습니까?")){
		document.querySelector("#MHP").disabled = false;
	}
});

//이메일 수정시
document.querySelector("#emailmodify").addEventListener("click",function(){
	if(confirm("이메일을 수정하시겠습니까?")){
		document.querySelector("#MEMAIL").disabled = false;
	}
});

//배열 키 : MPASS(패스워드), MEMAIL(이메일), MHP(연락처)

//개인정보 수정 버튼 클릭시 AJAX 발동
document.querySelector("#modify_myinfo").addEventListener("click",function(){
	/* //Map 배열로 키를 생성하여 전달하는 방식(Spring에서는 안됨, Spring-boot에서 사용가능)
	let udata = new Map();
	udata.set("MID",document.querySelector("#MID").value)
	if(document.querySelector("#MPASS").value != ""){
		udata.set("MPASS",document.querySelector("#MPASS").value);
	}
	udata.set("MHP",document.querySelector("#MHP").value);
	udata.set("MEMAIL",document.querySelector("#MEMAIL").value);
	*/
	
	fetch("./myinfo_modify.do/mykey",{
		method : "PATCH",
		headers : {"content-type":"application/json"},
		body : JSON.stringify({
			"MID" : document.querySelector("#MID").value,
			"MPASS" : document.querySelector("#MPASS").value,
			"MHP" : document.querySelector("#MHP").value,
			"MEMAIL" : document.querySelector("#MEMAIL").value,
		})
	}).then(function(result){
		return result.text();
	}).then(function(data){
		if(data=="ok"){
			alert("정상적으로 회원정보가 변경 되었습니다.");
			location.reload();
		}
		else{
			alert("시스템이 원할하지 회원정보변경이 완료 되지 않았습니다.");
		}
	}).catch(function(error){
		console.log(error)
	});
});