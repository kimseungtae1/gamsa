<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<main>
<div class="view_wrap">
	<table class="board">
		<tr>
			<th>번호
			</td>
			<td>1</td>
		</tr>
		<tr>
			<th>제목
			</td>
			<td>${n.title}</td>
		</tr>
		<tr>
			<th>게시일
			</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${n.regDate}" /></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="3"><c:forEach var="f" items="${files}"
					varStatus="s">
					<a href="../download?f=${f.src}">${f.src}</a>
					<c:if test="${!s.last}">,</c:if>
				</c:forEach></td>
		</tr>
	</table>
	<div class="page_num">
		<div class="content">${n.content}</div>

		<div class="reg-button">
			<a href="../notice">목록으로</a>
		</div>

	</div>
</main>