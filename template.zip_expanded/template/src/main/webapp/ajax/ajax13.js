console.log("테스트");
export class api_insert{
	
	api_put(){
		console.log("test");
		fetch("./ajax14/a123456",{
			method : "POST",
			headers : {"content-type":"application/json"},
			body : JSON.stringify({  //new URLSearchParams
				pd1 : document.querySelector("#pd1").value,
				pd2 : document.querySelector("#pd2").value,
				pd3 : document.querySelector("#pd3").value,
				pd4 : document.querySelector("#pd4").value,
				pd5 : document.querySelector("#pd5").value,
			})
		}).then(function(aa){
			return aa.text();
		}).then(function(bb){
			if(bb=="ok"){
				alert("정상저으로 데이터가 저장 되었습니다.");
				location.reload();
			}
			else{
				alert("해당 정보가 올바르게 저장되지 않았습니다.");
			}
		}).catch(function(error){
			console.log(error);
		});
	}
}