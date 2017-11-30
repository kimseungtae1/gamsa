<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>


<c:set var="path" value="${pageContext.request.contextPath}"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<main class="main">
<input type="hidden" id="qna_id" name="qna_id" value="${question.id}" />
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
<!--DB에서 가져온 댓글테이블  -->
<table id="comment_area">
	<tbody>
	 <%--  <c:forEach var="comment" items="${CommentList}" varStatus="status">
	  	<tr>
	  		<td>${comment.writerId}</td>
	  		<td>${comment.content}</td>
	  		<td><fmt:formatDate value="${comment.date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  	</tr>
	  	<input type="hidden" class="Comment_id" name="Comment_id" value="${comment.id}" /> 
	  </c:forEach> --%>
  </tbody>
  <template>
	<tr>
		<td></td>
		<td></td>
		<td></td>
	</tr>
  </template>
</table>
<!-- 댓글 삽입하는 테이블 -->
<table>
	<tr>
		<td><textarea id="comment_content" name="comment_content" placeholder="댓글을 입력하세요."></textarea></td>
		<td><button id="comment_reg" name="comment_reg">댓글 등록</button></td>
		<td><button id="comment_update" name="comment_update">리플 새로고침</button></td>
	</tr>
</table>
<!-- Bootstrap -->
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>	
	var count = 0;
	$(function() {
		
		var updateComment = function() {

			
				//ajax 호출
				console.log("count더하기 전"+$(".Comment_id").last().val());
				
				var CommentLastId = $(".Comment_id").last().val();
				if(typeof CommentLastId == "undefined")
					CommentLastId = 0;
				var fianlCommentLastId = parseInt(CommentLastId) + count;
				console.log("count더하기 후"+fianlCommentLastId);
				
				var template  = $("#comment_area template");
				var tbody = $("#comment_area tbody");
				
				$.get("${path}/qna/comment/update-ajax?qnaId="
						+ $("#qna_id").val() + "&cId="
						+ fianlCommentLastId, function(data) {
						
						//alert(data);
						/* if(data=="[]")
							alert("최신댓글입니다.");
						else{ */
							var json =JSON.parse(data);//data를 json형식으로 만들어줌
				
	 						for (var i = 0; i < json.length; i++) {
								var clone = $(document.importNode(template.prop("content"),
										true));
								//$("template tr td").empty();
								var tds = clone.find("td");
								
								tds.eq(0).text(json[i].answerWriterId);
								tds.eq(1).text(json[i].content);
								var date = new Date(parseInt(Date.parse(json[i].regDate)));
								var month = date.getMonth()+1;
								var year = date.getFullYear();
								var day = date.getDate();
								var hour = date.getHours();
								var min = date.getMinutes();	
								var sec = date.getSeconds();
								tds.eq(2).text(year+"-"+month+"-"+day+" "+hour+":"+min+":"+sec);

								tbody.append(clone);// 복제된 clone(tr)을 노드 트리에 추가
								count++;
							}
						//}	
				});
			};
			
			updateComment();
			
		
			
			
		//댓글을 다는 이벤트
		$("#comment_reg")
				.click(
						function() {
							//null 검사
							if ($("#comment_content").val().trim() == "") {
								alert("내용을 입력하세요.");
								$("#comment_content").focus();
								return false;
							}
							var comment_content = $("#comment_content").val()
									.replace("\n", "<br>");//개행처리			
							//값 셋팅
							var objParams = {
								qna_id : $("#qna_id").val(),
								comment_content : comment_content
							};
							var token = $("meta[name='_csrf']").attr("content");
							var header = $("meta[name='_csrf_header']").attr(
									"content");
							var comment_id;
							//ajax 호출
							$.ajax({
										beforeSend : function(xhr) {
											xhr.setRequestHeader(header, token);
										},
										url : '${path}/qna/comment/save',
										dataType : 'gson',
										contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
										type : 'POST',
										async : false, //동기: false, 비동기: ture
										data : objParams,
										error : function(error) {
									        alert("등록완료");
									    },
									    success : function(gson) {
									        alert("등록실패");
									    }


									
								});

							//댓글 초기화
							//$("#comment_content").val("");
							$("#comment_area tbody").empty();
							updateComment();
						}

				);

		//댓글 새로고침을 하는 이벤트
		$("#comment_update").click(function() {
			updateComment();
		});

		//삭제링크를 눌렀을때 해당 댓글을 삭제하는 이벤트
		$(document)
				.on(
						"click",
						"table#commentTable a",
						function() {//동적으로 버튼이 생긴 경우 처리 방식
							if ($(this).attr("name") == "pDel") {
								if (confirm("답글을 삭제 하시면 밑에 답글도 모두 삭제 됩니다. 정말 삭제하시겠습니까?") == true) { //확인

									var delComment = $(this);
									delComment.remove();

								} else
									//취소
									return;
							}
						});

	});
</script>
<%-- 	<form id="formname1"  method="post">
	<input type="hidden" name="qnaId" value="${question.id}"/>
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
							<input type="text" name="content"/>
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
				       <input type="button" value="Ajax 폼 전송" onclick="formSubmit()" />
					</div>
				</c:if>
		
				<div class="reg-button">../notice/edit/${question.id}
					<a href="../edit/${answer.id}">수정</a>
				</div>
			</div>
	
	<input type="hidden"
	name="${_csrf.parameterName}"
	value="${_csrf.token}"/>
		</form>	 --%>	

	
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