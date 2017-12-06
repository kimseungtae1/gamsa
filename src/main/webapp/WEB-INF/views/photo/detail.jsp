<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<c:set var="path" value="${pageContext.request.contextPath}" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진상세페이지</title>
<link rel="stylesheet" href="${path}/resource/css/qna/list.css">
<link rel="stylesheet" href="${path}/resource/css/qna/reg.css">
<link rel="stylesheet" href="${path}/resource/css/qna/reply.css">
<link rel="stylesheet" href="${path}/resource/css/photo/detail.css">

</head>
<body class="main"><input type="hidden" id="photo_id"
	name="photo_id" value="${p.id}" />
	
	<div class="sub-title top">감성사진</div>
	<div class="view_wrap">
		<c:set var="TextValue" value="${p.src}" />
		<c:set var="reTextValue" value="${fn:replace(TextValue, '\', '/')}" />
		<table class="board">
			<tr>
				<th>번호</th>
				<td>${p.id}</td>
				<th>제목</th>
				<td>${p.title}</td>
				<th>게시일</th>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${p.regDate}" /></td>
			</tr>
		</table>
		<div class="img_area"><img src="${fn:substringAfter(reTextValue,'wtpwebapps')}" ></div>
		<a class="buttonDownload"  href="${fn:substringAfter(reTextValue,'wtpwebapps')}" download>Download</a>

	</div>
<!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  -->
<!--DB에서 가져온 댓글테이블  -->
<div class="comment-all">
	<div class="sub-title">댓글</div>
<table  class="qna-board name comment-title">
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
						
				<span class="reply-list-text"><textarea id="comment_content" name="comment_content"
				placeholder="댓글을 입력하세요."></textarea>
				<button id="comment_reg" name="comment_reg" class="reply-reg">댓글 등록</button>
				</span>
			</td>
		</tr>
		<%-- </c:forEach> --%>
	</table>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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

			$.get("${path}/photo/comment/update-ajax?photoId=" + $("#photo_id").val()
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

					tds.eq(0).text(json[i].writerId);
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
								photo_id : $("#photo_id").val(),
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
										url : '${path}/photo/comment/save',
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
<!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  -->	





	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="tag-section">
		<div class="tag-section-child">#태그1#태그2#태그3</div>
		<div class="tag-section-child">#태그1#태그2#태그3</div>
		<div class="tag-section-child">#태그1#태그2#태그3</div>
	</div>
	</main>
</body>
</html>