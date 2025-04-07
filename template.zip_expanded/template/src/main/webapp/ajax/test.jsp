<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
LocalDateTime now = LocalDateTime.now();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="button" value="전송" onclick="gopage()">
</body>
<script src="./test.js?v=<%=now%>"></script>
</html>