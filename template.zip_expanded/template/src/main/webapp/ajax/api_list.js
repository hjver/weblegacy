export function api_select(){
	fetch("./api_select.do?key=apink",{
		method : "GET"
	}).then(function(aa){
		return aa.json(); //text() : 문자열, json() => JSON.parse와 동일
	}).then(function(bb){
		//forEach, for in (ECMA), JQuery (forEach, Each, for in)
		//foeEach, Map (React)
		let ea = bb["data_all"].length;
		bb["data_all"].forEach(function(a,b,c){ //a : 배열데이터, b:배열 그룹먼호, c:전체배열
			//console.log(a["pd1"]);
			//innerHTML로 배열값을 웹페이지에 출력하는 코드
			document.querySelector("#table_view").innerHTML += `
				<tr data-index='`+a["midx"]+`'>
				<td>`+Number(ea-b)+`</td>
				<td>`+a["pd1"]+`</td>
				<td>`+a["pd2"]+`</td>
				<td>`+a["pd3"]+`</td>
				<td>`+a["pd4"]+`</td>
				<td>`+a["pd5"]+`</td>
				</tr>
				`;
		});
	}).catch(function(error){
		console.log(error);
	});
}