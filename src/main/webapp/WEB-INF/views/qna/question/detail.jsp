<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<main class="main">
<div class="view_wrap">
	<table class="board">
		<tr>
			<th>번호</th>
			<td>1</td>
		</tr>
		<tr>
			<th>제목
			</th>
			<td>${qna.title}</td>
		</tr>
		<tr>
			<th>게시일
			</th>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${qna.regDate}" /></td>
		</tr>

	</table>
	<div class="page_num">
		<div class="content detail-content">${qna.content}</div>

		<div class="reg-button">
			<a href="../list">목록으로</a>
		</div>
		

		<div class="reg-button"><%-- ../notice/edit/${qna.id} --%>
			<a href="../edit/${qna.id}">수정</a>
		</div>
	</div>
		


	</div>
	
</main>