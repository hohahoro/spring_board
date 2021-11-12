<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Board View</title>
</head>
<body>

<p>번호 : ${boardView.bno}</p>
<p>제목 : ${boardView.btitle}</p>
<p>내용 : ${boardView.bcontent}</p>
<p>작성자 : ${boardView.bwriter}</p>
<p>작성시간 : ${boardView.bregdate}</p>

<a href="/modifyForm?bno=${boardView.bno}">수정하기</a>
<a href="/delete?bno=${boardView.bno}">삭제하기</a>

</body>
</html>
