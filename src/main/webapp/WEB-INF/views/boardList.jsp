<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Board List</title>
</head>
<body>

	<table border="1">

		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>삭제</td>
		</tr>

		<c:forEach var="boardList" items="${boardList}">
			<tr>
				<td>${boardList.bno}</td>
				<td><a href="/boardView?bno=${boardList.bno}">${boardList.btitle}</a></td>
				<td>${boardList.bwriter}</td>
				<td>${boardList.bregdate}</td>
				<td><a href="/delete?bno=${boardList.bno}">삭제하기</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pageInfo">
		<c:if test="${pageMaker.prev}">
			<li><a href="${pageMaker.startPage-1}">이전</a></li>
		</c:if>
		
		<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<li><a href="${num}">${num}</a></li>
		</c:forEach>
		
		<c:if test="${pageMaker.next}">
			<li><a href="${pageMaker.endPage+1}">다음</a></li>
		</c:if>
	</div>


	<form action="boardList" method="get" class="moveForm">
		<input type="text" name="pageNum" value="${pageMaker.cri.pageNum}">
		<input type="text" name="amount" value="${pageMaker.cri.amount}">
	</form>

</body>
</html>


<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<script>
	$(function() {

		var moveForm = $(".moveForm");

		$(".pageInfo a").on("click", function(e) {
			e.preventDefault();
			moveForm.find("input[name = 'pageNum']").val($(this).attr("href"));
			moveForm.attr("action", "/boardList");
			moveForm.submit();
		})
	})
</script>
