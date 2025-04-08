document.querySelector("#btn").addEventListener("click",function(){
	new myinfos().ajax_data();
});

//var, let(동일한 변수명을 사용못함), const(상수)
export class myinfos{
	
	ajax_data(){
		//사용자가 입려한 아이디값을 가져오는 코드
		let mid = document.querySelector("#mid").value;
		this.arr = new Array(); 
		this.arr[0] = mid;
		this.arr[1] = "apink@naver.com";
		this.arr[2] = "01010041004";
		this.arr[3] = "서울시 종로구";
		
		fetch("./ajax12.do/patch_myinfo",{
			method : "PATCH",
			headers : {"content-type":"application/json"},
			body : JSON.stringify(this.arr)
		}).then(function (aa){
			return aa.text();
		}).then(function (bb){
		    console.log(bb);
		}).catch(function(error){
			console.log(error);
		});
	}
	
}