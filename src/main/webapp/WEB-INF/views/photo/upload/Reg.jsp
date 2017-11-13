<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

$(function () {
    var obj = $(".view_big_img");
    var submitbutton = $("#uploadForm input[type='submit']");
    

    obj.on('dragenter', function (e) {
         e.stopPropagation();
         e.preventDefault();
         $(this).css('border', '2px solid #5272A0');
    });

    obj.on('dragleave', function (e) {
         e.stopPropagation();
         e.preventDefault();
         $(this).css('border', '2px dotted #8296C2');
    });

    obj.on('dragover', function (e) {
         e.stopPropagation();
         e.preventDefault();
    });

    obj.on('drop', function (e) {
         e.preventDefault();
         $(this).css('border', '2px dotted #8296C2');

         var files = e.originalEvent.dataTransfer.files;
         if(files.length < 1)
              return;

         F_FileMultiUpload(files, obj);
    });

  //파일 멀티 업로드
    function F_FileMultiUpload(files, obj) {
    	if(confirm(files.length + "개의 파일을 업로드 하시겠습니까?") ) {
    		
    	    var formData = new FormData();
    	    
    	    for (var i = 0; i < files.length; i++) {
    	   	  formData.append('file', files[i]);
    	    }
    	 
    	    var url = "../../../../gamsung/photo/upload?${_csrf.parameterName}=${_csrf.token}";
    	    //var url = "Reg.jsp";
    	    $.ajax({
    	       url: url,
    	       method: 'post',
    	       data: formData,
    	       enctype:"multipart/form-data",
    	       dataType: 'json',
    	       processData: false,
    	       contentType: false,
    	       success: function(res) {
    	           F_FileMultiUpload_Callback(res.files);
    	           console.log('success');
    	       }
    	    });
    	}
    }

    //파일 멀티 업로드 Callback
    function F_FileMultiUpload_Callback(files) {
    	for(var i=0; i < files.length; i++)
    	    console.log(files[i].file_nm + " - " + files[i].file_size);
    }
    
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

				
				<form action="?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
					<div class="profile">
						<div class="left">
							<h2 class="font-h2">제목
								<input class="font-h2" type="text" name="title" />
							</h2>
						</div>
	
						<div class="right">
							<h2 class="font-h2">사진작가명${n.writerId}</h2>
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