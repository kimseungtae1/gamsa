<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
window.onload = function(){
	var answerAdd = document.querySelector(".main .answer .page_num .reg-button input[value='등록']");
	var answerForm = document.querySelector(".main #frm");
	
	answerAdd.onclick = function(){
		answerForm.submit();
	};
};
</script>

<main class="main">
	<div class="view_wrap">
	문의사항
		<table class="board">
			<tr>
				<th>번호</th>
				<td>${question.id}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${question.title}</td>
			</tr>
			<tr>
				<th>게시일</th>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${question.regDate}" /></td>
			</tr>
	
		</table>
		
		<div class="page_num">
			<div class="content detail-content">${question.content}</div>
	
			<div class="reg-button">
				<a href="../list">목록으로</a>
			</div>
			
			<div class="reg-button"><%-- ../notice/edit/${question.id} --%>
				<a href="../edit/${question.id}">수정</a>
			</div>
		</div>
	</div>
	
	<form id="frm" action="${path}/qna/list" method="post">
		<div class="answer">
		comment
			<c:if test="${not empty answer.content}">
				<table class="board">
					<tr>
						<th>번호</th>
						<td>${answer.id}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${answer.title}</td>
					</tr>
					<tr>
						<th>게시일</th>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${answer.regDate}" /></td>
					</tr>
				
				</table>
			</c:if>
				
			<c:if test="${empty answer.content}">
				<table class="board">
					<tr>
						<th>내용</th>
						<td>
							<input type="text"/>
						</td>
					</tr>
				
				</table>
			</c:if>
				
	
			<div class="page_num">
				<div class="content detail-content">${answer.content}</div>
				
				<c:if test="${not empty answer.content}">
					<div class="reg-button">
						<input type="button" value="수정"/>
					</div>
				</c:if>		
				<c:if test="${empty answer.content}">
					<div class="reg-button">
						<input type="button" value="등록"/>
					</div>
				</c:if>
		
		<%-- 		<div class="reg-button">../notice/edit/${question.id}
					<a href="../edit/${answer.id}">수정</a>
				</div> --%>
			</div>
		</div>
	</form>
	
	<div class="view_wrap">
	<%-- ${n} --%>개의 댓글
		<table class="board">
			<%-- <c:forEach var="" items=""> --%>
				<tr>
					<td class="table-text">
						<span class="reply-list-id">admin<%-- ${member.id} --%></span> 
						<span class="reply-list-date">2017-11-07<%-- ${answer.regDate} --%></span><br/>
						<span class="reply-list-text">아지토정식 13,000원<%-- ${answer.content} --%></span>
					</td>
				</tr>
			<%-- </c:forEach> --%>
		</table>
	</div>
	
</main>