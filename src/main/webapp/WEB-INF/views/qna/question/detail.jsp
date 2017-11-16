<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>


<c:set var="path" value="${pageContext.request.contextPath}"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>




var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");


function formSubmit() {
    var params = jQuery("#formname1").serialize(); // serialize() : 입력된 모든Element(을)를 문자열의 데이터에 serialize 한다.ide=54&
    console.log(params);
    jQuery.ajax({
    	beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        /* url: '${path}/qna/detail/regcomment', */
        url: '${path}/qna/detail/regcomment',
        type: 'POST',
        data:params,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
        dataType: 'html',
        success: function(data){
            // 성공 했을 때 처리
            alert("sucsess");
            },
             error:function(request,status,error){
                alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
               },
             complete : function(data) {
                         //  실패했어도 완료가 되었을 때 처리
                         alert("end");
                }


    });
    
    function commentList(){
        $.ajax({
            url : '${path}/qna/detail/commentlist',
            type : 'get',
            data : {'qnaId':qnaId},
            success : function(data){
                var a =''; 
                $.each(data, function(key, value){ 
                    a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
                    a += '<div class="commentInfo'+value.cno+'">'+'댓글번호 : '+value.cno+' / 작성자 : '+value.writer;
                    a += '<a onclick="commentUpdate('+value.cno+',\''+value.content+'\');"> 수정 </a>';
                    a += '<a onclick="commentDelete('+value.cno+');"> 삭제 </a> </div>';
                    a += '<div class="commentContent'+value.cno+'"> <p> 내용 : '+value.content +'</p>';
                    a += '</div></div>';
                });
                
                $(".commentList").html(a);
            },
             error:function(request,status,error){
                alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
               }
        });
    }



    
    
    

    $(document).ready(function(){
        commentList(); //페이지 로딩시 댓글 목록 출력 
    });

    
    
}
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
	
	<form id="formname1"  method="post">
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
		
		<%-- 		<div class="reg-button">../notice/edit/${question.id}
					<a href="../edit/${answer.id}">수정</a>
				</div> --%>
			</div>
	
	<input type="hidden"
	name="${_csrf.parameterName}"
	value="${_csrf.token}"/>
		</form>				
	   <div class="container">
        <div class="commentList"></div>
    </div>

	
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