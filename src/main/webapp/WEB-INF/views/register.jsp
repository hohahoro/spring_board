<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Insert 예제</title>
</head>
<body>


	<form action="/register" method="post">
		
		제목입력 : <input type="text" name="btitle"><br>
		내용입력 : <input type="text" name="bcontent"><br>
		글쓴이 : <input type="text" name="bwriter"><br>
		
		<input type="submit" value="글쓰기">
	
	</form>



</body>
</html>
