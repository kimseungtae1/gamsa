<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta charset="UTF-8">
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
/* window.addEventListener("beforeunload", function(evt){
	 
	$.ajax({
		url : "${path}/photo/delete",
		type : "delete",
		success : function(data) {
			//F_FileMultiUpload_Callback(data.files);
			console.log('success');
			alert("aa");
		}
	});
	 
}); */
/* window.addEventListener("beforeunload", function (evt) {
	  evt.returnValue = "진짜 나감?";
	}); */
	
/* 
window.onbeforeunload = function(){
	
	var xhr = new XMLHttpRequest();
	
	
	xhr.onerror = function(e){
		alert("예기치 못한 오류가 생겼습니다.")
	};
	
	xhr.open("get", "${path}/photo/delete", true);
	xhr.send();
	
	return "";
};
 */
$(function () {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	$(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
	
    var obj = $(".view_big_img");
    var submitButton = $("#uploadForm input[type='submit']");
    var resetButton = $("#uploadForm input[type='reset']");
    var form = $("#form");
    var uploadForm = $("#uploadForm");
    var files = "";
  
    
    obj.on('dragenter', function (e) {
         e.stopPropagation();
         e.preventDefault();
         obj.css('border', '2px solid #5272A0');
    });
    obj.on('dragleave', function (e) {
         e.stopPropagation();
         e.preventDefault();
         obj.css('border', '2px dotted #8296C2');
    });
    obj.on('dragover', function (e) {
         e.stopPropagation();
         e.preventDefault();
    });
    obj.on('drop', function (e) {
         e.preventDefault();
         obj.css('border', '2px dotted #8296C2');
         //var files = e.originalEvent.dataTransfer.files;
         files = e.originalEvent.dataTransfer.files;
         
         var file = files[0];
         console.log(file);
         
         var formData = new FormData();
         
         formData.append("file", file);
         
		$.ajax({
			url : "${path}/photo/upload?${_csrf.parameterName}=${_csrf.token}",
			type : "post",
			data : formData,
			dataType : "text",
			processData: false,
            contentType: false,
			success : function(data) {
				//F_FileMultiUpload_Callback(data.files);
				console.log('success');
				alert("이미지는 업로드 되었습니다. 사진 제목과 내용을 입력해주세요.");
			}
		});
	});
    $(window).on("beforeunload",function (evtt){
    	
    	submitButton.click(function() {
    		$(window).off("beforeunload");
    		uploadForm.submit();
    	});
    	
    	/* $.ajax({
    		url : "${path}/photo/delete",
    		cache : "false",
    		type : "delete",
    		async : false, //동기화설정(비동기화사용안함)
    		success:function(args){   
    			console.log('데이터삭제success');
    		},   
    		error:function(e){  
    			console.log('오류로 인해 삭제가 되지 않았습니다.');  
    		}
    	});  */
    	return ""; 
    });
    
	resetButton.click(function(event) {
		event.stopPropagation();
		event.preventDefault();
		alert("사진업로드가 취소되었습니다.");
		location.href = "${path}/photo/delete";
	});
	
	
		//파일 멀티 업로드
		/* function F_FileMultiUpload(files, obj) {
			if (confirm(files.length + "개의 파일을 업로드 하시겠습니까?")) {
				console.log(files[0]);
				var formData = new FormData();
				for (var i = 0; i < files.length; i++) {
					formData.append('file', files[i]);
				}
				$
						.ajax({
							url : "${path}/photo/upload?${_csrf.parameterName}=${_csrf.token}",
							type : 'post',
							data : formData,
							 enctype:"multipart/form-data", 
							dataType : 'text',
							processData : false,
							contentType : false,
							success : function(data) {
								F_FileMultiUpload_Callback(data.files);
								console.log('success');
								alert(data);
								var str = "";
								
								if(checkImageType(data)){ 
								     str = "<div><a href='${path}/upload/displayFile?fileName="+getImageLink(data)+"'>";
								     str += "<img src='${path}/upload/displayFile?fileName="+data+"'></a>";
								 // 일반파일이면 다운로드링크
								 } else { 
								     str = "<div><a href='${path}/upload/displayFile?fileName="+data+"'>"+getOriginalName(data)+"</a>";
								 }
								 // 삭제 버튼
								 str += "<span data-src="+data+">[삭제]</span></div>";
								 $(".uploadedList").append(str);
							}
						});
			}
		}
		//파일 멀티 업로드 Callback
		function F_FileMultiUpload_Callback(files) {
			for (var i = 0; i < files.length; i++)
				console.log(files[i].file_nm + " - " + files[i].file_size);
		} */
	});
</script>
</head>
<body>
	<main class="main">
	
		<div>
			<div class="view_wrap" id="view_top">
				
				<%-- <form id="uploadForm" action="${path}/photo/upload/reg?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data"> --%> 
					<div class="view_big_img">
						<img src="" alt="사진을 넣으세요!">
						<!-- <input class="" type="submit" value="사진 업로드"/> -->
						
					</div>				

				<form id="uploadForm" action="${path}/photo/upload/reg?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
					<div class="profile">
						<div class="left">
							<h2 class="font-h2">제목
								<input class="font-h2" type="text" name="title" />
							</h2>
						</div>
	
						<div class="right">
							<h2 class="font-h2">사진작가명</h2>
						</div>
					</div>
				
					<h2 class="font-h2">설명</h2>
					<div class="detail">
						<textarea name="explain"></textarea>
					<br>
						<div class="view_img_btn">
							<ul>
								<li></li>
								<li>
									<input type="submit" value="내용등록" />
								<!-- <button type="button" class="view_down btn_down">
										<img src="/static/common/img/view_icon_01.png" alt="등록">
										<span class="view_btn_txt"></span>
									</button> -->
								</li>

								<li>
									<input type="reset" value="취소" />
								<!-- <button type="button" class="view_sns">
										<img src="/static/common/img/view_icon_02.png" alt="취소">
									</button> -->
								</li>
	
							</ul>
						</div>
					</div>
				</form>
				
			</div>
				
		</div>

	</main> 
</body>