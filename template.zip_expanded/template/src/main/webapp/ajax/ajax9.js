export class ecma_ajax{
	
	//배열 생성 후 POST 전송
	ajax_arr(){
		//this.arr = {mid:"hong",mname:"홍길동"};
		//this.arr = ["hong","kim","park"];
		fetch("./ajax11.do",{
			method:"POST",
			//Back-end : @RequestBody => JSONArray, JSONObject
			/* 
			headers : {"content-type":"application/json"},
			body : JSON.stringify(this.arr)
			*/
			
			//Back-end : @RequestParam 또는 @ModelAttribute 처리가능
			/* 
			headers : {"content-type":"application/x-www-form-urlencoded"},
			body : "mid=hong&mname=홍길동"
			*/
			
			//new URLSearchParams : 키배열, 원시배열을 구성하여 Ajax로 데이터 전송시키는 방식
			headers : {"content-type":"application/x-www-form-urlencoded"},
			body : new URLSearchParams({
				mid : "hong",
				mname : "홍길동",
				mage : 25
			})
			
		}).then(function (aa){
			return aa.text();
		}).then(function (bb){
		    console.log(bb);
		}).catch(function(error){
			console.log(error);
		});
	}
}