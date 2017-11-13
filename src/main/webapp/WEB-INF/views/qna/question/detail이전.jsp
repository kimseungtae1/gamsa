<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
window.addEventListener("load", function(){
	
	var submitButton = document.querySelector("#submit-button");

	submitButton.onclick = function(e){
		 var event = new MouseEvent("click", {
				'view' : window,
				'bubbles' : true,
				'cancelbale' : true
		}); 
		var formData = $("#frm").serialize();
			var file = fileInput.files[0];
			alert(file.name);
			var formData = new FormData();
			formData.append("title", "테스트");
			formData.append("content", "content");
			
			var xhr = new XMLHttpRequest(); 
			xhr.open("POST", "${path}/qna/detail/regcomment?${_csrf.parameterName}=${_csrf.token}", true);
			xhr.send(formData);

	};
});
/* function submitForm() {
    var formData = $("#frm").serialize();
     console.log(formData);
    $.ajax({
        type: "POST",
        url: "../../../qna/detail/regcomment",
        data: formData,
        success: function(json) {
            // 받아온 데이터 파싱 후 
            var json_data = JSON.stringify(json);
            var parse_data = JSON.parse(json_data);
     
        },
        error: function() {
         
        }        
    });
    
} */


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
	
	<form name="frm" id="frm" action="${path}/qna/list" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
						<input type="button" value="등록"/>
					</div>
					
				</c:if>		
				<c:if test="${empty answer.content}">
					<div class="reg-button">
						<button type="button" id="submit-button">전송</button>

					</div>
				</c:if>
		
		<%-- 		<div class="reg-button">../notice/edit/${question.id}
					<a href="../edit/${answer.id}">수정</a>
				</div> --%>
			</div>
	</form>
		

	

	
</main>