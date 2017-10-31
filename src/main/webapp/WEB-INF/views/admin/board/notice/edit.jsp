<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<main class="main">
<form action="?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
		<fieldset>
	<div class="view_wrap">
		<table class="board">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${n.title}" /></td>
			</tr>
		</table>
		
		<div class="page_num">
		<textarea name="content" rows="20" cols="60">${n.content}</textarea>
		<div class="reg-button">
			<a href="../../notice/delete/${n.id}">삭제</a>

		<input type="submit" class="reg-button" value="등록" />


					<a href="../../notice">취소</a>
		</div>

	</div>



</fieldset>
</form>
</main>