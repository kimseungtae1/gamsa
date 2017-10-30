<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
	$(document).ready(function(){
	    var objDragAndDrop = $(".view_big_img");
	     
	    $(document).on("dragenter",".view_big_img",function(e){
	        e.stopPropagation();
	        e.preventDefault();
	        $(this).css('border', '2px solid #0B85A1');
	    });
	    $(document).on("dragover",".view_big_img",function(e){
	        e.stopPropagation();
	        e.preventDefault();
	    });
	    $(document).on("drop",".view_big_img",function(e){
	         
	        $(this).css('border', '2px dotted #0B85A1');
	        e.preventDefault();
	        var files = e.originalEvent.dataTransfer.files;
	     
	        handleFileUpload(files,objDragAndDrop);
	    });
	     
	    $(document).on('dragenter', function (e){
	        e.stopPropagation();
	        e.preventDefault();
	    });
	    $(document).on('dragover', function (e){
	      e.stopPropagation();
	      e.preventDefault();
	      objDragAndDrop.css('border', '2px dotted #0B85A1');
	    });
	    $(document).on('drop', function (e){
	        e.stopPropagation();
	        e.preventDefault();
	    });
	     
	    function handleFileUpload(files,obj)
	    {
	       for (var i = 0; i < files.length; i++) 
	       {
	            var fd = new FormData();
	            fd.append('file', files[i]);
	      
	            var status = new createStatusbar(obj); //Using this we can set progress.
	            status.setFileNameSize(files[i].name,files[i].size);
	            sendFileToServer(fd,status);
	      
	       }
	    }
	     
	    var rowCount=0;
	    function createStatusbar(obj){
	             
	        rowCount++;
	        var row="odd";
	        if(rowCount %2 ==0) row ="even";
	        this.statusbar = $("<div class='statusbar "+row+"'></div>");
	        this.filename = $("<div class='filename'></div>").appendTo(this.statusbar);
	        this.size = $("<div class='filesize'></div>").appendTo(this.statusbar);
	        this.progressBar = $("<div class='progressBar'><div></div></div>").appendTo(this.statusbar);
	        this.abort = $("<div class='abort'>중지</div>").appendTo(this.statusbar);
	         
	        obj.after(this.statusbar);
	      
	        this.setFileNameSize = function(name,size){
	            var sizeStr="";
	            var sizeKB = size/1024;
	            if(parseInt(sizeKB) > 1024){
	                var sizeMB = sizeKB/1024;
	                sizeStr = sizeMB.toFixed(2)+" MB";
	            }else{
	                sizeStr = sizeKB.toFixed(2)+" KB";
	            }
	      
	            this.filename.html(name);
	            this.size.html(sizeStr);
	        }
	         
	        this.setProgress = function(progress){       
	            var progressBarWidth =progress*this.progressBar.width()/ 100;  
	            this.progressBar.find('div').animate({ width: progressBarWidth }, 10).html(progress + "% ");
	            if(parseInt(progress) >= 100)
	            {
	                this.abort.hide();
	            }
	        }
	         
	        this.setAbort = function(jqxhr){
	            var sb = this.statusbar;
	            this.abort.click(function()
	            {
	                jqxhr.abort();
	                sb.hide();
	            });
	        }
	    }
	     
	    function sendFileToServer(formData,status)
	    {
	        var uploadURL = "/upload/photo/2017"; //Upload URL
	        var extraData ={}; //Extra Data.
	        var jqXHR=$.ajax({
	                xhr: function() {
	                var xhrobj = $.ajaxSettings.xhr();
	                if (xhrobj.upload) {
	                        xhrobj.upload.addEventListener('progress', function(event) {
	                            var percent = 0;
	                            var position = event.loaded || event.position;
	                            var total = event.total;
	                            if (event.lengthComputable) {
	                                percent = Math.ceil(position / total * 100);
	                            }
	                            //Set progress
	                            status.setProgress(percent);
	                        }, false);
	                    }
	                return xhrobj;
	            },
	            url: uploadURL,
	            type: "POST",
	            contentType:false,
	            processData: false,
	            cache: false,
	            data: formData,
	            success: function(data){
	                status.setProgress(100);
	      
	                //$("#status1").append("File upload Done<br>");           
	            }
	        }); 
	      
	        status.setAbort(jqXHR);
	    }
	     
	});
	</script>

<main class="main">

	<form action="notice-reg" method="post" enctype="multipart/form-data">

			<link type="text/css" rel="stylesheet" href="Reg.css">
			<link type="text/css" rel="stylesheet" href="reset.css">


	

			<div>
				<div class="view_wrap" id="view_top">
					<div class="profile">

						<div class="left">
							<h2>제목:
								<input type="text" name="title" value="${n.title}" />
							</h2>
						</div>

						<div class="right">
							<h2>사진작가명</h2>
						</div>
					</div>






					<div class="view_big_img">
					
						<img src="" alt="사진을 넣으세요!">
						<button>사진 업로드</button>


					</div>
					
					
					<h2>설명</h2>
					<div class="detail"><textarea name="content"></textarea></div>
					<br>
						<div class="view_img_btn">
							<ul>
								<li></li>
								<li><button type="button" class="view_down btn_down">
										<img src="/static/common/img/view_icon_01.png" alt="등록"><span
											class="view_btn_txt"></span>
									</button></li>



								<li><button type="button" class="view_sns">
										<img src="/static/common/img/view_icon_02.png" alt="취소">
									</button></li>

							</ul>
						</div>


					</div>









			</div>

	</form>

</main>