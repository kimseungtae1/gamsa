<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />


<c:set var="path" value="${pageContext.request.contextPath}" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<main class="main"> <input type="hidden" id="qna_id"
	name="qna_id" value="${question.id}" />
		<div class="sub-title">문의사항</div>
<div class="view_wrap">


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
			<td><fmt:formatDate pattern="yyyy-MM-dd"
					value="${question.regDate}" /></td>
		</tr>

	</table>

	<div class="page_num">
		<div class="content detail-content">${question.content}</div>

		<div class="reg-button">
			<a href="../list">목록으로</a>
		</div>

		<div class="reg-button">
			<%-- ../notice/edit/${question.id} --%>
			<a href="../edit/${question.id}">수정</a>
		</div>
	</div>
</div>
<!--DB에서 가져온 댓글테이블  -->
<div class="comment-all">
	<div class="sub-title">댓글</div>
	<table class="qna-board name">
		<tr>
			<td>작성자</td>
			<td>내용</td>
			<td>개시일</td>
		</tr>
	</table>
	<table id="comment_area" class="qna-board">


		<tbody id="data" class="view_wrap">

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
	<div class="view_wrap">
		<div class="sub-title reply">댓글 등록</div>
		<table class="board">
			<%-- <c:forEach var="" items=""> --%>
			<tr>

				<span class="reply-list-text"><textarea id="comment_content"
						name="comment_content" placeholder="댓글을 입력하세요."></textarea>
					<button id="comment_reg" name="comment_reg" class="reply-reg">댓글
						등록</button></span>

			</tr>
			<%-- </c:forEach> --%>
		</table>
	</div>
</div>
<!-- Bootstrap --> <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) --> <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	/* var auto_refresh = setInterval(
	 function ()
	 {
	 $('#comment_area').load('#comment_area #data').fadeIn("slow");
	 }, 1000);
	 */

	var commentLength = 0;
	var count = 0;

	$(function() {

		var updateComment = function() {

			//ajax 호출
			console.log("count더하기 전" + $(".Comment_id").last().val());

			var CommentLastId = $(".Comment_id").last().val();
			if (typeof CommentLastId == "undefined")
				CommentLastId = 0;
			var fianlCommentLastId = parseInt(CommentLastId) + count;
			console.log("count더하기 후" + fianlCommentLastId);

			var template = $("#comment_area template");
			var tbody = $("#comment_area tbody");

			$.get("${path}/qna/comment/update-ajax?qnaId=" + $("#qna_id").val()
					+ "&cId=" + fianlCommentLastId, function(data) {

				//alert(data);
				/* if(data=="[]")
					alert("최신댓글입니다.");
				else{ */
				var json = JSON.parse(data);//data를 json형식으로 만들어줌
				console.log(json);
				console.log("개수:" + json.length);
				commentLength = json.length
				for (var i = 0; i < json.length; i++) {
					var clone = $(document.importNode(template.prop("content"),
							true));
					//$("template tr td").empty();
					var tds = clone.find("td");

					tds.eq(0).text(json[i].answerWriterId);
					tds.eq(1).html(json[i].content);
					var date = new Date(parseInt(Date.parse(json[i].regDate)));
					var month = date.getMonth() + 1;
					var year = date.getFullYear();
					var day = date.getDate();
					var hour = date.getHours();
					var min = date.getMinutes();
					var sec = date.getSeconds();
					tds.eq(2).html(
							year + "-" + month + "-" + day + " " + hour + ":"
									+ min + ":" + sec);

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
							$
									.ajax({
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
											alert("성공");
										},
										success : function(gson) {
											alert("실패!");
										}

									});
							//댓글 초기화
							//$("#comment_content").val("");
							/* $("#comment_area").empty(); */
							/* $("#comment_area #data td").empty(); */
							$("#comment_area #data tr").remove();
							updateComment();
						});

	});
</script> </main>