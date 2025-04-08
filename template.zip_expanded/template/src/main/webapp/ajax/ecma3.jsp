<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA - ES7 ~ 기초코드2 (Class 및 외부 js 로드)</title>
</head>
<body>
<form id="frm">
검색 : <input type="text" name="search" id="mid" class="mid"><br>
<button type="button" id="btn">검색</button>
</form>
</body>
<script type="module">
import { zzz } from "./ecma2.js"; //zzz 함수를 로드
import { logins } from "./ecma3.js";  //logins라는 class를 로드
zzz();
new logins().zzz();

let obj = new logins();
obj.agree_data1 = {a: "7", b: "5"};
console.log(obj.agree_data2);
</script>
</html>